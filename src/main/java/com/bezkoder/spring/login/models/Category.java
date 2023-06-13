package com.bezkoder.spring.login.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="parent_category")
    private Long parentCategoryId;

    @OneToMany
    @JoinColumn(name="related_category_id")
    private Set<AttributeGroup> attributeGroups;

    @Transient
    private Category parentCategory;

    public Category() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public Set<AttributeGroup> getAttributeGroups() {
        return attributeGroups;
    }

    public void setAttributeGroups(Set<AttributeGroup> attributes) {
        this.attributeGroups = attributes;
    }

    @Nullable
    public String getAttributeGroupsStringRepresentation() {
        if(attributeGroups == null || attributeGroups.isEmpty()) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(AttributeGroup attribute: attributeGroups) {
            stringBuilder.append(attribute.getName());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString().trim();
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
