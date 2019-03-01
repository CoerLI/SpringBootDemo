package com.lihang.springboot;

import com.lihang.springboot.controller.StudentController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStudentApi {
    MockMvc mockMvc;

    @Autowired
    StudentController controller;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // Gson gson = new Gson();
    }

    @Test
    public void testSelectAll() throws Exception {
        mockMvc.perform(get("/student")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testSelectById() throws Exception {
        mockMvc.perform(get("/student/" + (int) Math.random())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void testUpdateById() throws Exception {
        mockMvc.perform(delete("/student/" + anyInt())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void testDeleteById() throws Exception {
        mockMvc.perform(get("/student")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    public void testInsert() throws Exception {
        mockMvc.perform(get("/student")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
