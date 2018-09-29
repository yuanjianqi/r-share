package com.yuan.iliya.rshare.book.controller;

import com.yuan.iliya.rshare.book.entity.dto.BuyInfoDto;
import com.yuan.iliya.rshare.book.service.OrderService;
import com.yuan.iliya.rshare.core.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/29 14:52
 * @since 1.8
 */
@Controller("orderController")
@RestController
@RequestMapping("/r-share")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/buy")
    public Status buyBook(BuyInfoDto dto){
        Status status = new Status();

        try {
            String deal = orderService.dealWithOrder(dto);
            status.setStatus("200");
            status.setMessage(deal);
        }catch (Exception e){
            status.setStatus("500");
            status.setMessage("发生未知错误，订单处理失败");
        }

        return status;

    }
}
