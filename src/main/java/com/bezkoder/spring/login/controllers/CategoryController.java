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

import java.util.*;

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
            CategoryResponse parentCategory = null;
            if(sourceCategory.getParentCategory() != null) {
                parentCategory = getCategoryById(sourceCategory.getParentCategory().getId());
            }
            categoryResponseList.add(new CategoryResponse(
                    Math.toIntExact(sourceCategory.getId()),
                    sourceCategory.getName(),
                    parentCategory,
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

    private CategoryResponse getCategoryById(Long id) {
        CategoryResponse categoryResponse = null;
        if(id != null) {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent()) {
                CategoryResponse parentCategory = getCategoryById(category.get().getParentCategoryId());
                Set<AttributeGroupResponse> attributeGroupSet = new HashSet<>();
                for (AttributeGroup attributeGroup: category.get().getAttributeGroups()) {
                    Set<AttributeResponse> attributes = new HashSet<>();
                    for (Attribute attr: attributeGroup.getAttributes()) {
                        attributes.add(new AttributeResponse(
                                Math.toIntExact(attr.getId()),
                                attr.getName()
                        ));
                    }
                    attributeGroupSet.add(new AttributeGroupResponse(
                            Math.toIntExact(attributeGroup.getId()),
                            attributeGroup.getName(),
                            attributes
                    ));
                }
                categoryResponse = new CategoryResponse(
                        Math.toIntExact(category.get().getId()),
                        category.get().getName(),
                        parentCategory,
                        attributeGroupSet
                );
            }
        }
        return categoryResponse;
    }
}
