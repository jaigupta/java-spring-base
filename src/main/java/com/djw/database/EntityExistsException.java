package com.djw.database;

public class EntityExistsException extends Exception {
  private static final long serialVersionUID = 1L;

  public EntityExistsException(String msg) {
    super(msg);
  }
}