package com.wealth.demo.controller;


import com.wealth.demo.model.entity.User;
import com.wealth.demo.service.UserService;
import com.wealth.demo.util.SessionUtils;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final SessionUtils sessionUtils;


    /**
     * 顯示會員資料頁面
     */
    @GetMapping
    public String showProfile(HttpSession session, Model model) {
        Long userId = sessionUtils.getAuthenticatedUserId(session)
                .orElseThrow(() -> new IllegalArgumentException("用戶未登錄"));

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("用戶不存在"));

        model.addAttribute("user", user);
        return "profile"; // profile.jsp
    }

    /**
     * 處理會員資料更新
     */
    @PostMapping("/update")
    public String updateProfile(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            HttpSession session,
            Model model) {
        try {
            Long userId = sessionUtils.getAuthenticatedUserId(session)
                    .orElseThrow(() -> new IllegalArgumentException("用戶未登錄"));

            // 驗證新密碼是否一致
            if (!newPassword.equals(confirmPassword)) {
                throw new IllegalArgumentException("新密碼與確認密碼不一致");
            }

            // 更新會員資料
            userService.updateProfile(userId, username, email, currentPassword, newPassword);

            model.addAttribute("success", "會員資料已成功更新");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "profile"; // 返回 profile.jsp
    }
}


