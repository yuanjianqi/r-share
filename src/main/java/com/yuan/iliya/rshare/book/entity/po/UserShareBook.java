package com.yuan.iliya.rshare.book.entity.po;

import com.yuan.iliya.rshare.user.entity.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 * 用户共享的书
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 15:47
 * @since 1.8
 */

@Entity
@Table(name = "tb_user_share_book")
public class UserShareBook implements Serializable {

    private User user;
    private Book book;

    public UserShareBook() {
    }

    public UserShareBook(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    @Override
    public String toString() {
        return "UserBuyBook{" +
                "user=" + user +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserShareBook that = (UserShareBook) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getBook(), that.getBook());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUser(), getBook());
    }

    @ManyToOne
    @Id
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @Id
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


}
