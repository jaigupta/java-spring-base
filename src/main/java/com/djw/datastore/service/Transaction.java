package com.djw.datastore.service;

import java.util.Collection;

import com.google.protobuf.Message;

public interface Transaction {
	public void getPB(String Key, Class<? extends Message> protoType);
	public void getPBMap(String Key, Collection<Class<? extends Message>> protoList);
	public void setPB(String Key, Class<? extends Message> protoType);
	public void setPBMap(String Key, Collection<Class<? extends Message>> protoList);
	public boolean commit();
}
