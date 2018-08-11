package com.yuan.iliya.rshare.user.controller;

import com.yuan.iliya.rshare.core.entity.Status;
import com.yuan.iliya.rshare.information.entity.Information;
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
        //清空用户中收藏的信息列表，避免一次传输数据量过大
        user.getInformations().clear();
        return user;
    }

    /**
     * 通过用户id查询出所有的收藏信息
     * @param id 用户id
     * @return 所有的收藏信息
     */
    @GetMapping("/user/{id}/informations")
    @ResponseBody
    public List<Information> getUserInformations(@PathVariable("id") String id){

        return userService.getUserInformations(id);
    }

    /**
     * 通过用户id查询出部分收藏信息
     * @param id 用户id
     * @param index 用户收藏索引
     * @param size 用户收藏一次请求多少
     * @return 所有的收藏信息
     */
    @GetMapping("/user/{id}/informations/{index}")
    @ResponseBody
    public List<Information> getUserInformationsByIndex(@PathVariable("id") String id,@PathVariable("index") Integer index,@RequestParam(value = "size",required = false) Integer size){

        System.out.println(size);
        return userService.getUserInformationsByIndex(id,index,size);
    }

    /**
     * 根据用户id，和用户收藏的信息id，删除对应的单个记录
     * @param userId
     * @param informationId
     */
    @DeleteMapping("/user/{userId}/information/{informationId}")
    @ResponseBody
    public Status deleteUserInformationsByInformationId(@PathVariable("userId")String userId,@PathVariable("informationId")String informationId){

        Status status = new Status();
        try {
            userService.deleteUserInformationsByInformationId(userId,informationId);
            status.setStatus(Status.GOOD_STATUS);
            status.setMessage("删除成功");
        } catch (Exception e) {
            status.setStatus(Status.BAD_STATUS);
            status.setMessage("删除失败"+ e.getMessage());
        }
        return status;
    }

    /**
     * 根据用户id和信息id批量删除收藏的信息记录
     * 如果没有请求参数则代表全部删除
     * @param userId
     */
    @DeleteMapping("/user/{userId}/informations")
    @ResponseBody
    public Status deleteUserInformationsByInformationId(@PathVariable("userId")String userId,@RequestParam(value = "selected[]",required = false)String[] selected){
        Status status = new Status();

        try {
            userService.deleteUserInformationsByInformationId(userId,selected);
            status.setStatus(Status.GOOD_STATUS);
            status.setMessage("删除成功");
        } catch (Exception e) {
            status.setStatus(Status.BAD_STATUS);
            status.setMessage("删除失败"+ e.getMessage());
        }
        return status;
    }

    /**
     * 添加用户收藏
     * @param userId
     * @param informationId
     * @return
     */
    @PutMapping("/user/{userId}/information/{informationId}")
    @ResponseBody
    public Status addUserInformations(@PathVariable("userId")String userId,@PathVariable("informationId")String informationId){

        Status status = new Status();
        try {
            userService.addUserInformations(userId,informationId);
            status.setStatus(Status.GOOD_STATUS);
            status.setMessage("添加成功");
        } catch (Exception e) {
            status.setStatus(Status.BAD_STATUS);
            status.setMessage("添加失败"+ e.getMessage());
        }
        return status;

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
            User userBefore = userService.findUserById(user.getId());

            userBefore.setUsername(user.getUsername());
            userBefore.setLocation(user.getLocation());
            userBefore.setStudentNumber(user.getStudentNumber());
            userBefore.setCredibility(user.getCredibility());
            userBefore.setGender(user.getGender());
            userBefore.setContact(user.getContact());

            userService.update(userBefore);
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
        for (User user:users){
            user.getInformations().clear();
        }
        return users;
    }



}
