package com.yuan.iliya.rshare.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuan.iliya.rshare.core.constant.WeChatConstants;
import com.yuan.iliya.rshare.core.entity.WeChatData;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public Map login(@RequestParam(value = "id",required = false)String openId, @RequestParam("code") String code, HttpSession session){
        Map map = new HashMap();

        //判断登录凭证问题
        if (StringUtils.isEmpty(code)){
            map.put("status",400);
            map.put("message","code 为空，请重新登录");
            return map;
        }

        if (openId != null){
            if (session.getAttribute(openId) != null){
                map.put("sessionKey",session.getAttribute(openId));
                return map;
            }

        }



        //1.向服务器请求获取sessionkey等信息
        CloseableHttpClient client = HttpClients.createDefault();

        //1.1拼装请求参数
        String params = "appid="+WeChatConstants.WX_APPID + "&secret="+WeChatConstants.WX_SERECT+"&js_code="
                + code + "&grant_type=" + WeChatConstants.GRANT_TYPE;
        HttpGet get = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?"+params);
        //1.2.发送请求
        String responseString = null;
        try {
            CloseableHttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            StringBuilder entityStringBuilder = new StringBuilder();
            if (entity != null){
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"), 8 * 1024);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null){
                        entityStringBuilder.append(line + "\n");
                    }
                    responseString = entityStringBuilder.toString();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(responseString)){
            logger.info("请求超时，没有获得信息");
            map.put("status",400);
            map.put("message","请求超时，没有获得信息");
            return map;
        }

        //解析Json
        ObjectMapper objectMapper = new ObjectMapper();
        WeChatData data = null;
        try {
            data = objectMapper.readValue(responseString, WeChatData.class);
            logger.info("微信返回的结果是: " + responseString);
            if (data.getErrcode() != null){
                logger.info("请求错误");
            }
        } catch (IOException e) {
            logger.info("解析出现错误");
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        session.setAttribute(uuid,data.getOpenid());
        session.setAttribute(data.getOpenid(),data.getSession_key());
        map.put("uuid",uuid);
        return map;
    }

}
