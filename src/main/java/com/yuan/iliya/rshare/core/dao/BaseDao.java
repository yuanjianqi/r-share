package com.yuan.iliya.rshare.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 * 基础的DAO方法接口。
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/7/28 16:20
 * @since 1.8
 */
public interface BaseDao<T> {

    /**
     * 向数据库中存储指定的实体
     * @param t 实体
     */
    public void save(T t);

    /**
     * 向数据库中更新指定实体
     * @param t 指定实体
     */
    public void update(T t);

    /**
     * 根据id来删除数据库中对应的实体
     * @param id 实体id
     */
    public void delete(Serializable id);

    /**
     * 根据id查询数据中对应的单个实体
     * @param id 实体id
     */
    public T findObjectById(Serializable id);

    /**
     * 查询数据库中的所有实体
     * @return 所有实体
     */
    public List<T> findObjects();

    /**
     * 分页操作
     * @param index
     * @param size
     * @return
     */
    public List<T> findObjectsByIndexAndSize(Integer index,Integer size);
}
