package com.bezkoder.spring.login.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="pictures")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
            name="product_pictures",
            joinColumns = {@JoinColumn(name="picture_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Product product;

    @Column(name="product_image")
    private String imageSrc;

    public ProductImage() {}

    public ProductImage(Product parentProduct, String fileName) {
        product = parentProduct;
        imageSrc = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductImage)) return false;
        ProductImage that = (ProductImage) o;
        return id == that.id && Objects.equals(product, that.product) && Objects.equals(imageSrc, that.imageSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, imageSrc);
    }
}
