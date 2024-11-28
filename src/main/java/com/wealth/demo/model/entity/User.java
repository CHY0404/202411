package com.wealth.demo.model.entity;


import jakarta.persistence.*;
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
    private String username; // 用戶名

    @Column(nullable = false, length = 255)
    private String password; // 密碼 (加密後存儲)

    @Column(nullable = false, unique = true, length = 100)
    private String email; // 電子郵件

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wealth> wealthRecords = new ArrayList<>(); // 收支記錄

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>(); // 用戶自訂分類
}
