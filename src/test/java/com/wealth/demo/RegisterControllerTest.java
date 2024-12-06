package com.wealth.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wealth.demo.model.dto.UserLoginDTO;
import com.wealth.demo.model.dto.UserRegisterDTO;
import com.wealth.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testRegister() throws Exception {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("testUser");
        dto.setPassword("testPassword");
        dto.setEmail("test@example.com");

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("用戶註冊成功!"));
    }

    @Test
    public void testLogin() throws Exception {
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");

        when(userService.login(any(UserLoginDTO.class))).thenReturn("jwtToken");

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("jwtToken"));
    }
}
