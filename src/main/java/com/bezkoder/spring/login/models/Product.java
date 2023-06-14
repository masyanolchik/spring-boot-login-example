package com.bezkoder.spring.login.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    @Column(name="guarantee_length_month")
    private int guaranteeLengthMonth;

    @Column(name="return_exchange_length")
    private int returnExchangeLength;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="status_id")
    private ProductStatus productStatus;

    @Column(name="title_image_src")
    private String productImageSrc;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
            name="product_pictures",
            joinColumns = {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")}
    )
    private Set<ProductImage> images;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "product_tags",
            joinColumns = {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id")}
    )
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
            name="product_attribute_values",
            joinColumns= {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name="attribute_id")}
    )
    Set<Attribute> attributes = new HashSet<>();

    @Transient
    private MultipartFile titleImage;

    @Transient
    private Collection<MultipartFile> productImages;

    public Product() {}

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public MultipartFile getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(MultipartFile titleImage) {
        this.titleImage = titleImage;
    }

    public Collection<MultipartFile> getProductImages() {
        return productImages;
    }

    public void setProductImages(Collection<MultipartFile> productImages) {
        this.productImages = productImages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getGuaranteeLengthMonth() {
        return guaranteeLengthMonth;
    }

    public void setGuaranteeLengthMonth(int guaranteeLengthMonth) {
        this.guaranteeLengthMonth = guaranteeLengthMonth;
    }

    public int getReturnExchangeLength() {
        return returnExchangeLength;
    }

    public String getProductImageSrc() {
        return productImageSrc;
    }

    public void setProductImageSrc(String productImageSrc) {
        this.productImageSrc = productImageSrc;
    }

    public Set<ProductImage> getImages() {
        return images;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }

    public void setReturnExchangeLength(int returnExchangeLength) {
        this.returnExchangeLength = returnExchangeLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public String getAttributesStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for(Attribute attribute: attributes) {
            sb.append(attribute.getAttributeGroup().getName())
                    .append(": ")
                    .append(attribute.getName())
                    .append(";");
        }
        return sb.toString();
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", guaranteeLengthMonth=" + guaranteeLengthMonth +
                ", returnExchangeLength=" + returnExchangeLength +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", productStatus=" + productStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id && guaranteeLengthMonth == product.guaranteeLengthMonth && returnExchangeLength == product.returnExchangeLength && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(description, product.description) && Objects.equals(category, product.category) && Objects.equals(productStatus, product.productStatus) && Objects.equals(productImageSrc, product.productImageSrc) && Objects.equals(images, product.images) && Objects.equals(tags, product.tags) && Objects.equals(attributes, product.attributes) && Objects.equals(titleImage, product.titleImage) && Objects.equals(productImages, product.productImages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, guaranteeLengthMonth, returnExchangeLength, description, category, productStatus, productImageSrc, images, tags, attributes, titleImage, productImages);
    }
}
