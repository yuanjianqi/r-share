package com.yuan.iliya.rshare.book.service;

import com.yuan.iliya.rshare.book.entity.dto.BuyInfoDto;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/29 14:59
 * @since 1.8
 */

public interface OrderService {

    /**
     * 处理订单的实际Service方法
     * @param dto 传递进来的订单数据
     * @return 处理订单的信息
     */
    String dealWithOrder(BuyInfoDto dto) throws Exception;

}
