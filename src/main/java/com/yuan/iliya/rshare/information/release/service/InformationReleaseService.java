package com.yuan.iliya.rshare.information.release.service;

import com.yuan.iliya.rshare.information.entity.Information;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/27 22:03
 * @since 1.8
 */
public interface InformationReleaseService {

    /**
     * 向数据库中更新信息的状态
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
     * 查询指定标题内容的信息
     * @param title 信息标题
     * @return 信息集合
     */
    public List<Information> findInformationsByTitle(String title);

    /**
     * 返回指定状态的信息结合
     * @param state 状态
     * @return 信息集合
     */
    public List<Information> findInformationsByState(Boolean state);
}
