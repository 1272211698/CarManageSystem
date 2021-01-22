package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.mapper.UserMapper;
import cn.zcbigdata.mybits_demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    //用户登录接口实现
    @Override
    public User loginSubmit(User user) {
        return this.userMapper.loginSubmit(user);
    }

    //用户注册接口实现
    @Override
    public Integer signUpSubmit(User user) {
        try {
            return this.userMapper.signUpSubmit(user);
        }catch (Exception e){
            //e.printStackTrace();
            return 0;
        }
    }
}
