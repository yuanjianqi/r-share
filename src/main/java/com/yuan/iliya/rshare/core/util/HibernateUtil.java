package com.yuan.iliya.rshare.core.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/7/28 16:33
 * @since 1.8
 */
public class HibernateUtil {

    public static Session getCurrentSession(SessionFactory sessionFactory){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }


}
