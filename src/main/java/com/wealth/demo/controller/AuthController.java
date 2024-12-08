package com.wealth.demo.controller;

import com.wealth.demo.model.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthController {

    @GetMapping("/income-expense")
    public String validateSession(HttpSession session, Model model) {
        // 檢查用戶是否已登入
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/"; // 未登入，跳轉到登入頁面
        }

        // 如果需要，可以將用戶名添加到 Model，供 JSP 使用
        model.addAttribute("username", currentUser.getUsername());
        return "income-expense"; // 返回收支管理頁面
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 清除會話，登出用戶
        session.invalidate();
        return "redirect:/"; // 返回登入頁面
    }
}
