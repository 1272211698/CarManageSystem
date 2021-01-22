package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.mapper.UserMapper;
import cn.zcbigdata.mybits_demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User loginSubmit(User user) {
        return this.userMapper.loginSubmit(user);
    }
}
