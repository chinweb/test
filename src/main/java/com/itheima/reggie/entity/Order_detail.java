package com.itheima.reggie.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
@Data
public class Order_detail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 菜品id
     */
    private Long dishId;

    /**
     * 套餐id
     */
    private Long setmealId;

    /**
     * 口味
     */
    private String dishFlavor;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 金额
     */
    private BigDecimal amount;



}
