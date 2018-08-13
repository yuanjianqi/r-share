package com.yuan.iliya.rshare.information.service;

import com.yuan.iliya.rshare.information.entity.Information;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/9 18:16
 * @since 1.8
 */
public interface InformationService {

    /**
     * 向数据库中存储指定的信息
     * @param information 信息
     */
    public void save(Information information);

    /**
     * 向数据库中更新指定信息
     * @param information 指定信息
     */
    public void update(Information information);

    /**
     * 根据id来删除数据库中对应的信息
     * @param id 信息id
     */
    public void delete(Serializable id);

    /**
     * 根据id查询数据中对应的单个信息
     * @param id 信息id
     */
    public Information findInformationById(Serializable id);

    /**
     * 查询数据库中的所有信息
     * @return 所有信息
     */
    public List<Information> findInformations();

    /**
     * 分页查询和模糊查询
     * @return
     */
    public List<Information> findInformationByIndexAndSize(String title, Integer index, Integer size);

    /**
     * 根据热度查找最热门的通知
     * @param size
     * @return
     */
    public List<Information> findAdviceInformationsByPublictity(Integer size);

    /**
     * 根据类别查询发布的信息
     * @param classify 类别名，必须是四个类别名中的一个
     * @return 信息
     */
    public List<Information> findInformationsByClassify(String classify);
}
