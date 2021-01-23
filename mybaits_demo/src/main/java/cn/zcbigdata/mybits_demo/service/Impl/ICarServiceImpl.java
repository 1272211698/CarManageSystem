package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Car;
import cn.zcbigdata.mybits_demo.mapper.CarMapper;
import cn.zcbigdata.mybits_demo.service.ICarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ICarServiceImpl implements ICarService {

    @Resource
    private CarMapper carMapper;

    //前台用户新增车辆接口实现
    @Override
    public Integer userAddCar(Car car) {
        return this.carMapper.userAddCar(car);
    }

    //前台用户更新车辆信息接口实现
    @Override
    public Integer userUpdateCar(Car car) {
        Car carSelected = carMapper.selectCarById(car.getId());
        if (carSelected == null || !carSelected.getUserid().equals(car.getUserid())) {
            return 0;
        }
        return this.carMapper.userUpdateCar(car);
    }

    //前台用户删除车辆信息接口实现
    @Override
    public Integer UserDeleteCarById(Integer carid, Integer userid) {
        Car carSelected = carMapper.selectCarById(carid);
        if (carSelected == null || !carSelected.getUserid().equals(userid)) {
            return 0;
        }
        return this.carMapper.userDeleteCarById(carid);
    }

    //前台用户根据用户id获取车辆信息列表接口实现
    @Override
    public List<Car> selectCarListByUserid(Integer userid) {
        return this.carMapper.selectCarListByUserid(userid);
    }

    //根据userid获取车辆总数
    @Override
    public Integer selectUserCarCount(Integer userid) {
        return this.carMapper.selectUserCarCount(userid);
    }

    @Override
    public List<Car> seeCar(int page, int limit) throws Exception {
        int pageIndex = (page - 1) * limit;
        return this.carMapper.seeCar(pageIndex, limit);
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
