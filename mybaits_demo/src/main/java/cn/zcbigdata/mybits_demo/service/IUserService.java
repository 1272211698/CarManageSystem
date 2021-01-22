package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.User;

public interface IUserService {
    User loginSubmit(User user);//用户登录
    Integer signUpSubmit(User user);//用户注册
}
