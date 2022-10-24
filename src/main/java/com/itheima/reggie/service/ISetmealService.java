package com.itheima.reggie.service;

import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
public interface ISetmealService extends IService<Setmeal> {


    public void saveSetmeal(SetmealDto setmealDto);


    public void removeWithDish(List<Long> ids);
}
