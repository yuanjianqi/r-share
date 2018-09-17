package com.yuan.iliya.rshare.book.entity.po;

import com.yuan.iliya.rshare.user.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 15:51
 * @since 1.8
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 买的书id
     */
    private Book book;
    /**
     * 买的人的id
     */
    private User user;

    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话号码
     */
    private String mobileNumber;
    /**
     * 收货人地址
     */
    private String address;

    /**
     * 收货人留言
     */
    private String message;


    @Id
    @GenericGenerator(strategy = "uuid",name = "order_uuid")
    @GeneratedValue(generator = "order_uuid")
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @OneToOne
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
