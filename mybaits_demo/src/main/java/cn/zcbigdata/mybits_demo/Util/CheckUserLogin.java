package cn.zcbigdata.mybits_demo.Util;

import javax.servlet.http.HttpSession;

public class CheckUserLogin {
    public static boolean check(HttpSession se) {
        String useridStr = (String) se.getAttribute("userid");
        return useridStr != null && useridStr.length() > 0;
    }
}
