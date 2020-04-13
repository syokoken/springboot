package com.example.demo;

import com.example.demo.web.HelloController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hello/hello")
                .param("name","小明")
                .accept("application/json;charset=UTF-8"))
                .andDo(print());
    }

    @Test
    public void getHello_t()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/hello/hello")
                .param("name","中文")
                .accept("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("中文")));
    }

}