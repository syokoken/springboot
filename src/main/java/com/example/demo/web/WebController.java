package com.example.demo.web;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @RequestMapping("/thymeleaf")
    public String thymeleaf(ModelMap map) {
        map.addAttribute("message", "thymeleaf");
        map.addAttribute("username","username");
        map.addAttribute("flag", "true");
        map.addAttribute("list", getUsers());
        map.addAttribute("type","type");
        map.addAttribute("pageId",1);
        return "hello";
    }

    private List<User> getUsers(){
        List<User> list = new ArrayList<User>();
        list.add(new User("大牛",12,"123456"));
        list.add(new User("哈哈",22,"234567"));
        return list;
    }


}
