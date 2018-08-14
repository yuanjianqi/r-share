package com.yuan.iliya.rshare.information.service.impl;

import com.yuan.iliya.rshare.information.dao.InformationDao;
import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.information.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collections;
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
    public List<Information> findInformationsByClassify(String classify,String detailClassify,Integer index,Integer size) {
        return informationDao.findInformationsByClassify(classify,detailClassify,index,size);
    }

    @Override
    public List<Information> findInformationByIndexAndSize(String title, Integer index, Integer size) {
        if (index == null){
            index = 0;
        }
        if (size == null){
            size = Integer.MAX_VALUE;
        }
        if (StringUtils.isEmpty(title)){
            if (index < 0 || size <= 0){
                return null;
            }
            return informationDao.findObjectsByIndexAndSize(index,size);
        }



        return informationDao.findInformationsByTitle(title, index, size);
    }

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
        List<Information> informationList = informationDao.findObjects();
        Collections.sort(informationList,(information1,information2) -> {
            if (information1.getPublicity() > information2.getPublicity()){
                return -1;
            }else if (information1.getPublicity().equals(information2.getPublicity())){
                return 0;
            }else {
                return 1;
            }
        });
        return informationList;
    }

    @Override
    public List<Information> findAdviceInformationsByPublictity(Integer size) {
        return informationDao.findAdviceInformationsByPublictity(size);
    }
}
