package com.example.beanvalidation;

import com.example.beanvalidation.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	@Autowired
	protected WebApplicationContext context;

	private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
	@Before
	public void setMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void  addUser()throws Exception{
        User user = new User();
        user.setId(1);
        user.setAge(10);
        user.setName("zs");
        String contentAsString = mockMvc.perform(post("/add").content(objectMapper.writeValueAsString(user)).contentType("application/json")).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

    }


}
