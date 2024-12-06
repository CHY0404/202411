package com.wealth.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "myPassword124";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("加密後的密碼: " + encodedPassword);
    }
}

