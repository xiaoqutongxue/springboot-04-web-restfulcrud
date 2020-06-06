package com.atguigu.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

// 配置类 国际化
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l= request.getParameter("l");
        // 获取默认的区域信息
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            String[] spilt = l.split("_");
            locale  =  new Locale(spilt[0],spilt[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
