package com.itheima.reggie.service;

import com.itheima.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
public interface ICategoryService extends IService<Category> {
    public void remove(Long ids);

}
