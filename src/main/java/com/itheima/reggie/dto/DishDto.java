package com.itheima.reggie.dto;

import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Dish_flavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class DishDto extends Dish {

    private List<Dish_flavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
