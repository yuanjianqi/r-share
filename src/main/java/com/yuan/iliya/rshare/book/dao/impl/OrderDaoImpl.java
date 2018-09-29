package com.yuan.iliya.rshare.book.dao.impl;

import com.yuan.iliya.rshare.book.dao.OrderDao;
import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.book.entity.po.Order;
import com.yuan.iliya.rshare.book.entity.po.ShoppingItem;
import com.yuan.iliya.rshare.book.entity.po.UserOrder;
import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import com.yuan.iliya.rshare.user.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/29 15:05
 * @since 1.8
 */
@Repository("orderDao")
public class OrderDaoImpl extends HibernateBaseDaoImpl<Order> implements OrderDao {

    /**
     * 处理订单信息，将之保存到数据库中
     *
     * @param order 订单信息
     * @return 处理结果
     */
    @Override
    public String saveOrderInfo(Order order) {
        try {
            UserOrder userOrder = new UserOrder();
            userOrder.setUser(order.getUser());
            userOrder.setOrder(order);

            Session session = HibernateUtil.getCurrentSession(getSessionFactory());
            session.save(order);
            session.save(userOrder);
        } catch (Exception e) {
            return "保存失败" + e.getMessage();
        }

        return "保存成功";
    }

    /**
     * 通过购买的书籍id来查出对应的书籍信息
     *
     * @param shoppingItems 购买的书籍
     * @return 对应的书籍信息
     */
    @Override
    public List<Book> getBuyBookInfo(List<ShoppingItem> shoppingItems) {

        //拼装查询字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (ShoppingItem shoppingItem: shoppingItems){
            stringBuilder.append(shoppingItem.getId());
            stringBuilder.append(",");
        }
        String queryString = stringBuilder.toString();
        queryString = queryString.substring(0,queryString.lastIndexOf(","));

        //执行查询
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from Book where id in (:queryString)")
                .setParameter("queryString", queryString);
        return query.getResultList();
    }

    /**
     * 进行扣钱
     *
     * @param totalPrice 扣钱金额
     */
    @Override
    public void deductMoney(Long totalPrice,String userId) throws Exception{
        try {
            Session session = HibernateUtil.getCurrentSession(getSessionFactory());
            User user = (User) session.get(userId, User.class);
            if (user.getCredibility() >= totalPrice){
                user.setCredibility(user.getCredibility() - totalPrice);

            }else {
                throw new Exception("余额不足");
            }
            session.update(user);
        } catch (Exception e) {
            throw new Exception("是真的钱不够");
        }

    }
}
