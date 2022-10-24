package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Shopping_cart;
import com.itheima.reggie.service.IShopping_cartService;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class Shopping_cartController {

    @Autowired
    private IShopping_cartService shoppingCartService;

    /**
     * 添加购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<Shopping_cart> add(@RequestBody Shopping_cart shoppingCart){
        log.info("购物车数据:{}",shoppingCart);

        //设置用户id，指定当前是哪个用户的购物车数据
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);

        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<Shopping_cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shopping_cart::getUserId,currentId);

        if(dishId != null){
            //添加到购物车的是菜品
            queryWrapper.eq(Shopping_cart::getDishId,dishId);

        }else{
            //添加到购物车的是套餐
            queryWrapper.eq(Shopping_cart::getSetmealId,shoppingCart.getSetmealId());
        }

        //查询当前菜品或者套餐是否在购物车中
        //SQL:select * from shopping_cart where user_id = ? and dish_id/setmeal_id = ?
        Shopping_cart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if(cartServiceOne != null){
            //如果已经存在，就在原来数量基础上加一
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartService.updateById(cartServiceOne);
        }else{
            //如果不存在，则添加到购物车，数量默认就是一
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return R.success(cartServiceOne);
    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    public R<List<Shopping_cart>> list(){
        log.info("查看购物车...");

        LambdaQueryWrapper<Shopping_cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shopping_cart::getUserId,BaseContext.getCurrentId());
        queryWrapper.orderByAsc(Shopping_cart::getCreateTime);

        List<Shopping_cart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }


    @PostMapping("/sub")
    public R<Shopping_cart> sub(@RequestBody Shopping_cart shoppingCart){
        log.info("购物车数据:{}",shoppingCart);

        //设置用户id，指定当前是哪个用户的购物车数据
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);

        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<Shopping_cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shopping_cart::getUserId,currentId);

        if(dishId != null){
            //添加到购物车的是菜品
            queryWrapper.eq(Shopping_cart::getDishId,dishId);

        }else{
            //添加到购物车的是套餐
            queryWrapper.eq(Shopping_cart::getSetmealId,shoppingCart.getSetmealId());
        }

        //查询当前菜品或者套餐是否在购物车中
        //SQL:select * from shopping_cart where user_id = ? and dish_id/setmeal_id = ?
        Shopping_cart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if(cartServiceOne != null){
            //如果已经存在，就在原来数量基础上加一
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number - 1);
            shoppingCartService.updateById(cartServiceOne);
        }

        return R.success(cartServiceOne);
    }



    @DeleteMapping("/clean")
    public R<String> delete(){
        LambdaQueryWrapper<Shopping_cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shopping_cart::getUserId, BaseContext.getCurrentId());

        shoppingCartService.remove(queryWrapper);

        return R.success("清空购物车成功");

    }
}

