package com.yuan.iliya.rshare.user.service;

import com.yuan.iliya.rshare.user.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/7 17:27
 * @since 1.8
 */
public interface UserService {
    /**
     * 向数据库中存储指定的用户
     * @param user 用户
     */
    public void save(User user);

    /**
     * 向数据库中更新指定用户
     * @param user 指定用户
     */
    public void update(User user);

    /**
     * 根据id来删除数据库中对应的用户
     * @param id 用户id
     */
    public void delete(Serializable id);

    /**
     * 根据id查询数据中对应的单个用户
     * @param id 用户id
     */
    public User findUserById(Serializable id);

    /**
     * 查询数据库中的所有用户
     * @return 所有用户
     */
    public List<User> findUsers();
}
