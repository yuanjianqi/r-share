package com.yuan.iliya.rshare.information.release.dao;

import com.yuan.iliya.rshare.core.dao.BaseDao;
import com.yuan.iliya.rshare.information.entity.Information;

import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/27 22:01
 * @since 1.8
 */
public interface InformationReleaseDao extends BaseDao<Information> {

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
