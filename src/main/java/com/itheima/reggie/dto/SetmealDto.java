package com.itheima.reggie.dto;

import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.entity.Setmeal_dish;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<Setmeal_dish> setmealDishes = new ArrayList<>();

    private String categoryName;
}
