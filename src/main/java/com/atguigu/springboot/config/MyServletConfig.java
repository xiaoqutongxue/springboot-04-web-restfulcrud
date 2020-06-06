package com.atguigu.springboot.config;

import com.atguigu.springboot.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class MyServletConfig {


    // 注册servlet三大组件
    // @Bean的意思是加在容器中
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean registrationBean =  new  ServletRegistrationBean(new MyServlet(),"/myServlet");
        return registrationBean;
    }



    // 配置嵌入式的servlet容器
    //    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//
//            //定制嵌入式servlet容器的相关规则
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                // 设置嵌入式容器的端口号
//                container.setPort(8083);
//            }
//        };
//    }
}
