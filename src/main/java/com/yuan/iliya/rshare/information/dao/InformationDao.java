package com.yuan.iliya.rshare.information.dao;

import com.yuan.iliya.rshare.core.dao.BaseDao;
import com.yuan.iliya.rshare.information.entity.Information;

import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/9 17:13
 * @since 1.8
 */
public interface InformationDao extends BaseDao<Information> {

    /**
     * 模糊查询和分页
     * @param title
     * @param index
     * @param size
     * @return
     */
    public List<Information> findInformationsByTitle(String title,Integer index,Integer size);
}
