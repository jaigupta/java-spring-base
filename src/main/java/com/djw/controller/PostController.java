package com.djw.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djw.auth.entity.UserProfile;
import com.djw.database.NoSuchEntityException;
import com.djw.post.entity.Post;
import com.djw.post.entity.PostComment;
import com.djw.post.entity.UserPostLike;
import com.djw.post.service.PostCommentService;
import com.djw.post.service.PostService;
import com.djw.post.service.UserPostLikeService;
import com.djw.web.common.WebResponse;
import com.djw.web.form.StringValidator;
import com.djw.web.producer.SessionEntity;

@Controller
@RequestMapping("post")
public class PostController {
  @Autowired
  PostService postService;
  @Autowired
  PostCommentService postCommentService;
  @Autowired
  UserPostLikeService membershipService;

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String getNewPost(WebResponse response) {
    response.setTargetPage("post/new_post");
    response.getPreparedResponse();
    return "landing";
  }

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String postNewPost(WebResponse response,
      HttpServletRequest request,
      @SessionEntity UserProfile requesterUser,
      @RequestParam(value = "post_title", defaultValue = "") String postTitle,
      @RequestParam(value = "post_value", defaultValue = "") String postValue) {
    new StringValidator(postTitle, response, "post_title").minLength(5);
    new StringValidator(postValue, response, "post_value").minLength(5);
    if (response.hasError()) {
      response.setTargetPage("post/new_post");
      response.getPreparedResponse();
      return "landing";
    }
    Post post = new Post().setAuthId(requesterUser.getAuthId())
        .setName(requesterUser.getFullName())
        .setTitle(postTitle)
        .setData(postValue);
    postService.createAuthenticatedPost(post);
    return "redirect:/post/list/my";
  }

  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public String updatePost(WebResponse response) {
    response.setTargetPage("post/update_post");
    response.getPreparedResponse();
    return "landing";
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String updatePost2(WebResponse response) {
    response.setTargetPage("post/update_post");
    response.getPreparedResponse();
    return "landing";
  }

  @RequestMapping(value = "/get/{postId}", method = RequestMethod.GET)
  public String deletePost(WebResponse response, @PathVariable Long postId)
      throws NoSuchEntityException {
    Post post = postService.getPost(postId);
    Iterable<PostComment> commentsOnPost = postCommentService
        .getCommentsOnPost(postId);
    response.addAttribute("post", post);
    response.addAttribute("commentList", commentsOnPost.iterator());
    response.setTargetPage("post/get_post");
    response.getPreparedResponse();
    return "landing";
  }

  @RequestMapping(value = "/list/my")
  public String listMyPosts(WebResponse response,
      @SessionEntity UserProfile requesterUser) {
    Long authId = requesterUser.getAuthId();
    Iterable<Post> userPosts = postService.getUserPosts(authId);
    response.addAttribute("postList", userPosts.iterator());
    response.setTargetPage("post/list_my");
    response.getPreparedResponse();
    return "landing";
  }

  @RequestMapping(value = "/comment/new")
  public String addComment(
      @RequestParam(value = "post_id", defaultValue = "-1") Long postId,
      @RequestParam(value = "data", defaultValue = "") String data,
      @SessionEntity UserProfile requesterUser) {
    // Should we verify if the Post actually exists?
    Long authId = requesterUser.getAuthId();
    String name = requesterUser.getFullName();
    PostComment comment = new PostComment();
    comment.setAuthId(authId);
    comment.setName(name);
    comment.setData(data);
    comment.setParentEntityId(postId);
    postCommentService.addAuthorisedComment(comment);
    return "redirect:/post/get/" + postId;
  }

  @RequestMapping(value = "/starred")
  public String listStarredPosts(WebResponse response) {
    response.setTargetPage("post/list_starred");
    return "landing";
  }

  @RequestMapping("/upvote/{postId}")
  @ResponseBody
  public String likePost(WebResponse response,
      @SessionEntity UserProfile requesterUser,
      @PathVariable Long postId) throws Exception {
    Long authId = requesterUser.getAuthId();
    String requesterName = requesterUser.getFullName();
    if (updatePostLike(authId, postId, +1, response, requesterName)) {
      response.setSuccess();
    } else {
      response.setFailed();
    }
    return response.getJsonResponse();
  }

  @RequestMapping("/downvote/{postId}")
  @ResponseBody
  public String dislikePost(WebResponse response,
      @SessionEntity UserProfile requesterUser,
      @PathVariable Long postId) throws JsonGenerationException,
      JsonMappingException, IOException {
    Long authId = requesterUser.getAuthId();
    String requesterName = requesterUser.getFullName();
    if (updatePostLike(authId, postId, -1, response, requesterName)) {
      response.setSuccess();
    } else {
      response.setFailed();
    }
    return response.getJsonResponse();
  }

  @RequestMapping("/removevote/{postId}")
  @ResponseBody
  public String removeVoteOnPost(WebResponse response,
      @SessionEntity UserProfile requesterUser,
      @PathVariable Long postId)
      throws JsonGenerationException, JsonMappingException, IOException {
    long authId = requesterUser.getAuthId();
    UserPostLike postLike = membershipService.getLike(postId, authId);
    if (postLike == null) {
      response.setFailed();
      return response.getJsonResponse();
    }
    membershipService.removeLike(postLike);
    response.setSuccess();
    return response.getJsonResponse();
  }

  @RequestMapping("/list/recent")
  public String listRecentPosts(WebResponse response) {
    Iterable<Post> recentPosts = postService.getRecentPosts();
    response.addAttribute("postList", recentPosts.iterator());
    response.setTargetPage("post/list_my");
    response.getPreparedResponse();
    return "landing";
  }

  @RequestMapping("/search")
  public String findPosts(@RequestParam String searchTerm,
      WebResponse response) {
    Iterable<Post> searchPosts = postService.getPostFromTitle(searchTerm);
    response.addAttribute("postList", searchPosts.iterator());
    response.setTargetPage("post/list_my");
    response.getPreparedResponse();
    return "landing";
  }

  private boolean updatePostLike(long authId, long postId, int voteDiff,
      WebResponse response, String requesterName) {
    // Assert post exists and in state_active
    // TODO(jaigupta): Remove this and load instead of get.
    Post post = postService.getPost(postId);
    UserPostLike postLike = membershipService.getLike(postId, authId);
    if (postLike != null) {
      if (postLike.getValue() == voteDiff) {
        return false;
      }
    } else {
      postLike = new UserPostLike();
      postLike.setChildId(authId);
      postLike.setParentId(postId);
      postLike.setUserName(requesterName);
    }
    if (postLike.getValue() > 0) {
      post.setUpvoteCount(post.getUpvoteCount() - postLike.getValue());
    } else {
      post.setDownvoteCount(post.getDownvoteCount() + postLike.getValue());
    }
    if (voteDiff > 0) {
      post.setUpvoteCount(post.getUpvoteCount() + voteDiff);
    } else {
      post.setDownvoteCount(post.getDownvoteCount() - voteDiff);
    }
    postLike.setValue(voteDiff);
    membershipService.updateLike(postLike);
    postService.updatePost(post);
    response.addAttribute("upvote_count", post.getUpvoteCount());
    response.addAttribute("downvote_count", post.getDownvoteCount());
    return true;
  }
}