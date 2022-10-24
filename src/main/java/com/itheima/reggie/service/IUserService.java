package com.itheima.reggie.service;

import com.itheima.reggie.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-10-11
 */
public interface IUserService extends IService<User> {
    //发送邮件
}



