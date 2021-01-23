package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckNull;
import cn.zcbigdata.mybits_demo.Util.CheckUserLogin;
import cn.zcbigdata.mybits_demo.Util.JsonUtil;
import cn.zcbigdata.mybits_demo.Util.Strs;
import cn.zcbigdata.mybits_demo.entity.Record;
import cn.zcbigdata.mybits_demo.service.IRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Resource
    private IRecordService recordService;

    //前台查询保养记录接口
    @RequestMapping(value = "/selectRecordByCarid", method = RequestMethod.GET)
    @ResponseBody
    public String selectRecordByCarid(HttpServletRequest request) throws Exception {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String useridStr = (String) se.getAttribute("userid");
        String caridStr = request.getParameter("carid");
        if (!CheckNull.checkNull(new String[]{caridStr})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        List<Record> records = recordService.selectRecordByCarid(Integer.valueOf(caridStr.trim()), Integer.valueOf(useridStr));
        if (records == null) {
            return Strs.FAIL_RETURN_JSON;
        }
        return JsonUtil.listToJson(new String[]{"id", "plan", "recordTime"}, records);
    }

    //前台添加保养记录接口
    @RequestMapping(value = "/userAddRecord", method = RequestMethod.POST)
    @ResponseBody
    public String addRecord(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String useridStr = (String) se.getAttribute("userid");
        String caridStr = request.getParameter("carid");
        String plan = request.getParameter("plan");
        if (!CheckNull.checkNull(new String[]{caridStr, plan})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        Record record = new Record();
        record.setCarid(Integer.valueOf(caridStr.trim()));
        record.setPlan(plan.trim());
        int flag = recordService.userAddRecord(record, Integer.valueOf(useridStr));
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    //前台修改保养记录接口
    @RequestMapping(value = "/userUpdateRecord", method = RequestMethod.POST)
    @ResponseBody
    public String userUpdateRecord(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String useridStr = (String) se.getAttribute("userid");
        String plan = request.getParameter("plan");
        String recordIdStr = request.getParameter("id");
        if (!CheckNull.checkNull(new String[]{plan, recordIdStr})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        Record record = new Record();
        record.setId(Integer.valueOf(recordIdStr));
        record.setPlan(plan.trim());
        int flag = recordService.userUpdateRecord(record, Integer.valueOf(useridStr));
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    //前台删除保养记录接口
    @RequestMapping(value = "/userDeleteRecord", method = RequestMethod.POST)
    @ResponseBody
    public String userDeleteRecord(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String useridStr = (String) se.getAttribute("userid");
        String recordIdStr = request.getParameter("id");
        if (!CheckNull.checkNull(new String[]{recordIdStr})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        int flag = recordService.userDeleteRecordById(Integer.valueOf(recordIdStr.trim()), Integer.valueOf(useridStr));
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }
}
