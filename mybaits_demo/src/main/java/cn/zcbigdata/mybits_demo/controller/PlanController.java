package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckNull;
import cn.zcbigdata.mybits_demo.Util.CheckUserLogin;
import cn.zcbigdata.mybits_demo.Util.JsonUtil;
import cn.zcbigdata.mybits_demo.Util.Strs;
import cn.zcbigdata.mybits_demo.entity.Plan;
import cn.zcbigdata.mybits_demo.service.IPlanService;
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
@RequestMapping("/plan")
public class PlanController {
    private static final Logger LOGGER = Logger.getLogger(PlanController.class);

    @Resource
    private IPlanService IPlanService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "content2";
    }

    // 添加保养方案
    @ResponseBody
    @RequestMapping(value = "/addPlan", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String addPlan(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        // 插入数据库
        String plan = request.getParameter("plan");
        if (!CheckNull.checkNull(new String[]{plan})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        Plan plans = new Plan();
        plans.setPlan(plan);
        int flag = IPlanService.addPlan(plans);
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 修改保养方案
    @ResponseBody
    @RequestMapping(value = "/updataPlan", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String updataPlan(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        // 插入数据库
        String idStr = request.getParameter("id");
        String plan = request.getParameter("plan");
        if (!CheckNull.checkNull(new String[]{idStr, plan})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        Plan plans = new Plan();
        plans.setId(Integer.valueOf(idStr.trim()));
        plans.setPlan(plan);
        int flag = IPlanService.updataPlan(plans);
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 删除用户信息
    @ResponseBody
    @RequestMapping(value = "/deletePlan", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deletePlan(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!CheckNull.checkNull(new String[]{idStr})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        int flag = IPlanService.deletePlan(Integer.valueOf(idStr.trim()));
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 查询全部
    @ResponseBody
    @RequestMapping(value = "/seePlan", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String seePlan(HttpServletRequest request) throws Exception {
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
        List<Plan> plans = IPlanService.seePlan(Integer.valueOf(pageString), Integer.valueOf(limitString));
        String[] colums = {"id", "plan"};
        return JsonUtil.listToLayJson(colums, plans);
    }

    // 查询有多少条数据
    @ResponseBody
    @RequestMapping(value = "/selectCount", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String selectCount(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        int count = IPlanService.selectCount();
        String data = String.valueOf(count);
        return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"count\":\"" + data + "\"}";
    }
}
