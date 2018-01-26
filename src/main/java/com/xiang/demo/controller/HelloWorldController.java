package com.xiang.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author xjc
 *
 * 测试访问前后端
 *
 * 这里前后端没有分离，不要使用@RestController注解，只有写rest api接口的时候使用
 * @RestController = @Controller + @ResponseBody
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/index")
    public String index(Model model) {
        // 加入一个属性，用来在模板中读取
        model.addAttribute("host", "http://blog.didispace.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @RequestMapping(value = "/helloWorld/to_hello", method = RequestMethod.GET)
    @ResponseBody
    public String toHello() {
        return "i am Spring Boot!";
    }

}
