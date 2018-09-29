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
 * @date 2018/9/22 11:21
 * @since 1.8
 */
@Embeddable
public class ShoppingItem implements Serializable {

    private String id;
    private Integer buyNumber;


    @Column(name = "book_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Column(name = "book_buy_number")
    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
}
