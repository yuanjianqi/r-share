package com.yuan.iliya.rshare.book.entity.po;

import com.yuan.iliya.rshare.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
@Table(name = "tb_user_order")
public class UserOrder implements Serializable {

    private User user;
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserOrder userOrder = (UserOrder) o;
        return Objects.equals(getUser(), userOrder.getUser()) &&
                Objects.equals(getOrder(), userOrder.getOrder());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUser(), getOrder());
    }

    @ManyToOne
    @Id
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @Id
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
