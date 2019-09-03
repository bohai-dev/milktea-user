package com.milktea.milkteauser;



import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
 

@SpringBootApplication
@MapperScan("com.milktea.milkteauser.dao")
public class Application {  
	
//	@Value("${http.port}")
//    private Integer port;
//
//
//	
//	@Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 添加http
//        return tomcat;
//    }
//
///* --------------------请按照自己spring boot版本选择 end--------------------- */
// 
//
//    // 配置http
//    private Connector createStandardConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(port);
//        return connector;
//    }


    

    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args);
    }
    

}
