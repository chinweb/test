package com.itheima.reggie.service.impl;

import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeDao;
import com.itheima.reggie.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements IEmployeeService {

}
