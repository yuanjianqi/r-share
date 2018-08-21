package com.yuan.iliya.rshare.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuan.iliya.rshare.core.constant.WeChatConstants;
import com.yuan.iliya.rshare.core.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/15 17:27
 * @since 1.8
 */
@Controller
@RequestMapping("/r-share")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    @ResponseBody
    public Map login(@RequestParam(value = "id",required = false)String userId, @RequestParam("code") String code){
        Map map = new HashMap();

        //判断登录凭证问题
        if (StringUtils.isEmpty(code)){
            map.put("status",400);
            map.put("message","code 为空，请重新登录");
            return map;
        }

        //1.向服务器请求获取sessionkey等信息
        //1.1拼装请求参数
        String params = "appid="+WeChatConstants.WX_APPID +"&secret="+WeChatConstants.WX_SERECT+"&js_code="+code+"&grant_type="+ WeChatConstants.GRANT_TYPE;
        //1.2.发送请求
        String response = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session",params);
        logger.info("微信返回的结果是: " + response);

        if (StringUtils.isEmpty(response)){
            logger.info("请求超时，没有获得信息");
            map.put("status",400);
            map.put("message","请求超时，没有获得信息");
            return map;
        }
        //1.3解析响应内容
        ObjectMapper objectMapper = new ObjectMapper();





        return null;
    }

}
