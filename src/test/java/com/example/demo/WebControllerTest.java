package com.example.demo;

import com.example.demo.web.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class WebControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getUser() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/hello/getUser")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andReturn().getResponse().getContentAsString();
        System.out.println("result : "+responseString);
    }

    @Test
    public void getUsers() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/hello/getUsers")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result : "+responseString);
    }

    @Test
    public void saveUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hello/saveUser")
                .param("name","")
                .param("age","666")
                .param("pass","test")
        );
    }


}
