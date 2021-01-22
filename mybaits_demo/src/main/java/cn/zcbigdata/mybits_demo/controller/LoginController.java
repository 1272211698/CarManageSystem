package cn.zcbigdata.mybits_demo.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "back";
    }

}
