package com.yuan.iliya.rshare.information.dao.impl;

import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.information.dao.InformationDao;
import com.yuan.iliya.rshare.information.entity.Information;
import org.springframework.stereotype.Repository;

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
}
