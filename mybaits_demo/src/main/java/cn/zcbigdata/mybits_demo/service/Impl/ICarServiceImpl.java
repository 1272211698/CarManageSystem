package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Car;
import cn.zcbigdata.mybits_demo.mapper.CarMapper;
import cn.zcbigdata.mybits_demo.service.ICarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ICarServiceImpl implements ICarService {

    @Resource
    private CarMapper carMapper;

    @Override
    public Integer addCar(Car car) {
        return this.carMapper.addCar(car);
    }

    @Override
    public Integer updateCar(Car car) {
        return this.carMapper.updateCar(car);
    }
}
