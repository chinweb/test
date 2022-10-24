package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Address_book;
import com.itheima.reggie.service.IAddress_bookService;
import com.itheima.reggie.service.ISetmeal_dishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 地址管理 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
@RestController
@RequestMapping("/addressBook")
@Slf4j
public class Address_bookController {

    @Autowired
    private IAddress_bookService addressBookService;

    /**
     * 新增
     */
    @PostMapping
    public R<Address_book> save(@RequestBody Address_book addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}", addressBook);
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * 设置默认地址
     */
    @PutMapping("default")
    public R<Address_book> setDefault(@RequestBody Address_book addressBook) {
        log.info("addressBook:{}", addressBook);
        LambdaUpdateWrapper<Address_book> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Address_book::getUserId, BaseContext.getCurrentId());
        wrapper.set(Address_book::getIsDefault, 0);
        //SQL:update address_book set is_default = 0 where user_id = ?
        addressBookService.update(wrapper);

        addressBook.setIsDefault(1);
        //SQL:update address_book set is_default = 1 where id = ?
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        Address_book addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        } else {
            return R.error("没有找到该对象");
        }
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public R<Address_book> getDefault() {
        LambdaQueryWrapper<Address_book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address_book::getUserId, BaseContext.getCurrentId());
        queryWrapper.eq(Address_book::getIsDefault, 1);

        //SQL:select * from address_book where user_id = ? and is_default = 1
        Address_book addressBook = addressBookService.getOne(queryWrapper);

        if (null == addressBook) {
            return R.error("没有找到该对象");
        } else {
            return R.success(addressBook);
        }
    }

    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public R<List<Address_book>> list(Address_book addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}", addressBook);

        //条件构造器
        LambdaQueryWrapper<Address_book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != addressBook.getUserId(), Address_book::getUserId, addressBook.getUserId());
        queryWrapper.orderByDesc(Address_book::getUpdateTime);

        //SQL:select * from address_book where user_id = ? order by update_time desc
        return R.success(addressBookService.list(queryWrapper));
    }


}

