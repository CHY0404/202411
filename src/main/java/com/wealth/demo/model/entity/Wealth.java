package com.wealth.demo.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wealth_records")

public class Wealth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount; // 金額

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WealthType type; // 收入或支出 (INCOME/EXPENSE)

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // 所屬分類

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 所屬用戶

    @Column(nullable = false)
    private LocalDateTime timestamp; // 記錄時間

    @Column(length = 255)
    private String note; // 備註 (可選)
}

