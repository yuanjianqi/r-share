package com.yuan.iliya.rshare.book.entity.po;

import com.yuan.iliya.rshare.user.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
     * 买的书id和数量
     */
    private List<ShoppingItem> books;
    /**
     * 买的人的id
     */
    private User user;

    /**
     * 购买者详细信息
     */
    private BuyerInformation buyerInformation;

    /**
     * 收货人留言
     */
    private String remark;

    /**
     * 总价格
     */
    private Long totalPrice;


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

    @ElementCollection
    public List<ShoppingItem> getBooks() {
        return books;
    }

    public void setBooks(List<ShoppingItem> books) {
        this.books = books;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Embedded
    public BuyerInformation getBuyerInformation() {
        return buyerInformation;
    }

    public void setBuyerInformation(BuyerInformation buyerInformation) {
        this.buyerInformation = buyerInformation;
    }

    @Column(name = "order_remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "totalPrice")
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
