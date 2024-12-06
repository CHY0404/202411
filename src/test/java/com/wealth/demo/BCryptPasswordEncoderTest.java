package com.wealth.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BCryptPasswordEncoderTest {

    @Test
    public void testPasswordEncodingAndMatching() {
        // 創建 BCryptPasswordEncoder 實例
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 原始密碼
        String rawPassword = "testPassword1";

        // 對原始密碼進行加密
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("加密後的 Password: " + encodedPassword);

        // 驗證原始密碼與加密後的密碼是否匹配
        boolean matches = encoder.matches(rawPassword, encodedPassword);

        // 斷言匹配結果為 true
        assertTrue(matches, "符合");

        // 測試不同的原始密碼，應該返回 false
        boolean matchesWrong = encoder.matches("密碼錯誤", encodedPassword);
        assertTrue(!matchesWrong, "不符合");
    }
}
