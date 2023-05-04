package com.hazelcast.spring.test.spring.cached.microservice.interceptor;

import com.hazelcast.map.MapInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageInterceptor implements MapInterceptor {

    @Override
    public Object interceptGet(Object value) {
        return null;
    }

    @Override
    public void afterGet(Object value) {
    }

    @Override
    public Object interceptPut(Object oldValue, Object newValue) {
        return null;
    }

    @Override
    public void afterPut(Object value) {
        log.info("message: "+ value + " added to the cache");
    }

    @Override
    public Object interceptRemove(Object removedValue) {
        return null;
    }

    @Override
    public void afterRemove(Object oldValue) {
    }
    
}
