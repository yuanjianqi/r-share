package com.yuan.iliya.rshare.book.entity.dto;

import com.yuan.iliya.rshare.book.entity.po.BuyerInformation;
import com.yuan.iliya.rshare.book.entity.po.ShoppingItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/29 14:55
 * @since 1.8
 */
public class BuyInfoDto {
    private String userId;
    private BuyerInformation buyerInformation;
    private List<ShoppingItem> shoppingItems;
    private String remark;
    private BigDecimal totalPrice;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BuyerInformation getBuyerInformation() {
        return buyerInformation;
    }

    public void setBuyerInformation(BuyerInformation buyerInformation) {
        this.buyerInformation = buyerInformation;
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
