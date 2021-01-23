package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Car;

import java.util.List;

public interface CarMapper {

    List<Car> seeCar(int page, int limit) throws Exception;

    int updataCar(Car car);

    int addCar(Car car);

    int deleteCar(int id);

    int selectCount();
}
