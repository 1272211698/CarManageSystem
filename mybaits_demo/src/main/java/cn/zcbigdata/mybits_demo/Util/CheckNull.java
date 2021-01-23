package cn.zcbigdata.mybits_demo.Util;

public class CheckNull {
    public static boolean checkNull(String[] args){
        for (String arg : args) {
            if (arg == null || arg.length() <= 0) {
                return false;
            }
        }
        return true;
    }

}
