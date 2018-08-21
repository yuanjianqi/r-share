package com.yuan.iliya.rshare.information.service.impl;

import com.yuan.iliya.rshare.information.dao.InformationDao;
import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.information.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public String save(Information information, String userId) {
        return informationDao.save(information,userId);
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
        return informationList;
    }

    @Override
    public List<Information> findAdviceInformationsByPublictity(Integer size) {
        return informationDao.findAdviceInformationsByPublictity(size);
    }

    @Override
    public void saveImage(MultipartFile[] files, String path, String id) {
        List<String> paths = new ArrayList<>();
        String filePath = null;
        String fileName = null;
        try {
            for (MultipartFile file : files){
                fileName = UUID.randomUUID().toString().replaceAll("-","") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                filePath = path + File.separator + fileName;
                file.transferTo(new File(filePath));
                paths.add("/upload/informationimage/" + fileName);
            }
            informationDao.saveImage(paths,id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
