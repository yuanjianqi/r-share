package com.yuan.iliya.rshare.information.dao.impl;

import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import com.yuan.iliya.rshare.information.dao.InformationDao;
import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.user.entity.User;
import com.yuan.iliya.rshare.user.entity.UserReleaseInformation;
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
    public void update(Information information) {
        super.update(information);

    }

    @Override
    public String save(Information information, String userId) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        session.save(information);
        UserReleaseInformation userReleaseInformation = new UserReleaseInformation(new User(userId),new Information(information.getId()));
        session.save(userReleaseInformation);
        return information.getId();
    }

    @Override
    public void saveImage(List<String> paths, String id) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Information information = session.get(Information.class,id);
        information.getImgUrls().addAll(paths);
        session.update(information);
    }

    @Override
    public List<Information> findObjectsByIndexAndSize(Integer index, Integer size) {
        Query<Information> query = HibernateUtil.getCurrentSession(getSessionFactory()).createQuery("from  Information where state = :state order by date desc ,publicity desc" ).setParameter("state",Information.INFORMATION_VALID);
        query.setFirstResult(index);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<Information> findInformationsByTitle(String title, Integer index, Integer size) {
        title = "%" + title + "%";
        Query<Information> query = HibernateUtil.getCurrentSession(getSessionFactory()).createQuery("from  Information  where  title like  :title and state = :state order by date desc ,publicity desc ").setParameter("state",Information.INFORMATION_VALID);
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
        Query<Information> query = session.createQuery("from Information where state = :state order by date desc ,publicity desc ").setParameter("state",Information.INFORMATION_VALID);
        query.setFirstResult(0);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<Information> findInformationsByClassify(String classify, String detailClassify,Integer index,Integer size) {

        if (index == null){
            index = 0;
        }
        if (size == null){
            size = Integer.MAX_VALUE;
        }
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query<Information> query = null;

        if (detailClassify == null){
            query = session.createQuery("from Information where classify = :classify and state = :state order by date desc ,publicity desc ").setParameter("state",Information.INFORMATION_VALID);
            query.setParameter("classify",classify);
        }else {
            query = session.createQuery("from Information where classify = :classify and detailClassify = :detailClassify and state = :state order by date desc ,publicity desc ").setParameter("state",Information.INFORMATION_VALID);
            query.setParameter("classify",classify);
            query.setParameter("detailClassify",detailClassify);
        }
        query.setFirstResult(index);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<Information> findObjects() {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from Information where state = :state order by date desc ,publicity desc ").setParameter("state",Information.INFORMATION_VALID);
        return query.getResultList();
    }

    @Override
    public void delete(Serializable id) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query queryforUserInformation = session.createQuery("delete from UserInformations where information.id = :id");
        queryforUserInformation.setParameter("id",id);
        queryforUserInformation.executeUpdate();

        Query queryForInformation = session.createQuery("delete from Information where id = :id");
        queryForInformation.setParameter("id",id);
        queryForInformation.executeUpdate();

        Query queryForUserReleaseInformation = session.createQuery("delete from UserReleaseInformation where information.id = :id");
        queryForUserReleaseInformation.setParameter("id",id);
        queryForUserReleaseInformation.executeUpdate();
    }
}
