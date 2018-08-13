package com.yuan.iliya.rshare.user.service;

import com.yuan.iliya.rshare.information.entity.Information;
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

    /**
     * 通过用户id查询出所有的收藏信息
     * @param id 用户id
     * @return 所有的收藏信息
     */
    public List<Information> getUserInformations(Serializable id);


    /**
     * 通过用户id查询出部分收藏信息
     * @param id 用户id
     * @param index 用户收藏索引
     * @param size 用户收藏一次请求多少
     * @return 所有的收藏信息
     */
    public List<Information> getUserInformationsByIndex( String id,  Integer index, Integer size);

    /**
     * 根据用户id，和用户收藏的信息id，删除对应的记录
     * @param userId
     * @param informationId
     */
    public void deleteUserInformationsByInformationId(String userId, String informationId);


    /**
     * 根据用户id和信息id批量删除收藏的信息记录
     * 如果没有请求参数则代表全部删除
     * @param userId
     */
    public void deleteUserInformationsByInformationId(String userId,String[] selected);

    /**
     * 添加用户收藏
     * @param userId
     * @param informationId
     */
    public void addUserInformations(String userId, String informationId);


}
