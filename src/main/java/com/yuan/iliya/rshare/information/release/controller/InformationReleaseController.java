package com.yuan.iliya.rshare.information.release.controller;

import com.yuan.iliya.rshare.core.entity.Status;
import com.yuan.iliya.rshare.information.entity.Information;
import com.yuan.iliya.rshare.information.release.service.InformationReleaseService;
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
 * @date 2018/8/27 22:10
 * @since 1.8
 */
@Controller("informationReleaseController")
@RequestMapping("/r-share/release")
public class InformationReleaseController {


    @Autowired
    private InformationReleaseService informationReleaseService;

    /**
     * 查询state 为 true所有的信息
     * @return 所有的信息
     */
    @RequestMapping(value = "/informations-all",method = RequestMethod.GET)
    @ResponseBody
    public List<Information> getAllInformations(){
        return informationReleaseService.findInformations();

    }

    /**
     * 查找指定关键词的信息
     * @param title 关键词
     * @return 信息
     */
    @RequestMapping(value = "/informations-title",method = RequestMethod.GET)
    @ResponseBody
    public List<Information> getInformationsByTitle(@RequestParam("title") String title){
        return informationReleaseService.findInformationsByTitle(title);
    }

    /**
     * 查找指定状态的信息
     * @param state 状态
     * @return 信息
     */
    @RequestMapping(value = "/informations-state",method = RequestMethod.GET)
    @ResponseBody
    public List<Information> getInformationsByState(@RequestParam("state") Boolean state){
        return informationReleaseService.findInformationsByState(state);
    }

    /**
     * 删除指定id的信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/information/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Status deleteInformationById(@PathVariable("id") String id){
        Status status = new Status();
        try {
            informationReleaseService.delete(id);
            status.setStatus(Status.GOOD_STATUS);
            status.setMessage("删除成功");
        } catch (Exception e) {
            status.setStatus(Status.BAD_STATUS);
            status.setMessage("删除失败"+ e.getMessage());
        }
        return status;
    }

    /**
     * 更新信息的状态
     * @param state 状态
     * @param id id
     * @return 更新情况
     */
    @RequestMapping(value = "/inforamtion/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Status updateInformationByState(@RequestParam("state") Boolean state,@PathVariable("id") String id){
        Status status = new Status();
        try {
            Information information = informationReleaseService.findInformationById(id);
            information.setState(state);
            informationReleaseService.update(information);
            status.setStatus(Status.GOOD_STATUS);
            status.setMessage("更新成功");
        } catch (Exception e) {
            status.setStatus(Status.BAD_STATUS);
            status.setMessage("更新失败"+ e.getMessage());
        }
        return status;
    }
}
