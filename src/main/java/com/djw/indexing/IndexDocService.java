package com.djw.indexing;

import java.util.List;

import com.djw.indexing.solr.entity.IndexableDocument;

public interface IndexDocService {

	public void addToIndex(IndexableDocument todoEntry);

	public void deleteFromIndex(String id);

	public List<IndexableDocument> findInTitleOrDescription(String searchTerm);
}
