package com.yuan.iliya.rshare.user.dao;

import com.yuan.iliya.rshare.core.dao.BaseDao;
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
 * @date 2018/8/7 17:11
 * @since 1.8
 */
public interface UserDao extends BaseDao<User> {


    /**
     * 根据用户id，和用户收藏的信息id，删除对应的记录
     * @param userId
     * @param informationId
     */
    public void deleteUserInformationsByInformationId(String userId, String informationId);

    /**
     * 全部删除用户信息
     * @param userId
     */
    public void deleteAllUserInformations(String userId);

    /**
     * 通过用户id查询出所有的收藏信息
     * @param id 用户id
     * @return 所有的收藏信息
     */
    public List<Information> getUserInformationsById(Serializable id);


    /**
     * 通过用户id查询出部分收藏信息
     * @param id 用户id
     * @param index 用户收藏索引
     * @param size 用户收藏一次请求多少
     * @return 所有的收藏信息
     */
    public List<Information> getUserInformationsByIndex(String id, Integer index, Integer size);

    /**
     * 添加用户收藏
     * @param userId
     * @param informationId
     */
    public void addUserInformations(String userId, String informationId);
}
