package com.wealth.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * WealthDTO 是 Wealth 實體的數據傳輸對象，用於封裝收支記錄的數據。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WealthDTO {

    private Long id; // 收支記錄 ID
    private Integer amount; // 金額
    private LocalDateTime timestamp; // 記錄的時間戳
    private String note; // 備註
    private String type;

}
