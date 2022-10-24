package com.itheima.reggie.mapper;

import com.itheima.reggie.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
@Mapper
public interface OrdersDao extends BaseMapper<Orders> {

}
