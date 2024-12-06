package com.wealth.demo.controller;

import com.wealth.demo.exception.EmailAlreadyExistsException;
import com.wealth.demo.exception.UserAlreadyExistsException;
import com.wealth.demo.model.dto.UserRegisterDTO;
import com.wealth.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> {
                model.addAttribute("errorSource", "register");
                model.addAttribute("error", error.getField());
                model.addAttribute("errorMessage", error.getDefaultMessage());
            });
            return "index"; // 返回註冊表單
        }
        try {
            userService.register(userRegisterDTO);
            return "redirect:/"; // 註冊成功跳轉到登入頁
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("errorSource", "register");
            model.addAttribute("error", "username");
            model.addAttribute("errorMessage", e.getMessage());
            return "index"; // 返回註冊表單並顯示錯誤
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("errorSource", "register");
            model.addAttribute("error", "email");
            model.addAttribute("errorMessage", e.getMessage());
            return "index"; // 返回註冊表單並顯示錯誤
        }
    }
}