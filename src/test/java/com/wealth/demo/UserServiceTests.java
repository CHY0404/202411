package com.wealth.demo;

import com.wealth.demo.exception.UserAlreadyExistsException;
import com.wealth.demo.model.dto.UserLoginDTO;
import com.wealth.demo.model.dto.UserRegisterDTO;
import com.wealth.demo.model.entity.User;
import com.wealth.demo.repository.UserRepository;
import com.wealth.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testRegisterSuccess() {
        // Mock 数据
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("testUser");
        dto.setPassword("testPassword");
        dto.setEmail("test@example.com");

        // 模拟数据库行为
        when(userRepository.existsByUsername("testUser")).thenReturn(false);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(passwordEncoder.encode("testPassword")).thenReturn("encodedPassword");

        // 执行注册逻辑
        userService.register(dto);

        // 验证交互
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testRegisterUsernameExists() {
        // Mock 数据
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("existingUser");
        dto.setPassword("testPassword");
        dto.setEmail("test@example.com");

        // 模拟用户名已存在
        when(userRepository.existsByUsername("existingUser1")).thenReturn(true);

        // 执行注册逻辑
        userService.register(dto);
    }

    @Test
    public void testLoginSuccess() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("encodedPassword");

        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("rawPassword", "encodedPassword")).thenReturn(true);

        String token = userService.login(loginDTO);

        assertNotNull(token);
    }
}
