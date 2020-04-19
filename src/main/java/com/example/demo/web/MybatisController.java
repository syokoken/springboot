package com.example.demo.web;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepositoryImp;
import com.example.demo.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "mybatis")
public class MybatisController {

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @GetMapping("/insert")
    public void testInsert(){
        userRepositoryImp.save(new User("a-name",11,"123"));
    }

    @GetMapping("/testinsert")
    public List<User> test(){
        return userRepositoryImpl.findAll();
    }

}
