package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHandlerInterceptr;
import com.atguigu.springboot.component.MyLocaleResolver;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

// 配置类
// 使用WebMvcConfigurerAdapter可以扩展SpringMvc功能
// WebMvcConfigurerAdapter是SpringMvc的配置类
// @EnableWebMvc全面接管SpringMVC,需要什么自己配置
// @EnableWebMvc  前期简单的功能上推荐使用可以节省内存空间,后期不推荐，因为功能太多，还是自动配置方便不需要全面接管
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // super.addViewControllers(registry);
        registry.addViewController("/atguigu").setViewName("success");
    }

    // 所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                // 提交到main页面 但是映射到dashboard
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                registry.addInterceptor(new LoginHandlerInterceptr()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }


    // 将自己写好的国际化配置类重新加载进SpringMVC让SpringMVC用自己写好的 不再用自动装配的
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


}
