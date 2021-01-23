package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Car;

import java.util.List;

public interface ICarService {
    Integer userAddCar(Car car);//前台用户新增车辆
    Integer userUpdateCar(Car car);//前台用户更新车辆信息
    Integer UserDeleteCarById(Integer carid, Integer userid);//前台用户删除车辆
    List<Car> selectCarListByUserid(Integer userid);//前台用户获取车辆信息列表
    Integer selectUserCarCount(Integer userid);//前台获取用户车辆总数
    List<Car> seeCar(int page, int limit) throws Exception;

    int updataCar(Car car);

    int addCar(Car car);

    int deleteCar(int id);

    int selectCount();
}
