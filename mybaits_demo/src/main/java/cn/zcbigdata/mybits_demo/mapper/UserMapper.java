package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.User;

public interface UserMapper {
    User loginSubmit(User user);//查询用户表，验证登录信息
    Integer signUpSubmit(User user);//添加新用户
}
