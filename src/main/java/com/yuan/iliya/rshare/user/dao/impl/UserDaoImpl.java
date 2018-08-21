package com.yuan.iliya.rshare.user.dao.impl;

import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.user.dao.UserDao;
import com.yuan.iliya.rshare.user.entity.User;
import com.yuan.iliya.rshare.user.entity.UserInformations;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
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
@Repository("userDao")
public class UserDaoImpl extends HibernateBaseDaoImpl<User> implements UserDao {

    @Override
    public void deleteUserInformationsByInformationId(String userId, String informationId) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        org.hibernate.query.Query query = session.createQuery("delete from UserInformations where :id =  user.id and :informationId = information.id");
        query.setParameter("informationId",informationId);
        query.setParameter("id",userId);
        query.executeUpdate();
    }

    @Override
    public void deleteAllUserInformations(String userId) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        org.hibernate.query.Query query = session.createQuery("delete from UserInformations where :id =  user.id");
        query.setParameter("id",userId);
        query.executeUpdate();

    }

    @Override
    public List<Information> getUserInformationsById(Serializable id) {

        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from UserInformations where user.id = :id and information.state = :state order by information.date desc ,information.publicity desc ").setParameter("state",Information.INFORMATION_VALID);
        query.setParameter("id",id);
        List<UserInformations> list = query.getResultList();
        List<Information> informations = new ArrayList<>();
        for (UserInformations userInformations:list){
            informations.add(userInformations.getInformation());
        }
        return informations;
    }

    @Override
    public List<Information> getUserInformationsByIndex(String id, Integer index, Integer size) {
        if (index == null || index < 0){
            index = 0;
        }

        if (size == null || size < 0){
            size = Integer.MAX_VALUE;
        }
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from UserInformations where user.id = :id and information.state = :state order by information.date desc ,information.publicity desc").setParameter("state",Information.INFORMATION_VALID);
        query.setParameter("id",id);
        query.setFirstResult(index);
        query.setMaxResults(size);

        List<UserInformations> list = query.getResultList();
        List<Information> informations = new ArrayList<>();
        for (UserInformations userInformations:list){
            informations.add(userInformations.getInformation());
        }
        return informations;
    }

    @Override
    public void addUserInformations(String userId, String informationId) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        UserInformations userInformations = new UserInformations(new User(userId),new Information(informationId));
        session.save(userInformations);
        session.flush();
    }
}
