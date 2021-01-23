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
    private ICarService ICarService;

    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public String backPage() {
        return "back";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "content1";
    }

    //车辆信息页面接口
    @RequestMapping("/userCarList")
    public String userCarList() {
        return "userCarList";
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
        int flag = ICarService.userAddCar(car);
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
        int flag = ICarService.userUpdateCar(car);
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
        int flag = ICarService.UserDeleteCarById(Integer.valueOf(idStr.trim()), Integer.valueOf((String) se.getAttribute("userid")));
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
        List<Car> cars = ICarService.selectCarListByUserid(Integer.valueOf((String) se.getAttribute("userid")));
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
        Integer count = ICarService.selectUserCarCount(Integer.valueOf((String) se.getAttribute("userid")));
        return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"count\":\"" + count + "\"}";
    }

    // 添加内置车辆
    @ResponseBody
    @RequestMapping(value = "/addCar", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String addCar(HttpServletRequest request) {
        // 插入数据库
        String useridString = request.getParameter("userid");
        String model = request.getParameter("model");
        String milageString = request.getParameter("milage");
        String capacityString = request.getParameter("capacity");
        String production = request.getParameter("production");
        String purchase = request.getParameter("purchase");
        if ("".equals(useridString) || "".equals(model) || "".equals(milageString) || "".equals(capacityString) || "".equals(production) || "".equals(purchase)) {
            return Strs.IS_NULL_RETURN_JSON;
        } else {
            useridString = useridString.trim();
            model = model.trim();
            milageString = milageString.trim();
            capacityString = capacityString.trim();
            production = production.trim();
            purchase = purchase.trim();
            if (useridString.length() == 0 || model.length() == 0 || milageString.length() == 0 || capacityString.length() == 0 || production.length() == 0 || purchase.length() == 0) {
                return Strs.IS_NULL_RETURN_JSON;
            }
        }
        Integer userid = Integer.valueOf(useridString);
        Double milage = Double.valueOf(milageString);
        Double capacity = Double.valueOf(capacityString);
        Car car = new Car();
        car.setUserid(userid);
        car.setModel(model);
        car.setMilage(milage);
        car.setCapacity(capacity);
        car.setProduction(production);
        car.setPurchase(purchase);
        int flag = ICarService.addCar(car);
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 修改车辆信息
    @ResponseBody
    @RequestMapping(value = "/updataCar", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String updataCar(HttpServletRequest request) {
        // 插入数据库
        String idString = request.getParameter("id");
        String useridString = request.getParameter("userid");
        String model = request.getParameter("model");
        String milageString = request.getParameter("milage");
        String capacityString = request.getParameter("capacity");
        String production = request.getParameter("production");
        String purchase = request.getParameter("purchase");
        if ("".equals(idString) || "".equals(useridString) || "".equals(model) || "".equals(milageString) || "".equals(capacityString) || "".equals(production) || "".equals(purchase)) {
            return Strs.IS_NULL_RETURN_JSON;
        } else {
            idString = idString.trim();
            useridString = useridString.trim();
            model = model.trim();
            milageString = milageString.trim();
            capacityString = capacityString.trim();
            production = production.trim();
            purchase = purchase.trim();
            if (idString.length() == 0 || useridString.length() == 0 || model.length() == 0 || milageString.length() == 0 || capacityString.length() == 0 || production.length() == 0 || purchase.length() == 0) {
                return Strs.IS_NULL_RETURN_JSON;
            }
        }
        Integer id = Integer.valueOf(idString);
        Integer userid = Integer.valueOf(useridString);
        Double milage = Double.valueOf(milageString);
        Double capacity = Double.valueOf(capacityString);
        Car car = new Car();
        car.setId(id);
        car.setUserid(userid);
        car.setModel(model);
        car.setMilage(milage);
        car.setCapacity(capacity);
        car.setProduction(production);
        car.setPurchase(purchase);
        int flag = ICarService.updataCar(car);
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 删除用户信息
    @ResponseBody
    @RequestMapping(value = "/deleteCar", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteCar(HttpServletRequest request) {
        String idString = request.getParameter("id");
        if (idString == null) {
            return Strs.IS_NULL_RETURN_JSON;
        } else {
            idString = idString.trim();
            if (idString.length() == 0) {
                return Strs.IS_NULL_RETURN_JSON;
            }
        }
        Integer id = Integer.valueOf(idString);
        int flag = ICarService.deleteCar(id);
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 查询全部
    @ResponseBody
    @RequestMapping(value = "/seeCar", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String seeCar(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        String pageString = request.getParameter("page");
        String limitString = request.getParameter("limit");
        if (pageString == null || limitString == null) {
            return Strs.IS_NULL_RETURN_JSON;
        } else {
            pageString = pageString.trim();
            limitString = limitString.trim();
            if (pageString.length() == 0 || limitString.length() == 0) {
                return Strs.IS_NULL_RETURN_JSON;
            }
        }
        Integer page = Integer.valueOf(pageString);
        Integer limit = Integer.valueOf(limitString);
        List<Car> cars = ICarService.seeCar(page, limit);
        String[] colums = {"id", "model", "milage", "capacity", "production", "purchase", "userid"};
        return JsonUtil.listToLayJson(colums, cars);
    }

    // 查询有多少条数据
    @ResponseBody
    @RequestMapping(value = "/selectCount", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String selectCount(HttpServletRequest request) {
        int count = ICarService.selectCount();
        String data = String.valueOf(count);
        return "{\"code\":\"0000\",\"count\":" + data + "}";
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
