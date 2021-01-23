package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.User;

public interface IUserService {
    User loginSubmit(User user);
    Integer signUpSubmit(User user);
}
