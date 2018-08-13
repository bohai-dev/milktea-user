package com.milktea.milkteauser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 

@SpringBootApplication
@MapperScan("com.milktea.milkteauser.dao")
public class Application {  
    

    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args);
    }
    

}
