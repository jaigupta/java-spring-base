package com.djw.database;

public class DatabaseException extends Exception { 
  private static final long serialVersionUID = 42L;
  public DatabaseException(String msg) {
    super(msg);
  }
}