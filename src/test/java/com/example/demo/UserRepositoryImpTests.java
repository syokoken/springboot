package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepositoryImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserRepositoryImpTests {

    @Autowired
    @Qualifier("userRepositoryImp")
    private UserRepositoryImp userRepositoryImp;

    @Test
    public void truncate()throws Exception{
        userRepositoryImp.resetTable();
    }

    @Test
    public void testInsert()throws Exception{
        userRepositoryImp.save(new User("a-name",11,"123"));
        userRepositoryImp.save(new User("b-name",11,"123"));
        userRepositoryImp.save(new User("c-name",11,"123"));
    }

    @Test
    public void getAllUser(){
        System.out.println(userRepositoryImp.findAll());
    }




}
