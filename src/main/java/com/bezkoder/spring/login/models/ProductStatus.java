package com.bezkoder.spring.login.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="product_status")
public class ProductStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="id")
    private int id;

    @Column(name="status_text")
    private String statusText;

    public ProductStatus(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    @Override
    public String toString() {
        return "ProductStatus{" +
                "statusText='" + statusText + '\'' +
                '}';
    }
}