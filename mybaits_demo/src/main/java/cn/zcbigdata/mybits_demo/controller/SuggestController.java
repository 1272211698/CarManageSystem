package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.Util.JsonUtil;
import cn.zcbigdata.mybits_demo.Util.Strs;
import cn.zcbigdata.mybits_demo.entity.Suggest;
import cn.zcbigdata.mybits_demo.service.SuggestService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/suggest")
public class SuggestController {

    private static final Logger LOGGER = Logger.getLogger(SuggestController.class);

    @Resource
    private SuggestService suggestService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "content4";
    }

    // 添加留言
    @ResponseBody
    @RequestMapping(value = "/addSuggest", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String addSuggest(HttpServletRequest request) {
        // 插入数据库
        String suggest = request.getParameter("suggest");
        if ("".equals(suggest)) {
            return Strs.IS_NULL_RETURN_JSON;
        } else {
            suggest = suggest.trim();
            if (suggest.length() == 0) {
                return Strs.IS_NULL_RETURN_JSON;
            }
        }
        Suggest suggests = new Suggest();
        suggests.setSuggest(suggest);
        int flag = suggestService.addSuggest(suggests);
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
        int flag = suggestService.deleteSuggest(id);
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
        List<Suggest> suggests = suggestService.seeSuggest(page, limit);
        String[] colums = { "id", "suggest"};
        String data = JsonUtil.listToLayJson(colums, suggests);
        return data;
    }

    // 查询有多少条数据
    @ResponseBody
    @RequestMapping(value = "/selectCount", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String selectCount(HttpServletRequest request){
        int count = suggestService.selectCount();
        String data = String.valueOf(count);
        String json = "{\"code\":\"0000\",\"count\":" + data + "}";
        return json;
    }
}
