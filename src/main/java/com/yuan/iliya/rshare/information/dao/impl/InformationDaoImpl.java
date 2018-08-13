package com.yuan.iliya.rshare.information.dao.impl;

import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import com.yuan.iliya.rshare.information.dao.InformationDao;
import com.yuan.iliya.rshare.information.entity.Information;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/9 17:20
 * @since 1.8
 */
@Repository("informationDao")
public class InformationDaoImpl extends HibernateBaseDaoImpl<Information> implements InformationDao {

    @Override
    public List<Information> findInformationsByTitle(String title, Integer index, Integer size) {
        title = "%" + title + "%";
        Query<Information> query = HibernateUtil.getCurrentSession(getSessionFactory()).createQuery("from  Information"  + "  where  title like  :title order by date desc ,publicity desc ");
        query.setParameter("title",title);
        query.setFirstResult(index);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<Information> findAdviceInformationsByPublictity(Integer size) {
        if (size == null || size == 0){
            size = 4;
        }

        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query<Information> query = session.createQuery("from Information order by date desc ,publicity desc ");
        query.setFirstResult(0);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<Information> findInformationsByClassify(String classify) {

        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query<Information> query = session.createQuery("from Information where classify = :classify order by date desc ,publicity desc ");
        query.setParameter("classify",classify);

        return query.getResultList();
    }

    @Override
    public void delete(Serializable id) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("delete from UserInformations where information.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();

        Query query1 = session.createQuery("delete from Information where id = :id");
        query1.setParameter("id",id);
        query1.executeUpdate();
    }
}
