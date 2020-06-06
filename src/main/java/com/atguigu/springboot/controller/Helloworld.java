package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class Helloworld {

    @ResponseBody
    @RequestMapping("/hello")
    public String helloWorld(@RequestParam("user") String user){
        if(user.equals("aaa")){
             throw new UserNotExistException();
        }
        return "Hello World";
    }

    // 例子:查询一些数据在页面显示
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        // 相当于是classpath:/templates/success.html
        // 根目录下寻找
        map.put("hello","<h1>你好thymeleaf</h1>");
        map.put("users", Arrays.asList("张三","李四"));
        return "success";
    }
}
