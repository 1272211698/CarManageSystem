package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckNull;
import cn.zcbigdata.mybits_demo.Util.CheckUserLogin;
import cn.zcbigdata.mybits_demo.Util.JsonUtil;
import cn.zcbigdata.mybits_demo.Util.Strs;
import cn.zcbigdata.mybits_demo.entity.Notice;
import cn.zcbigdata.mybits_demo.service.INoticeService;
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
@RequestMapping("/notice")
public class NoticeController {

    private static final Logger LOGGER = Logger.getLogger(NoticeController.class);

    @Resource
    private INoticeService INoticeService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "content3";
    }

    // 添加公告
    @ResponseBody
    @RequestMapping(value = "/addNotice", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String addNotice(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        // 插入数据库
        String notice = request.getParameter("notice");
        if (!CheckNull.checkNull(new String[]{notice})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        Notice notices = new Notice();
        notices.setNotice(notice);
        int flag = INoticeService.addNotice(notices);
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 修改保养方案
    @ResponseBody
    @RequestMapping(value = "/updataNotice", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String updataNotice(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        // 插入数据库
        String idStr = request.getParameter("id");
        String notice = request.getParameter("notice");
        if (!CheckNull.checkNull(new String[]{idStr, notice})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        Notice notices = new Notice();
        notices.setId(Integer.valueOf(idStr.trim()));
        notices.setNotice(notice);
        int flag = INoticeService.updataNotice(notices);
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 删除用户信息
    @ResponseBody
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteNotice(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!CheckNull.checkNull(new String[]{idStr})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        int flag = INoticeService.deleteNotice(Integer.valueOf(idStr.trim()));
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 查询全部
    @ResponseBody
    @RequestMapping(value = "/seeNotice", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String seeNotice(HttpServletRequest request) throws Exception {
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
        List<Notice> notices = INoticeService.seeNotice(Integer.valueOf(pageString), Integer.valueOf(limitString));
        String[] colums = {"id", "notice"};
        return JsonUtil.listToLayJson(colums, notices);
    }

    // 查询有多少条数据
    @ResponseBody
    @RequestMapping(value = "/selectCount", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String selectCount(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        int count = INoticeService.selectCount();
        String data = String.valueOf(count);
        return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"count\":\"" + data + "\"}";
    }

}
