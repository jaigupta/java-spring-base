package com.djw.indexing.solr.dao;

import java.util.List;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.djw.indexing.solr.entity.IndexableDocument;

public interface IndexableDocumentRepository extends
    SolrCrudRepository<IndexableDocument, String> {

  @Query("title:*?0* OR description:*?0*")
  public List<IndexableDocument> findInTitleOrDescription(String searchTerm);
}