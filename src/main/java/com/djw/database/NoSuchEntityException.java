package com.djw.database;

public class NoSuchEntityException extends Exception{
  private static final long serialVersionUID = 1L;

    public NoSuchEntityException(String msg) {
      super(msg);
    }
  }