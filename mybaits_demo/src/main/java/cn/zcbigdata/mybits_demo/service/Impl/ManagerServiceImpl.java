package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Manager;
import cn.zcbigdata.mybits_demo.mapper.ManagerMapper;
import cn.zcbigdata.mybits_demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Override
    public Manager find(String username) {
        return managerMapper.find(username);
    }
}
