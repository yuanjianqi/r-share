package com.yuan.iliya.rshare.user.dao.impl;

import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.user.dao.UserDao;
import com.yuan.iliya.rshare.user.entity.User;
import com.yuan.iliya.rshare.user.entity.UserInformations;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/7 17:11
 * @since 1.8
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateBaseDaoImpl<User> implements UserDao {

    @Override
    public void deleteUserInformationsByInformationId(String userId, String informationId) {
        User user = findObjectById(userId);
        Set<UserInformations> informations = user.getInformations();

        for (UserInformations userInformations: informations){
            if (userInformations.getInformation().getId().equals(informationId)){
                user.getInformations().remove(userInformations);
            }
        }
        update(user);
    }

    @Override
    public void deleteAllUserInformations(String userId) {
        User user = findObjectById(userId);
        user.getInformations().clear();
        update(user);
    }
}
