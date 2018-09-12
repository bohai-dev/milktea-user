package com.milktea.milkteauser.controller;

import com.milktea.milkteauser.vo.StripeBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

    @RequestMapping("/testStripe")
    public String testStripe(){

        return  "stripepaytest";
    }
    @RequestMapping( "/index")
    public String index(){
        return "index";
    }
}
