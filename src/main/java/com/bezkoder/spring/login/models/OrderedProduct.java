package com.bezkoder.spring.login.models;

import javax.persistence.*;

@Entity
@Table(name="order_products")
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @Column(name="id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_id", nullable = true)
    private Integer orderId;

    @Column(name="product_quantity")
    private Integer quantity;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
