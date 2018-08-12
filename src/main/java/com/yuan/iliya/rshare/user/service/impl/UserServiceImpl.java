package com.yuan.iliya.rshare.user.service.impl;

import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.user.dao.UserDao;
import com.yuan.iliya.rshare.user.entity.User;
import com.yuan.iliya.rshare.user.entity.UserInformations;
import com.yuan.iliya.rshare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        UserInformations userInformations = new UserInformations();
        User user = new User();
        user.setId(userId);
        Information information = new Information();
        information.setId(informationId);
        userInformations.setUser(user);
        userInformations.setInformation(information);


        User user1 = userDao.findObjectById(userId);
        user1.getInformations().add(userInformations);
        userDao.update(user1);
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
        User user = userDao.findObjectById(id);
        Set<UserInformations> informations = user.getInformations();
        List<Information> informationList = new ArrayList<>();
        for (UserInformations userInformations: informations){
            informationList.add(userInformations.getInformation());
        }
        return informationList;
    }

    @Override
    public List<Information> getUserInformationsByIndex(String id, Integer index, Integer size) {

        User user = userDao.findObjectById(id);
        Set<UserInformations> informations = user.getInformations();

        if (size <= 0 || index >=  informations.size() || index < 0){
            return null;
        }
        List<Information> informationList = new ArrayList<>();

        for (UserInformations userInformations: informations){
            informationList.add(userInformations.getInformation());

        }
        Collections.sort(informationList,(information1,information2) -> {
            if (information1.getPublicity() > information2.getPublicity()){
                return 1;
            }else if (information1.getPublicity() == information2.getPublicity()){
                return 0;
            }else {
                return -1;
            }
        });
        int i = 0;

        List<Information> returnInformations = new ArrayList<>();
        for (Information information:informationList){
            if (i >= index && i < index + size){
                returnInformations.add(information);
            }
            i++;
        }
        return returnInformations;
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
