package com.djw.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djw.auth.entity.User;
import com.djw.entity.IEntity.EntityType;
import com.djw.indexing.IndexDocService;
import com.djw.indexing.solr.entity.IndexableDocument;
import com.djw.post.dao.PostRepository;
import com.djw.post.entity.Post;
import com.djw.post.entity.QPost;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Transactional
public class PostServiceImpl implements PostService {

  @Autowired PostRepository postDAO;
  @Autowired IndexDocService indexDocService;
  @Autowired @Qualifier("useSolrForIndexing") Boolean useSolrForIndexing;

  @Override
  public Post createAuthenticatedPost(Post post) {
    Post postUpdated = postDAO.save(post);
    if (useSolrForIndexing) {
      indexDocService.addToIndex(postUpdated.toIndexableDocument());
    }
    return postUpdated;
  }

  @Override
  public Post createUnauthenticatedPost(Post post) {
    post.setAuthId(User.ANONYMOUS_ID);
    post.setName(User.ANONYMOUS_NAME);
    Post postUpdated = postDAO.save(post);
    if (useSolrForIndexing) {
      indexDocService.addToIndex(postUpdated.toIndexableDocument());
    }
    return postUpdated;
  }

  @Override
  public void deletePost(long postId) {
    postDAO.delete(postId);
    if (useSolrForIndexing) {
      indexDocService.deleteFromIndex(IndexableDocument.createId(
          EntityType.ENTITY_POST, postId));
    }
  }

  @Override
  public Post getPost(long postId) {
    return postDAO.findOne(postId);
  }

  @Override
  public Iterable<Post> getPostFromTitle(String titleHint) {
    if (useSolrForIndexing) {
      return Iterables.transform(indexDocService.findInTitleOrDescription(titleHint),
          new Function<IndexableDocument, Post>() {
            // TODO(jaigupta): Not a cool implementation, set all of to be called in a single call.
            @Override
            public Post apply(IndexableDocument doc) {
              return postDAO.getOne(doc.getId());
            }
      });
    } else {
      QPost qPost = QPost.post;
      return postDAO.findAll(
          qPost.title.contains(titleHint)
              .or(qPost.data.contains(titleHint)));
    }
  }

  @Override
  public Iterable<Post> getUserPosts(long authId) {
    QPost qPost = QPost.post;
    BooleanExpression query = qPost.authId.eq(authId);
    return postDAO.findAll(query);
  }

  @Override
  public void updatePost(Post post) {
    postDAO.save(post);
  }

  @Override
  public List<Post> getRecentPosts() {
    return postDAO.findAll(
        new PageRequest(0, 20, new Sort(Direction.DESC, "timeCreated")))
        .getContent();
  }

}
