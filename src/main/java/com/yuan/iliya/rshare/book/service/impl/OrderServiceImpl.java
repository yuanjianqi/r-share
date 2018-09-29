package com.yuan.iliya.rshare.book.service.impl;

import com.yuan.iliya.rshare.book.dao.OrderDao;
import com.yuan.iliya.rshare.book.entity.dto.BuyInfoDto;
import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.book.entity.po.Order;
import com.yuan.iliya.rshare.book.entity.po.ShoppingItem;
import com.yuan.iliya.rshare.book.service.OrderService;
import com.yuan.iliya.rshare.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/29 15:00
 * @since 1.8
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 处理订单的实际Service方法
     *
     * @param dto 传递进来的订单数据
     * @return 处理订单的信息
     */
    @Override
    public String dealWithOrder(BuyInfoDto dto) throws Exception{

        //将订单信息解包装，将它转换为实际的订单信息
        Order order = new Order();

        //用户信息
        User user = new User(dto.getUserId());
        order.setUser(user);

        //书籍信息
        order.setBooks(dto.getShoppingItems());

        //购买者信息
        order.setBuyerInformation(dto.getBuyerInformation());

        //其余信息
        order.setRemark(dto.getRemark());
        order.setTotalPrice(dto.getTotalPrice().multiply(BigDecimal.valueOf(100)).longValue());

        //进行校验
        //将订单总金额和实际的书籍价格总和做对比，进行校验
        List<Book> info = orderDao.getBuyBookInfo(dto.getShoppingItems());
        Long total = 0L;
        for (Book book: info){
            for (ShoppingItem shoppingItem : order.getBooks()){
                if (shoppingItem.getId() == book.getId()){
                    total += book.getNewPrice() * shoppingItem.getBuyNumber();
                }
            }
        }

        if (total.equals(order.getTotalPrice())){
            //校验成功，将订单保存到数据库中
            orderDao.saveOrderInfo(order);
            //进行相应的信誉值扣款
            orderDao.deductMoney(order.getTotalPrice(),order.getUser().getId());
            return "交易成功";
        }else {
            //校验失败，返回异常信息.
            return "发生错误,可能是订单信息被恶意用户修改";
        }

    }
}
