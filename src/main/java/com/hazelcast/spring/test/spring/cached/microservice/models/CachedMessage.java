package com.hazelcast.spring.test.spring.cached.microservice.models;

import lombok.Data;

@Data
public class CachedMessage {
    private String name;
    private String type;
    private String content;
}
