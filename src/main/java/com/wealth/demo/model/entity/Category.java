package com.wealth.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name; // 分類名稱

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WealthType type; // 收入或支出類型 (INCOME/EXPENSE)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user; // NULL 表示公共分類，非 NULL 表示用戶自訂分類

    @Column(nullable = false)
    private LocalDateTime createdAt; // 建立時間


}
