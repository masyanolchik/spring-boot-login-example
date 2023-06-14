package com.bezkoder.spring.login.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="id")
    private int id;

    @Column(name="buyers_name")
    private String buyersName;

    @Column(name="buyers_phone")
    private String buyersPhone;

    @Column(name="shipping_address")
    private String shippingAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="current_status_id")
    private OrderStatus currentStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User userCustomer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", nullable = true)
    Set<OrderedProduct> products;

    @Column(name="order_date")
    private Date orderDate;

    public Order(){}

    public Set<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderedProduct> products) {
        this.products = products;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDateFormatted() {
        return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(orderDate);
    }

    public String getBuyersPhone() {
        return buyersPhone;
    }

    public void setBuyersPhone(String buyersPhone) {
        this.buyersPhone = buyersPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public OrderStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(OrderStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public User getUserCustomer() {
        return userCustomer;
    }

    public void setUserCustomer(User userCustomer) {
        this.userCustomer = userCustomer;
    }

    public String getProductsStringRepresentation() {
        if(products == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (OrderedProduct product: products) {
            stringBuilder.append(product.getProduct().getName());
            stringBuilder.append(";ціна:");
            stringBuilder.append(product.getProduct().getPrice());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public BigDecimal getTotalOrderPrice() {
        if(products == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal bigDecimalAccumulator = new BigDecimal(0);
        for(OrderedProduct product : products) {
            bigDecimalAccumulator = bigDecimalAccumulator.add(product.getProduct().getPrice());
        }
        return bigDecimalAccumulator;
    }
}