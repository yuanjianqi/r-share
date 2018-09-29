package com.yuan.iliya.rshare.book.dao;

import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.book.entity.po.Order;
import com.yuan.iliya.rshare.book.entity.po.ShoppingItem;
import com.yuan.iliya.rshare.core.dao.BaseDao;

import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/29 15:03
 * @since 1.8
 */
public interface OrderDao extends BaseDao<Order> {

    /**
     * 处理订单信息，将之保存到数据库中
     * @param order 订单信息
     * @return 处理结果
     */
    String saveOrderInfo(Order order);

    /**
     * 通过购买的书籍id来查出对应的书籍信息
     * @param shoppingItems 购买的书籍
     * @return 对应的书籍信息
     */
    List<Book> getBuyBookInfo(List<ShoppingItem> shoppingItems);

    /**
     * 进行扣钱
     * @param totalPrice 扣钱金额
     */
    void deductMoney(Long totalPrice, String userId) throws Exception;
}
