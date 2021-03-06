package com.yuan.iliya.rshare.user.service.impl;

import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.user.dao.UserDao;
import com.yuan.iliya.rshare.user.entity.User;
import com.yuan.iliya.rshare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/7 17:30
 * @since 1.8
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUserInformations(String userId, String informationId) {
        userDao.addUserInformations(userId,informationId);
    }

    @Override
    public void deleteUserInformationsByInformationId(String userId, String[] selected) {
        if (selected == null || selected.length == 0){
            userDao.deleteAllUserInformations(userId);
            return;
        }


        for (String select:selected){
            userDao.deleteUserInformationsByInformationId(userId,select);
        }
    }

    @Override
    public void deleteUserInformationsByInformationId(String userId, String informationId) {
        userDao.deleteUserInformationsByInformationId(userId,informationId);
    }

    @Override
    public List<Information> getUserInformations(Serializable id) {
        return userDao.getUserInformationsById(id);
    }

    @Override
    public List<Information> getUserInformationsByIndex(String id, Integer index, Integer size) {
        return userDao.getUserInformationsByIndex(id,index,size);
    }

    @Override
    public void save(User user) {
        userDao.save(user);

    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Serializable id) {
        userDao.delete(id);
    }

    @Override
    public User findUserById(Serializable id) {
        return userDao.findObjectById(id);
    }

    @Override
    public List<User> findUsers() {
        return userDao.findObjects();
    }
}
