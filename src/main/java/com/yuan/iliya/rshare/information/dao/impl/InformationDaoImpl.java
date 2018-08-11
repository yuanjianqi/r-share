package com.yuan.iliya.rshare.information.dao.impl;

import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import com.yuan.iliya.rshare.information.dao.InformationDao;
import com.yuan.iliya.rshare.information.entity.Information;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

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
        Query<Information> query = HibernateUtil.getCurrentSession(getSessionFactory()).createQuery("from  Information"  + "  where  title like  :title");
        query.setParameter("title",title);
        query.setFirstResult(index);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
