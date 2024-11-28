package com.wealth.demo.model.dto;

import com.wealth.demo.model.entity.WealthType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WealthDTO {
    private Long id;
    private BigDecimal amount; // 金額
    private WealthType type;   // 收支類型 (INCOME/EXPENSE)
    private Long categoryId;   // 分類 ID
    private String categoryName; // 分類名稱 (方便顯示)
    private LocalDateTime timestamp; // 記錄時間
    private String note;       // 備註
}
