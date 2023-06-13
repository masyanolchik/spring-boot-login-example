package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.Attribute;
import com.bezkoder.spring.login.models.AttributeGroup;
import com.bezkoder.spring.login.models.Category;
import com.bezkoder.spring.login.payload.response.AttributeGroupResponse;
import com.bezkoder.spring.login.payload.response.AttributeResponse;
import com.bezkoder.spring.login.payload.response.CategoryResponse;
import com.bezkoder.spring.login.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllCategories() {
        Iterable<Category> sourceCategories = getCategories();
        ArrayList<CategoryResponse> categoryResponseList = new ArrayList<>();
        for (Category sourceCategory: sourceCategories) {
            Iterable<AttributeGroup> attributeGroups = sourceCategory.getAttributeGroups();
            HashSet<AttributeGroupResponse> attributeGroupResponseSet = new HashSet<>();
            for (AttributeGroup attributeGroup: attributeGroups) {
                HashSet<AttributeResponse> attributeResponseSet = new HashSet<>();
                for (Attribute attribute: attributeGroup.getAttributes()) {
                    attributeResponseSet.add(new AttributeResponse(
                            Math.toIntExact(attribute.getId()),
                            attribute.getName()
                    ));
                }
                attributeGroupResponseSet.add(new AttributeGroupResponse(
                        Math.toIntExact(attributeGroup.getId()),
                     attributeGroup.getName(),
                     attributeResponseSet
                ));
            }
            categoryResponseList.add(new CategoryResponse(
                    Math.toIntExact(sourceCategory.getId()),
                    sourceCategory.getName(),
                    sourceCategory.getParentCategory(),
                    attributeGroupResponseSet
            ));
        }
        return ResponseEntity.ok().body(categoryResponseList);
    }

    private Iterable<Category> getCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        for(Category category: categories) {
            if(category.getParentCategoryId() != null) {
                Optional<Category> parentCategory = categoryRepository.findById(category.getParentCategoryId());
                parentCategory.ifPresent(category::setParentCategory);
            }
        }
        return categories;
    }
}
