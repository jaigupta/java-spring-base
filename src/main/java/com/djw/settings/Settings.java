package com.djw.settings;

public interface Settings {
  public void set(String key, Object obj);
  public int getInt(String key);
  public boolean getBoolean(String key);
  public String getString(String key);
  public <T> T get(String key, Class<T> cl);
}
