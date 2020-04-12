package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "chatroom")
public class ChatRoomController {
    @GetMapping(value = "/index")
    public String index(){
        return "chatroom/index";
    }
}
