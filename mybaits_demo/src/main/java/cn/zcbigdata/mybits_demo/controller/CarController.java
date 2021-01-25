package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckNull;
import cn.zcbigdata.mybits_demo.Util.CheckUserLogin;
import cn.zcbigdata.mybits_demo.Util.JsonUtil;
import cn.zcbigdata.mybits_demo.Util.Strs;
import cn.zcbigdata.mybits_demo.entity.Car;
import cn.zcbigdata.mybits_demo.service.ICarService;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(CarController.class);
    @Resource
    private ICarService carService;

    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public String backPage() {
        return "back";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "content1";
    }

    //公告展示页面
    @RequestMapping("/notices")
    public String notices() {
        return "notices";
    }

    //车辆信息页面接口
    @RequestMapping("/userIndex")
    public String userCarList() {
        return "userIndex";
    }

    //添加汽车接口
    @RequestMapping(value = "/userAddCar", method = RequestMethod.POST)
    @ResponseBody
    public String UserAddCar(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String useridStr = (String) se.getAttribute("userid");
        String model = request.getParameter("model");
        String milageStr = request.getParameter("milage");
        String capacityStr = request.getParameter("capacity");
        String production = request.getParameter("production");
        String purchase = request.getParameter("purchase");
        if (!CheckNull.checkNull(new String[]{model, milageStr, capacityStr, production, purchase})) {
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
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    //修改车辆信息接口
    @RequestMapping(value = "/userUpdateCar", method = RequestMethod.POST)
    @ResponseBody
    public String userUpdateCar(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String model = request.getParameter("model");
        String milageStr = request.getParameter("milage");
        String capacityStr = request.getParameter("capacity");
        String production = request.getParameter("production");
        String purchase = request.getParameter("purchase");
        if (!CheckNull.checkNull(new String[]{idStr, model, milageStr, capacityStr, production, purchase})) {
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
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    //删除车辆信息接口
    @RequestMapping(value = "/userDeleteCar", method = RequestMethod.POST)
    @ResponseBody
    public String userDeleteCar(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!CheckNull.checkNull(new String[]{idStr})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        //service层鉴权，只能删除自己的车
        int flag = carService.userDeleteCarById(Integer.valueOf(idStr.trim()), Integer.valueOf((String) se.getAttribute("userid")));
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    //查看用户车辆信息接口
    @RequestMapping(value = "/selectUserCar", method = RequestMethod.GET)
    @ResponseBody
    public String selectUserCar(HttpServletRequest request) throws Exception {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        List<Car> cars = carService.selectCarListByUserid(Integer.valueOf((String) se.getAttribute("userid")));
        return JsonUtil.listToJson(new String[]{"id", "model", "milage", "capacity", "production", "purchase", "userid"}, cars);
    }

    //查询用户车辆总数接口
    @RequestMapping(value = "/selectUserCarCount", method = RequestMethod.GET)
    @ResponseBody
    public String selectUserCarCount(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        Integer count = carService.selectUserCarCount(Integer.valueOf((String) se.getAttribute("userid")));
        return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"count\":\"" + count + "\"}";
    }

    // 查询全部
    @ResponseBody
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String selectAll(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String pageString = request.getParameter("page");
        String limitString = request.getParameter("limit");
        if (!CheckNull.checkNull(new String[]{pageString, limitString})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        List<Car> cars = carService.selectAll(Integer.valueOf(pageString), Integer.valueOf(limitString));
        String[] colums = {"id", "model", "milage", "capacity", "production", "purchase", "userid"};
        return JsonUtil.listToLayJson(colums, cars);
    }

    // 查询有多少条数据
    @ResponseBody
    @RequestMapping(value = "/selectCount", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String selectCount(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        int count = carService.selectCount();
        String data = String.valueOf(count);
        return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"count\":\"" + data + "\"}";
    }

    //根据车辆id查询车辆信息
    @ResponseBody
    @RequestMapping(value = "/selectCarById", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String selectCarById(HttpServletRequest request) throws Exception {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String useridStr = (String) se.getAttribute("userid");
        String caridStr = request.getParameter("id");
        if (!CheckNull.checkNull(new String[]{caridStr})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        Car car = carService.selectCarById(Integer.valueOf(caridStr.trim()), Integer.valueOf(useridStr.trim()));
        if(car == null){
            return Strs.FAIL_RETURN_JSON;
        }else {
            return JsonUtil.objectToJson(new String[]{"id", "model", "milage", "capacity", "production", "purchase", "userid"}, car);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String logout(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        HttpSession se = request.getSession();
        se.invalidate();
        return Strs.SUCCESS_RETURN_JSON;
    }

}
