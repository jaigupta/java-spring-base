package com.djw.datastore.proto;

import java.util.Collection;

import com.google.protobuf.Message;

public interface PBStore {
	public void getPB(String Key, Class<? extends Message> protoType);
	public void getPBMap(String Key, Collection<Class<? extends Message>> protoList);
}
