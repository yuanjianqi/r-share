package com.yuan.iliya.rshare.user.service.impl;

import com.yuan.iliya.rshare.core.constant.InformationType;
import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.information.service.InformationService;
import com.yuan.iliya.rshare.user.entity.Contact;
import com.yuan.iliya.rshare.user.entity.Location;
import com.yuan.iliya.rshare.user.entity.User;
import com.yuan.iliya.rshare.user.service.UserService;
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
 * @date 2018/8/7 17:33
 * @since 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private InformationService informationService;
    @Test
    public void save() {
        User user = new User();
        user.setUsername("李四光");
        user.setGender(true);
        user.setStudentNumber("20162611");
        user.setCredibility(10000L);

        Location location = new Location();
        location.setDomitory("A区9舍706");
        location.setCollege("光电工程学院");
        location.setSpecialty("电子科学与技术");
        user.setLocation(location);

        Contact contact = new Contact();
        contact.setEmail("1849964931@qq.com");
        contact.setMobile("15182542484");
        contact.setQqNumber("1849964931");
        user.setContact(contact);

        Information information = new Information();
        information.setClassify(InformationType.ADVICE_INFORMATION);
        information.setDate(new Date());
        information.setPublicity(28176);
        information.getImgUrls().add("http://dflkjasklfj");
        information.setTitle("老人与海");
        information.setInformationBody("我操真的傻逼");
        information.setState(Information.INFORMATION_VALID);

        informationService.save(information);


        userService.save(user);
        userService.addUserInformations(user.getId(),information.getId());

    }

//    @Test
//    public void update() {
//        User user = userService.findUserById("402836816513cdbf016513cdc33b0000");
//        user.setUsername("赵明");
//        userService.update(user);
//
//
//    }

//    @Test
//    public void delete() {
//        userService.delete("40283681651dd5c201651dd5c5260000");
//    }

    @Test
    public void findUserById() {
    }

//    @Test
//    public void findUsers() {
//        List<User> users = userService.findUsers();
//        System.out.println(users);
//    }
}