package com.yuan.iliya.rshare.information.release.service.impl;

import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.information.release.dao.InformationReleaseDao;
import com.yuan.iliya.rshare.information.release.service.InformationReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/27 22:04
 * @since 1.8
 */
@Service("informationReleaseService")
public class InformationReleaseServiceImpl implements InformationReleaseService {

    @Autowired
    private InformationReleaseDao informationReleaseDao;

    @Override
    public void update(Information information) {
        informationReleaseDao.update(information);


    }

    @Override
    public void delete(Serializable id) {
        informationReleaseDao.delete(id);
    }

    @Override
    public Information findInformationById(Serializable id) {
        return informationReleaseDao.findObjectById(id);
    }

    @Override
    public List<Information> findInformations() {
        return informationReleaseDao.findObjects();
    }

    @Override
    public List<Information> findInformationsByTitle(String title) {
        return informationReleaseDao.findInformationsByTitle(title);
    }

    @Override
    public List<Information> findInformationsByState(Boolean state) {
        return informationReleaseDao.findInformationsByState(state);
    }
}
