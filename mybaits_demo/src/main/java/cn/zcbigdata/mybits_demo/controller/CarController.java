package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckNull;
import cn.zcbigdata.mybits_demo.Util.CheckUserLogin;
import cn.zcbigdata.mybits_demo.Util.JsonUtil;
import cn.zcbigdata.mybits_demo.Util.Strs;
import cn.zcbigdata.mybits_demo.entity.Car;
import cn.zcbigdata.mybits_demo.service.ICarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Resource
    private ICarService carService;

    //车辆信息页面接口
    @RequestMapping("/userCarList")
    public String userCarList(){return "userCarList";}

    //添加汽车接口
    @RequestMapping(value = "/userAddCar" , method = RequestMethod.POST)
    @ResponseBody
    public String UserAddCar(HttpServletRequest request){
        HttpSession se = request.getSession();
        if(!CheckUserLogin.check(se)){
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String useridStr = (String) se.getAttribute("userid");
        String model = request.getParameter("model");
        String milageStr = request.getParameter("milage");
        String capacityStr = request.getParameter("capacity");
        String production = request.getParameter("production");
        String purchase = request.getParameter("purchase");
        if(!CheckNull.checkNull(new String[]{model,milageStr,capacityStr,production,purchase})){
            return Strs.IS_NULL_RETURN_JSON;
        }
        Car car = new Car();
        car.setModel(model.trim());
        car.setMilage(Double.valueOf(milageStr.trim()));
        car.setCapacity(Double.valueOf(capacityStr.trim()));
        car.setProduction(production.trim());
        car.setPurchase(purchase.trim());
        car.setUserid(Integer.valueOf(useridStr.trim()));
        int flag = carService.userAddCar(car);
        if(flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        }else{
            return Strs.FAIL_RETURN_JSON;
        }
    }

    //修改车辆信息接口
    @RequestMapping(value = "/userUpdateCar" , method = RequestMethod.POST)
    @ResponseBody
    public String userUpdateCar(HttpServletRequest request){
        HttpSession se = request.getSession();
        if(!CheckUserLogin.check(se)){
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String model = request.getParameter("model");
        String milageStr = request.getParameter("milage");
        String capacityStr = request.getParameter("capacity");
        String production = request.getParameter("production");
        String purchase = request.getParameter("purchase");
        if(!CheckNull.checkNull(new String[]{idStr,model,milageStr,capacityStr,production,purchase})){
            return Strs.IS_NULL_RETURN_JSON;
        }
        Car car = new Car();
        car.setId(Integer.valueOf(idStr.trim()));
        car.setModel(model.trim());
        car.setMilage(Double.valueOf(milageStr.trim()));
        car.setCapacity(Double.valueOf(capacityStr.trim()));
        car.setProduction(production.trim());
        car.setPurchase(purchase.trim());
        car.setUserid(Integer.valueOf((String) se.getAttribute("userid")));
        //service层鉴权，只能修改自己的车
        int flag = carService.userUpdateCar(car);
        if(flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        }else{
            return Strs.FAIL_RETURN_JSON;
        }
    }

    //删除车辆信息接口
    @RequestMapping(value = "/userDeleteCar" , method = RequestMethod.POST)
    @ResponseBody
    public String userDeleteCar(HttpServletRequest request){
        HttpSession se = request.getSession();
        if(!CheckUserLogin.check(se)){
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if(!CheckNull.checkNull(new String[]{idStr})){
            return Strs.IS_NULL_RETURN_JSON;
        }
        //service层鉴权，只能删除自己的车
        int flag = carService.UserDeleteCarById(Integer.valueOf(idStr.trim()), Integer.valueOf((String) se.getAttribute("userid")));
        if(flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        }else{
            return Strs.FAIL_RETURN_JSON;
        }
    }

    //查看用户车辆信息接口
    @RequestMapping(value = "/selectUserCar" , method = RequestMethod.GET)
    @ResponseBody
    public String selectUserCar(HttpServletRequest request) throws Exception {
        HttpSession se = request.getSession();
        if(!CheckUserLogin.check(se)){
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        List<Car> cars= carService.selectCarListByUserid(Integer.valueOf((String) se.getAttribute("userid")));
        return JsonUtil.listToJson(new String[]{"id", "model", "milage", "capacity", "production", "purchase", "userid"}, cars);
    }

    //查询用户车辆总数接口
    @RequestMapping(value = "/selectUserCarCount" , method = RequestMethod.GET)
    @ResponseBody
    public String selectUserCarCount(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if(!CheckUserLogin.check(se)){
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        Integer count = carService.selectUserCarCount(Integer.valueOf((String) se.getAttribute("userid")));
        return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"count\":\""+count+"\"}";
    }
}
