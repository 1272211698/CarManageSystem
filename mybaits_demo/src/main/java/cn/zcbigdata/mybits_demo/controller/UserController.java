package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckNull;
import cn.zcbigdata.mybits_demo.Util.CheckUserLogin;
import cn.zcbigdata.mybits_demo.Util.Strs;
import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    //登录页面接口
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    //用户登录接口
    @RequestMapping(value = "/loginSubmit", method = RequestMethod.GET)
    @ResponseBody
    public String loginSubmit(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (!CheckNull.checkNull(new String[]{userName, password})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        User user = new User();
        user.setUserName(userName.trim());
        user.setPassword(password.trim());
        user = this.userService.loginSubmit(user);
        if (user == null || user.getUserName() == null || user.getUserName().length() <= 0) {
            return Strs.FAIL_RETURN_JSON;
        }
        HttpSession se = request.getSession();
        se.setAttribute("userid", user.getId().toString());
        se.setAttribute("admin", user.getAdmin().toString());
        se.setAttribute("userName", user.getUserName());
        if (user.getAdmin().equals(1)) {
            return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"data\":{\"admin\":\"1\"}}";
        } else {
            return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"data\":{\"admin\":\"0\"}}";
        }
    }

    //用户登出接口
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpServletRequest request) {
        HttpSession se = request.getSession();
        se.invalidate();
        return Strs.SUCCESS_RETURN_JSON;
    }

    //注册页面接口
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUpPage() {
        return "signUp";
    }

    //用户注册接口
    @RequestMapping(value = "/signUpSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String signUpSubmit(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (!CheckNull.checkNull(new String[]{userName, password})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        User user = new User();
        user.setUserName(userName.trim());
        user.setPassword(password.trim());
        int flag = this.userService.signUpSubmit(user);
        if (flag != 1) {
            return Strs.FAIL_RETURN_JSON;
        }
        return Strs.SUCCESS_RETURN_JSON;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String userid = (String) se.getAttribute("userid");
        String userName = (String) se.getAttribute("userName");
        return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"data\":{\"userid\":\"" + userid + "\",\"userName\":\"" + userName + "\"}}";
    }

    //登录检查接口
    @RequestMapping(value = "/checkUserLogin", method = RequestMethod.GET)
    @ResponseBody
    public String checkUserLogin(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        return Strs.SUCCESS_RETURN_JSON;
    }
}
