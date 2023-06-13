package com.bezkoder.spring.login.payload.response;

import com.bezkoder.spring.login.models.Attribute;

import java.util.Set;

public class AttributeGroupResponse {
    private int id;
    private String name;
    private Set<AttributeResponse> attributes;

    public AttributeGroupResponse(int id, String name, Set<AttributeResponse> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<AttributeResponse> getAttributes() {
        return attributes;
    }
}
