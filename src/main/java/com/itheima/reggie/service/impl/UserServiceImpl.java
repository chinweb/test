package com.itheima.reggie.service.impl;

import com.itheima.reggie.entity.User;
import com.itheima.reggie.mapper.UserDao;
import com.itheima.reggie.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    //把yml配置的邮箱号赋值到from
    @Value("${spring.mail.username}")
    private String from;
    //发送邮件需要的对象
    @Autowired
    private JavaMailSender javaMailSender;

    //邮件发送人


}
