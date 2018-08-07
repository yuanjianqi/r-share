package com.yuan.iliya.rshare.user.controller;

import com.yuan.iliya.rshare.core.entity.Status;
import com.yuan.iliya.rshare.user.entity.User;
import com.yuan.iliya.rshare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/7 17:31
 * @since 1.8
 */
@Controller("userController")
@RequestMapping("/r-share")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     * @param user user的json
     * @return 状态数据
     */
    @PostMapping("/user")
    @ResponseBody
    public Status addUser(@RequestBody(required = true) User user){
        Status status = new Status();
        try {
            userService.save(user);
            status.setStatus(Status.GOOD_STATUS);
            status.setMessage("添加成功");
        } catch (Exception e) {
            status.setStatus(Status.BAD_STATUS);
            status.setMessage("添加失败"+ e.getMessage());
        }
        return status;

    }

    /**
     * 获得指定id的用户
     * @param id id
     * @return 用户信息
     */
    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable String id){
        User user = userService.findUserById(id);
        return user;
    }

    /**
     * 更新用户信息
     * @param user 用户
     * @return 状态
     */
    @PutMapping("/user/{id}")
    @ResponseBody
    public Status updateUser(@RequestBody User user){

        Status status = new Status();
        try {
            userService.update(user);
            status.setStatus(Status.GOOD_STATUS);
            status.setMessage("修改成功");
        } catch (Exception e) {
            status.setStatus(Status.BAD_STATUS);
            status.setMessage("修改失败"+ e.getMessage());
        }
        return status;
    }

    /**
     * 删除用户信息
     * @param id 用户id
     * @return 状态码
     */
    @DeleteMapping("/user/{id}")
    @ResponseBody
    public Status deleteUser(@PathVariable String id){

        Status status = new Status();
        try {
            userService.delete(id);
            status.setStatus(Status.GOOD_STATUS);
            status.setMessage("删除成功");
        } catch (Exception e) {
            status.setStatus(Status.BAD_STATUS);
            status.setMessage("删除失败"+ e.getMessage());
        }
        return status;
    }

    /**
     * 返回所有用户信息
     * @return 用户信息
     */
    @GetMapping("/users")
    @ResponseBody
    public List<User> getUsers(){
        List<User> users = userService.findUsers();
        return users;
    }

}
