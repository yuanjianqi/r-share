package com.yuan.iliya.rshare.user.service.impl;

import com.yuan.iliya.rshare.user.entity.Contract;
import com.yuan.iliya.rshare.user.entity.Location;
import com.yuan.iliya.rshare.user.entity.User;
import com.yuan.iliya.rshare.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

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
    @Test
    public void save() {
        User user = new User();
        user.setUsername("李四光");
        user.setGender(true);
        user.setStudentNumber("20162611");
        user.setCredibility(100);

        Location location = new Location();
        location.setDomitory("A区9舍706");
        location.setCollege("光电工程学院");
        location.setSpecialty("电子科学与技术");
        user.setLocation(location);

        Contract contract = new Contract();
        contract.setEmail("1849964931@qq.com");
        contract.setMobile("15182542484");
        contract.setQqNumber("1849964931");
        user.setContract(contract);
        userService.save(user);

    }

    @Test
    public void update() {
        User user = userService.findUserById("402836816513cdbf016513cdc33b0000");
        user.setUsername("赵明");
        userService.update(user);


    }

    @Test
    public void delete() {
    }

    @Test
    public void findUserById() {
    }

    @Test
    public void findUsers() {
        List<User> users = userService.findUsers();
        System.out.println(users);
    }
}