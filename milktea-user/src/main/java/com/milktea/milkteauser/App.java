package com.milktea.milkteauser;

import com.milktea.milkteauser.wxpay.WXPayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.milktea.milkteauser"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }
}
