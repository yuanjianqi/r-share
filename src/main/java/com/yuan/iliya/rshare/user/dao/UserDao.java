package com.yuan.iliya.rshare.user.dao;

import com.yuan.iliya.rshare.core.dao.BaseDao;
import com.yuan.iliya.rshare.user.entity.User;

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
}
