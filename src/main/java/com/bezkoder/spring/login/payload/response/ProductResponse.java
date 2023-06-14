package com.bezkoder.spring.login.payload.response;

import com.bezkoder.spring.login.models.Tag;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private String status;
    private int warrantyLengthMonth;
    private int returnExchangeLength;
    private CategoryResponse category;
    private String description;
    private String titleImageSrc;
    private List<String> images;
    private List<PairAttributeWithGroup> attributes;
    private Set<Tag> additionalTags;

    public ProductResponse(int id, String name, BigDecimal price, String status, int warrantyLengthMonth, int returnExchangeLength, CategoryResponse category, String description, String titleImageSrc, List<String> images, List<PairAttributeWithGroup> attributes, Set<Tag> additionalTags) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.warrantyLengthMonth = warrantyLengthMonth;
        this.returnExchangeLength = returnExchangeLength;
        this.category = category;
        this.description = description;
        this.titleImageSrc = titleImageSrc;
        this.images = images;
        this.attributes = attributes;
        this.additionalTags = additionalTags;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public int getWarrantyLengthMonth() {
        return warrantyLengthMonth;
    }

    public int getReturnExchangeLength() {
        return returnExchangeLength;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getTitleImageSrc() {
        return titleImageSrc;
    }

    public List<String> getImages() {
        return images;
    }

    public List<PairAttributeWithGroup> getAttributes() {
        return attributes;
    }

    public Set<Tag> getAdditionalTags() {
        return additionalTags;
    }
}
