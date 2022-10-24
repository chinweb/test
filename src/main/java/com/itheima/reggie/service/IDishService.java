package com.itheima.reggie.service;

import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
public interface IDishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    //根据ID查询菜品信息和口味信息
    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);

    public void remove(Long ids);

    public void update(Long ids);
}
