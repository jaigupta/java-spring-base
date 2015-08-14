package com.djw.entity;

import javax.persistence.Embeddable;

@Embeddable
// Identifiable Entity
public class IEntity {
	public static final int MASK = -1;
  public enum EntityType {
    ENTITY_USER,
    ENTITY_POST,
    ENTITY_COMMENT,
  }

  private Long id;
	public long getId() {
	  return id;
  }
	public IEntity setId(long id) {
	  this.id = id;
	  return this;
  }
  
  public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!this.getClass().equals(obj.getClass())) return false;
      
      IEntity obj2 = (IEntity)obj;

      if (this.getId() == obj2.getId()) {
          return true;
      }
      return false;
  }

  public int hashCode() {
      return (int) (id & MASK);
  }
}
