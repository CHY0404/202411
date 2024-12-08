package com.wealth.demo.controller;

import com.wealth.demo.exception.PasswordMismatchException;
import com.wealth.demo.exception.UsernameNotFoundException;
import com.wealth.demo.model.dto.UserLoginDTO;
import com.wealth.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {

    @Autowired
    private  UserService userService;

    @PostMapping("/login")
    public String login(@ModelAttribute("userLoginDTO") UserLoginDTO userLoginDTO, Model model) {
        try {
            userService.login(userLoginDTO);

            return "redirect:/income-expense"; // 登入成功跳轉到後台
        } catch (UsernameNotFoundException e) {
            model.addAttribute("errorSource", "login");
            model.addAttribute("error", "username");
            model.addAttribute("errorMessage", e.getMessage());
            return "index"; // 返回登入表單並顯示錯誤
        } catch (PasswordMismatchException e) {
            model.addAttribute("errorSource", "login");
            model.addAttribute("error", "password");
            model.addAttribute("errorMessage", e.getMessage());
            return "index"; // 返回登入表單並顯示錯誤
        }

    }

}
