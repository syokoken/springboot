package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user =new User("neo",30,"123456");
        userRepository.save(user);
    }

    @Test
    public void testUpdate() {
        User user =new User("neo",18,"123456");
        user.setId(1L);
        userRepository.update(user);
    }

    @Test
    public void testDelete() {
        userRepository.delete(1L);
    }

    @Test
    public void testQueryOne()  {
        User user=userRepository.findById(1L);
        System.out.println("user == "+user.toString());
    }

    @Test
    public void testQueryAll()  {
        List<User> users=userRepository.findAll();
        for (User user:users){
            System.out.println("user == "+user.toString());
        }
    }
}
