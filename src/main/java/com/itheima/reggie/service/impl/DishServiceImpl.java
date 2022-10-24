package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Dish_flavor;
import com.itheima.reggie.mapper.DishDao;
import com.itheima.reggie.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.service.IDish_flavorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishDao, Dish> implements IDishService {

    @Autowired
    private IDish_flavorService dish_flavorService;


    @Transactional
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);

        Long dishId = dishDto.getId();

        List<Dish_flavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        dish_flavorService.saveBatch(flavors);



    }


    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        LambdaQueryWrapper<Dish_flavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish_flavor::getDishId, dish.getId());
        List<Dish_flavor> flavors = dish_flavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto ;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);

        LambdaQueryWrapper<Dish_flavor> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Dish_flavor::getDishId, dishDto.getId());


        dish_flavorService.remove(queryWrapper);

        List<Dish_flavor> flavors = dishDto.getFlavors();

        flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dish_flavorService.saveBatch(flavors);


    }


    //删除菜品
    @Override
    public void remove(Long ids) {
        super.removeById(ids);
    }

    @Override
    public void update(Long ids) {

    }


}
