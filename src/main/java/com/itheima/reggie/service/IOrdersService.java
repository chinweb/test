package com.itheima.reggie.service;

import com.itheima.reggie.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
public interface IOrdersService extends IService<Orders> {

    public void submit(Orders orders);
}
