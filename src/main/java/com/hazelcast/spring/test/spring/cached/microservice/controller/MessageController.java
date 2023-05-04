package com.hazelcast.spring.test.spring.cached.microservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.spring.test.spring.cached.microservice.interceptor.MessageInterceptor;

@RestController
@RequestMapping("/messageDump")
class MessageController {

  @Autowired
  private HazelcastInstance hazelcastInstance;

  @Cacheable("messages")
  @PostMapping("/add")
  public String addMessageToCache(@RequestParam("key")String key, @RequestParam("value")String value ){
    //some db operations
    retrieveCache().put(key, value);
    return "added " + key +" message key to cache";
  }

    @GetMapping("{id}")
    public String getById(@PathVariable("id") String key) {
      return "Message for Key : " + key + " And Message: " + retrieveCache().get(key);
    }
  
    private IMap<Object, Object> retrieveCache(){
      var cache =  hazelcastInstance.getMap("cachedMessages");
      cache.addInterceptor(new MessageInterceptor());
      return cache;
    }
}