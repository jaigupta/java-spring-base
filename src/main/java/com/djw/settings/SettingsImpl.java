package com.djw.settings;

import java.util.HashMap;
import java.util.Map;

public class SettingsImpl implements Settings{
  private Map<String, Object> _map;
  
  SettingsImpl() {
    _map = new HashMap<String, Object>();
  }
  
  @Override
  public void set(String key, Object value) {
    _map.put(key, value);
    
  }

  @Override
  public int getInt(String key) {
    return get(key, Integer.class);
  }

  @Override
  public boolean getBoolean(String key) {
    return get(key, Boolean.class);
  }

  @Override
  public String getString(String key) {
    return get(key, String.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(String key, Class<T> cl) {
    return (T)_map.get(key);
  }

}
