package com.yuan.iliya.rshare.core.dao.impl;

import com.yuan.iliya.rshare.core.dao.BaseDao;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/7/28 16:24
 * @since 1.8
 */

public class HibernateBaseDaoImpl<T> implements BaseDao<T> {

    private Class<?> clazz;
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public HibernateBaseDaoImpl() {

        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void save(T t) {
        Session session = HibernateUtil.getCurrentSession(sessionFactory);
        session.save(t);
        session.flush();

    }

    @Override
    public void update(T t) {
        Session session = HibernateUtil.getCurrentSession(sessionFactory);
        session.update(t);
        session.flush();
    }

    @Override
    public void delete(Serializable id) {
        Session session = HibernateUtil.getCurrentSession(sessionFactory);
        session.delete(findObjectById(id));
        session.flush();
    }

    @Override
    public T findObjectById(Serializable id) {
        Session session = HibernateUtil.getCurrentSession(sessionFactory);
        return (T) session.get(clazz,id);
    }

    @Override
    public List<T> findObjects() {
        Query<T> query = HibernateUtil.getCurrentSession(sessionFactory).createQuery("from  " + clazz.getSimpleName());
        return query.getResultList();
    }
}
