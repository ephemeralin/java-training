package com.ephemeralin.carplace.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * The Login controller test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc(secure = false)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test show custom login page.
     *
     * @throws Exception the exception
     */
    @Test
    public void testShowCustomLoginPage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(content().string(Matchers.containsString("Please, login...")));
    }

    /**
     * Test show access denied page.
     *
     * @throws Exception the exception
     */
    @Test
    public void testShowAccessDeniedPage() throws Exception {
        this.mockMvc.perform(get("/denied"))
                .andExpect(status().isOk())
                .andExpect(view().name("denied"))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("denied"))
                .andExpect(content().string(Matchers.containsString("Access denied: you are not authorized to access this resource.")));
    }
}
