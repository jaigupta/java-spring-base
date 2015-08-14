package com.djw.indexing.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import com.djw.entity.IEntity.EntityType;

public class IndexableDocument {

  private static final String ID_SEPERATOR = ".";

  @Id
  @Field
  private String id;

  @Field
  private String description;

  @Field
  private String title;

  @Field
  private long entityType;

  public String getTitle() {
    return title;
  }

  public long getId() {
    return Long.parseLong(this.id.split(ID_SEPERATOR)[1]);
  }

  public String getDescription() {
    return description;
  }

  public IndexableDocument() {

  }

  public void getEntityType() {
    
  }

  public static Builder getBuilder(EntityType entityType, Long id, String title) {
    return new Builder(entityType, id, title);
  }

  public static class Builder {
    private IndexableDocument build;

    public Builder(EntityType entityType, Long id, String title) {
      build = new IndexableDocument();
      build.id = createId(entityType, id);
      build.entityType = entityType.ordinal();
      build.title = title;
    }

    public Builder description(String description) {
      build.description = description;
      return this;
    }

    public IndexableDocument build() {
      return build;
    }
  }

  public static String createId(EntityType entityType, Long id) {
    return entityType.toString() + ID_SEPERATOR + id.toString();
  }
}
