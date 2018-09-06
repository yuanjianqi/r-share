package com.yuan.iliya.rshare.information.release.dao.impl;

import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import com.yuan.iliya.rshare.information.dao.InformationDao;
import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.information.release.dao.InformationReleaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/27 22:02
 * @since 1.8
 */
@Repository("informationReleaseDao")
public class InformationReleaseDaoImpl extends HibernateBaseDaoImpl<Information> implements InformationReleaseDao {
    @Autowired
    private InformationDao informationDao;

    @Override
    public List<Information> findInformationsByTitle(String title) {
        title = "%" + title + "%";
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from Information where title = :title order by date desc ,publicity desc")
                .setParameter("title",title);


        return query.getResultList();
    }

    @Override
    public List<Information> findInformationsByState(Boolean state) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from Information where state = :state order by date desc ,publicity desc")
                .setParameter("state",state);
        return query.getResultList();
    }

    @Override
    public void delete(Serializable id) {
        informationDao.delete(id);
    }
}
