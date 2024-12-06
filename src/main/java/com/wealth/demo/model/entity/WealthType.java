package com.wealth.demo.model.entity;

/**
 * 枚舉類型 WealthType
 * 表示收支記錄的類型: 收入(INCOME) 或 支出(EXPENSE)
 */
public enum WealthType {
    INCOME("收入"),  // 收入
    EXPENSE("支出");

    private final String description;

    //私有構造函數
    WealthType(String description){
        this.description = description;
    }
    //獲取描述訊息
    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return description;
    }
}