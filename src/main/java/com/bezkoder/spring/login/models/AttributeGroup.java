package com.bezkoder.spring.login.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="attribute_group")
public class AttributeGroup {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="related_category_id")
    private int relatedCategoryId;

    @OneToMany
    @JoinColumn(name="parent_attribute_group_id")
    private Set<Attribute> attributes;

    @Transient
    private Category relatedCategory;

    public AttributeGroup(){}

    public Category getRelatedCategory() {
        return relatedCategory;
    }

    public void setRelatedCategory(Category relatedCategory) {
        this.relatedCategory = relatedCategory;
    }

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

    public int getRelatedCategoryId() {
        return relatedCategoryId;
    }

    public void setRelatedCategoryId(int related_category_id) {
        this.relatedCategoryId = related_category_id;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Nullable
    public String getAttributesStringRepresentation() {
        if(attributes == null || attributes.isEmpty()) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(Attribute attribute: attributes) {
            stringBuilder.append(attribute.getName());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString().trim();
    }
}
