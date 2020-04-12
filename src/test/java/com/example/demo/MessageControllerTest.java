package com.example.demo;

import com.example.demo.web.MessageController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MessageControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.saveMessages();
    }

    //@Test
    public void AllMessages() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/rest/messages")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse();
        System.out.println("result : "+response.getStatus()+"|"+response.getContentAsString());
    }

    //@Test
    public void test()throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("text", "文本");
//        params.add("summary", "中文");
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/messageTest")
//                .params(params)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print());
                //.andExpect(content().string(equalTo("hello web")));
                //.andExpect(content().string(containsString("hello")));
    }


    @Test
    public void saveMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("text", "save_text");
        params.add("summary", "save_summary");
        String mvcResult=  mockMvc.perform(MockMvcRequestBuilders.post("/rest/message")
                .params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void getAllMessages() throws Exception {
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/rest/messages"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void getMessage() throws Exception {
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/rest/message/2"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void modifyMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "6");
        params.add("text", "modify_text");
        params.add("summary", "modify_summary");
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.put("/rest/message").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void patchMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "6");
        params.add("text", "text");
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.patch("/rest/message/text").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void deleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/message/6"))
                .andReturn();
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/rest/messages"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    private void  saveMessages()  {
        for (int i=1;i<10;i++){
            final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("text", "text"+i);
            params.add("summary", "summary"+i);
            try {
                MvcResult mvcResult=  mockMvc.perform(MockMvcRequestBuilders.post("/rest/message")
                        .params(params)).andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
