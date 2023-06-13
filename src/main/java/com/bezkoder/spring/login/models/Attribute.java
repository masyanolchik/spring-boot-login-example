package com.bezkoder.spring.login.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_attribute_group_id")
    private AttributeGroup attributeGroup;

    public Attribute(){}

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

    public Long getParentAttributeGroup() {
        return attributeGroup.getId();
    }

    public void setParentAttributeGroup(AttributeGroup parentAttributeGroup) {
        this.attributeGroup = parentAttributeGroup;
    }

    public AttributeGroup getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(AttributeGroup attributeGroup) {
        this.attributeGroup = attributeGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;
        Attribute attribute = (Attribute) o;
        return id == attribute.id && Objects.equals(name, attribute.name) && Objects.equals(attributeGroup, attribute.attributeGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, attributeGroup);
    }
}