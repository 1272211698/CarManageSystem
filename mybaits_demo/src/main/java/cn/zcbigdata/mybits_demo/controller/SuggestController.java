package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.CheckNull;
import cn.zcbigdata.mybits_demo.Util.CheckUserLogin;
import cn.zcbigdata.mybits_demo.Util.JsonUtil;
import cn.zcbigdata.mybits_demo.Util.Strs;
import cn.zcbigdata.mybits_demo.entity.Suggest;
import cn.zcbigdata.mybits_demo.service.ISuggestService;
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
@RequestMapping("/suggest")
public class SuggestController {

    private static final Logger LOGGER = Logger.getLogger(SuggestController.class);

    @Resource
    private ISuggestService ISuggestService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "content4";
    }

    // 添加留言
    @ResponseBody
    @RequestMapping(value = "/addSuggest", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String addSuggest(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        // 插入数据库
        String suggest = request.getParameter("suggest");
        if (!CheckNull.checkNull(new String[]{suggest})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        Suggest suggests = new Suggest();
        suggests.setSuggest(suggest);
        int flag = ISuggestService.addSuggest(suggests);
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 删除用户信息
    @ResponseBody
    @RequestMapping(value = "/deleteSuggest", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String deleteSuggest(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!CheckNull.checkNull(new String[]{idStr})) {
            return Strs.IS_NULL_RETURN_JSON;
        }
        int flag = ISuggestService.deleteSuggest(Integer.valueOf(idStr.trim()));
        if (flag == 1) {
            return Strs.SUCCESS_RETURN_JSON;
        } else {
            return Strs.FAIL_RETURN_JSON;
        }
    }

    // 查询全部
    @ResponseBody
    @RequestMapping(value = "/seeSuggest", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String seeSuggest(HttpServletRequest request) throws Exception {
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
        List<Suggest> suggests = ISuggestService.seeSuggest(Integer.valueOf(pageString), Integer.valueOf(limitString));
        String[] colums = {"id", "suggest"};
        return JsonUtil.listToLayJson(colums, suggests);
    }

    // 查询有多少条数据
    @ResponseBody
    @RequestMapping(value = "/selectCount", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String selectCount(HttpServletRequest request) {
        HttpSession se = request.getSession();
        if (!CheckUserLogin.check(se)) {
            return Strs.NO_LOGIN_RETURN_JSON;
        }
        int count = ISuggestService.selectCount();
        String data = String.valueOf(count);
        return "{\"code\":\"0000\",\"msg\":\"操作成功\",\"count\":\"" + data + "\"}";
    }
}
