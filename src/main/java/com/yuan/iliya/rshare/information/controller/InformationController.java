package com.yuan.iliya.rshare.information.controller;

import com.yuan.iliya.rshare.core.entity.Status;
import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.information.service.InformationService;
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
 * @date 2018/8/9 17:21
 * @since 1.8
 */
@Controller
@RequestMapping("/r-share")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping("/information/{id}")
    @ResponseBody
    public Information getInformationById(@PathVariable("id")String id){
        Information information =  informationService.findInformationById(id);
        return information;

    }

    /**
     * 添加用户
     * @param information information的json
     * @return 状态数据
     */
    @PostMapping("/information")
    @ResponseBody
    public Status addInformation(@RequestBody(required = true) Information information){
        Status status = new Status();
        try {
            informationService.save(information);
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
     * @param information xinxi
     * @return 状态
     */
    @PutMapping("/information/{id}")
    @ResponseBody
    public Status updateInformation(@RequestBody Information information){

        Status status = new Status();
        try {
            informationService.update(information);
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
    @DeleteMapping("/information/{id}")
    @ResponseBody
    public Status deleteInformation(@PathVariable String id){

        Status status = new Status();
        try {
            informationService.delete(id);
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
    @GetMapping("/informations")
    @ResponseBody
    public List<Information> getInformations(){
        List<Information> informations = informationService.findInformations();
        return informations;
    }


}
