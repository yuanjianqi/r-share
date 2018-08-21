package com.yuan.iliya.rshare.information.service.impl;

import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.information.service.InformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/9 18:22
 * @since 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class InformationServiceImplTest {

    @Autowired
    private InformationService informationService;
    @Test
    public void save() {

        Information information = new Information();
        information.setClassify("讲座培训");
        information.setDate(new Date());
        information.setPublicity(28176);
        information.getImgUrls().add("http:/ssdfsaf");
        information.setDetailClassify("游戏");
        information.setTitle("老人与海");
        information.setState(Information.INFORMATION_VALID);

        informationService.save(information);

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findInformationById() {
    }

    @Test
    public void findInformations() {
    }
}