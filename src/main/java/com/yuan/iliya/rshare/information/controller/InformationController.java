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

    /**
     * 获得指定id的信息
     * @param id 信息id
     * @return 信息
     */
    @GetMapping("/information/{id}")
    @ResponseBody
    public Information getInformationById(@PathVariable("id")String id){
        Information information =  informationService.findInformationById(id);
        return information;

    }

    /**
     * 添加信息
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
     * 删除对应id用户信息
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

    /**
     * 根据用户搜索的信息名选择信息
     * 并进行了分页处理
     * 需要请求参数
     * title 信息标题名，模糊查询 可选
     * index 需要的信息在所有信息里面的index 可选 默认是0
     * size 一次需要多少条信息 可选 默认是最大值
     * @return
     */
    @GetMapping("/informations-title")
    @ResponseBody
    public List<Information> getInformationByTitleAndIndexAndSize(@RequestParam(value = "title",required = false) String title,@RequestParam(value = "index",required = false)Integer index,@RequestParam(value = "size",required = false)Integer size){
        return informationService.findInformationByIndexAndSize(title, index, size);
    }

    /**
     * 获得swipper的信息
     * 需要请求参数
     * size 可选
     * 默认值是4
     * @param size
     * @return
     */
    @GetMapping("/informations/advice")
    @ResponseBody
    public List<Information> getAdviceInformationByPublictity(@RequestParam(value = "size",required = false)Integer size){
        return informationService.findAdviceInformationsByPublictity(size);
    }

    /**
     * 根据类别查询发布的信息
     * 需要请求参数
     * classify，必选
     * detailClassify 可选
     * index 不想再说啥意思,可选
     * size  同上
     * @param classify 类别名，必须是四个类别名中的一个
     * @return 信息
     */
    @GetMapping("/informations/classify")
    @ResponseBody
    public List<Information> getInformationsByClassify(@RequestParam("classify")String classify,@RequestParam(value = "detailClassify",required = false)String detailClassify,@RequestParam(value = "index",required = false)Integer index,@RequestParam(value = "size",required = false)Integer size){
        return informationService.findInformationsByClassify(classify,detailClassify,index,size);

    }





}
