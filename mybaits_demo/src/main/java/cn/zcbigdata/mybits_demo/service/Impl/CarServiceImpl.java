package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Car;
import cn.zcbigdata.mybits_demo.mapper.CarMapper;
import cn.zcbigdata.mybits_demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Resource
    CarMapper carMapper;

    @Override
    public List<Car> seeCar(int page, int limit) throws Exception {
        int pageIndex = (page - 1) * limit;
        int pageSize = limit;
        return this.carMapper.seeCar(pageIndex,pageSize);
    }

    @Override
    public int updataCar(Car car) {
        return this.carMapper.updataCar(car);
    }

    @Override
    public int addCar(Car car) {
        return this.carMapper.addCar(car);
    }

    @Override
    public int deleteCar(int id) {
        return this.carMapper.deleteCar(id);
    }

    @Override
    public int selectCount() {
        return this.carMapper.selectCount();
    }
}
