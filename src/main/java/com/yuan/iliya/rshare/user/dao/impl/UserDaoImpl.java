package com.yuan.iliya.rshare.user.dao.impl;

import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import com.yuan.iliya.rshare.user.dao.UserDao;
import com.yuan.iliya.rshare.user.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        User user = session.get(User.class,userId);
        org.hibernate.query.Query query = session.createQuery("delete from UserInformations where :id =  user.id and :informationId = information.id");
        query.setParameter("informationId",informationId);
        query.setParameter("id",user.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteAllUserInformations(String userId) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        User user = session.get(User.class,userId);
        org.hibernate.query.Query query = session.createQuery("delete from UserInformations where :id =  user.id");
        query.setParameter("id",user.getId());
        query.executeUpdate();

    }
}
