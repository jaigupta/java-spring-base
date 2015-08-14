package com.djw.post.service;

import com.djw.post.entity.Post;

public interface PostService {
	/**Set name and auth_id properly for authenticated posts
	 * Post.isAuthenticatedPost() returns true on such posts.
	 **/
  public Post createAuthenticatedPost(Post post);

  /**No need to set the fields name and auth_id
   * name will default to Anonymous.
   * auth_id will default to -1
   * Post.isAuthenticatedPost() will return false on such posts.
   * @param post
   */
  public Post createUnauthenticatedPost(Post post);

  public void deletePost(long postId);

  public void updatePost(Post post);

  public Post getPost(long postId);

  public Iterable<Post> getPostFromTitle(String titleHint);

  public Iterable<Post> getUserPosts(long authId);

  public Iterable<Post> getRecentPosts();
}
