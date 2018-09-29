package com.yuan.iliya.rshare.book.entity.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/29 14:32
 * @since 1.8
 */
@Embeddable
public class BuyerInformation implements Serializable {

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人地址
     */
    private String address;

    /**
     * 收货人电话号码
     */
    private String mobile;

    @Override
    public String toString() {
        return "BuyerInformation{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    @Column(name = "order_buyer_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "order_buyer_address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "order_buyer_mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
