package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Car;

import java.util.List;

public interface CarMapper {
    Integer userAddCar(Car car);//前台用户新增车辆信息

    Integer userUpdateCar(Car car);//前台用户修改车辆信息

    Integer userDeleteCarById(Integer id);//前台用户删除车辆信息

    Car selectCarById(Integer id);//根据车辆id获取车辆信息

    List<Car> selectCarListByUserid(Integer userid);//根据userid获取车辆信息列表

    Integer selectUserCarCount(Integer userid);//根据userid获取车辆总数

    List<Car> selectAll(int page, int limit) throws Exception;

    int selectCount();
}
