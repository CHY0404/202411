package com.wealth.demo.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "使用者名稱不能空白")
    private String username; // 用戶名

    @Column(nullable = false, length = 255)
    @NotBlank(message = "密碼不能空白")
    private String password; // 密碼 (加密後存儲)

    @Column(nullable = false, unique = true, length = 100)
    @Email(message = "必須填入有效Email")
    private String email; // 電子郵件

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wealth> wealthRecords = new ArrayList<>(); // 收支記錄

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>(); // 用戶自訂分類
}
