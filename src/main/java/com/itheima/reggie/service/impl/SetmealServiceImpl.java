package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.entity.Setmeal_dish;
import com.itheima.reggie.mapper.SetmealDao;
import com.itheima.reggie.service.ISetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.service.ISetmeal_dishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
@Service
@Slf4j

public class SetmealServiceImpl extends ServiceImpl<SetmealDao, Setmeal> implements ISetmealService {

    @Autowired
    private ISetmeal_dishService setmeal_dishService;

    @Transactional
    public void saveSetmeal(SetmealDto setmealDto) {
        this.save(setmealDto);

        List<Setmeal_dish> setmealDishes = setmealDto.getSetmealDishes();


        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmeal_dishService.saveBatch(setmealDishes);


    }

    @Transactional
    @Override
    public void removeWithDish(List<Long> ids) {

        //查询套餐状态
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        int count = this.count(queryWrapper);
        if(count > 0){
            throw new CustomException("套餐正在售卖中，不能删除");

        }

        this.removeByIds(ids);

        LambdaQueryWrapper<Setmeal_dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Setmeal_dish::getSetmealId, ids);

        setmeal_dishService.remove(lambdaQueryWrapper);

    }
}
