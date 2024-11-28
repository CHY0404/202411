package com.wealth.demo;

import com.wealth.demo.model.entity.User;
import com.wealth.demo.service.UserService;
import com.wealth.demo.repository.UserRepository;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @Test
//    public void testCreateUser() {
//        User user = new User();
//        user.setUsername("123");
//        user.setPassword("666");
//        user.setEmail("test@gmail.com");
//        User savedUser = userService.createUser(user);
//        assertNotNull(savedUser.getId());
//        System.out.println("新增用戶成功，ID：" + savedUser.getId());
//    }

    @Test
    public void testFindUserById() {
        // 假設我們已經有一個 ID 為 1 的用戶
        User user = userRepository.findById(2L).orElse(null);
        assertNotNull(user);
        Hibernate.initialize(user.getWealthRecords());
        System.out.println("查詢用戶成功: " + user);
    }



//    @Test
//    public void testFindUser() {
//        Long userId = 1L; // 假設這是已存在的用戶 ID
//        Optional<User> optionalUser = userService.getUserById(userId);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            assertNotNull(user);
//            System.out.println("找到用戶：" + user);
//        } else {
//            System.out.println("用戶 ID：" + userId + " 不存在！");
//            fail("未找到用戶");
//        }
//    }
//
//    @Test
//    public void testUpdateUser() {
//        Long userId = 1L; // 假設這是已存在的用戶 ID
//        Optional<User> optionalUser = userService.getUserById(userId);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setPassword("new_password");
//            User updatedUser = userService.updateUser(userId, user);
//            assertEquals("new_password", updatedUser.getPassword());
//            System.out.println("更新用戶成功：" + updatedUser);
//        } else {
//            System.out.println("用戶 ID：" + userId + " 不存在，無法更新！");
//            fail("未找到用戶");
//        }
//    }
//
}
