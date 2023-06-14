package com.bezkoder.spring.login.payload.response;

import com.bezkoder.spring.login.models.AttributeGroup;
import com.bezkoder.spring.login.models.Category;

import java.util.Set;

public class CategoryResponse {
    private int id;
    private String name;
    private CategoryResponse parentCategory;
    private Set<AttributeGroupResponse> attributeGroups;

    public CategoryResponse(int id, String name, CategoryResponse parentCategory, Set<AttributeGroupResponse> attributeGroups) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.attributeGroups = attributeGroups;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryResponse getParentCategory() {
        return parentCategory;
    }

    public Set<AttributeGroupResponse> getAttributeGroups() {
        return attributeGroups;
    }
}
