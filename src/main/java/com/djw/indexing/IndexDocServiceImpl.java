package com.djw.indexing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djw.indexing.solr.dao.IndexableDocumentRepository;
import com.djw.indexing.solr.entity.IndexableDocument;

@Service
public class IndexDocServiceImpl implements IndexDocService {

  // TODO(jaigupta): This is not being injected fix this; Bean creation fails.
  @Autowired(required=false) private IndexableDocumentRepository repository = null;

  @Transactional
  @Override
  public void addToIndex(IndexableDocument document) {
    repository.save(document);
  }

  @Transactional
  @Override
  public void deleteFromIndex(String id) {
    repository.delete(id);
  }

  @Transactional
  @Override
  public List<IndexableDocument> findInTitleOrDescription(String searchTerm) {
    return repository.findInTitleOrDescription(searchTerm);
  }
}