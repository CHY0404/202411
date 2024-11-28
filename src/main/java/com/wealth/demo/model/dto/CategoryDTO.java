package com.wealth.demo.model.dto;

import com.wealth.demo.model.entity.WealthType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDTO {

    private Long id;
    private String name;         // 分類名稱
    private WealthType type;     // 收支類型 (INCOME/EXPENSE)
    private Long userId;         // 用戶 ID，為 NULL 表示預設分類
}
