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
    public String register(
            @Valid @ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO,
            BindingResult result,
            Model model) {
        // 1. 處理校驗錯誤
        if (result.hasErrors()) {
            handleValidationErrors(result, model);
            return "index"; // 返回註冊表單
        }

        try {
            // 2. 註冊用戶
            userService.register(userRegisterDTO);
            return "redirect:/"; // 註冊成功跳轉到登入頁

        } catch (UserAlreadyExistsException e) {
            return handleRegistrationError("register", "username", e.getMessage(), model);

        } catch (EmailAlreadyExistsException e) {
            return handleRegistrationError("register", "email", e.getMessage(), model);
        }
    }

    /**
     * 處理校驗錯誤，將錯誤信息添加到模型中
     *
     * @param result 校驗結果
     * @param model  模型對象
     */
    private void handleValidationErrors(BindingResult result, Model model) {
        result.getFieldErrors().forEach(error -> {
            model.addAttribute("errorSource", "register");
            model.addAttribute("error", error.getField());
            model.addAttribute("errorMessage", error.getDefaultMessage());
        });
    }

    /**
     * 處理註冊異常，將錯誤信息添加到模型中
     *
     * @param errorSource  錯誤來源
     * @param error        錯誤類型
     * @param errorMessage 錯誤信息
     * @param model        模型對象
     * @return 返回註冊頁面
     */
    private String handleRegistrationError(String errorSource, String error, String errorMessage, Model model) {
        model.addAttribute("errorSource", errorSource);
        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);
        return "index"; // 返回註冊表單
    }
}
