package com.yuan.iliya.rshare.information.service.impl;

import com.yuan.iliya.rshare.information.service.InformationService;
import com.yuan.iliya.rshare.information.dao.InformationDao;
import com.yuan.iliya.rshare.information.entity.Information;
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
 * @date 2018/8/9 18:18
 * @since 1.8
 */
@Service("informationService")
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationDao informationDao;

    @Override
    public void save(Information information) {
        informationDao.save(information);

    }

    @Override
    public void update(Information information) {
        informationDao.update(information);
    }

    @Override
    public void delete(Serializable id) {
        informationDao.delete(id);
    }

    @Override
    public Information findInformationById(Serializable id) {
        return informationDao.findObjectById(id);
    }

    @Override
    public List<Information> findInformations() {
        return informationDao.findObjects();
    }
}
