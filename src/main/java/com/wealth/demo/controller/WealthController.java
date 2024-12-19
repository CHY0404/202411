package com.wealth.demo.controller;

import com.wealth.demo.exception.InvalidAmountException;
import com.wealth.demo.exception.ResourceNotFoundException;
import com.wealth.demo.model.dto.WealthDTO;
import com.wealth.demo.model.entity.User;
import com.wealth.demo.service.UserService;
import com.wealth.demo.service.WealthService;
import com.wealth.demo.util.SessionUtils;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/wealth")
@RequiredArgsConstructor
public class WealthController {

    private static final Logger logger = LoggerFactory.getLogger(WealthController.class);

    private final WealthService wealthService;
    private final UserService userService;
    private final SessionUtils sessionUtils; // 注入 SessionUtils

    // 新增收支紀錄
    @PostMapping("/records")
    public ResponseEntity<String> addRecord(@RequestBody WealthDTO wealthDTO, HttpSession session) {
        logger.info("接收到新增收支紀錄請求: {}", wealthDTO);

        if (wealthDTO.getAmount() < 0) {
            throw new InvalidAmountException("金額不可為負數");
        }

        // 確保 type 欄位不為空
        if (wealthDTO.getType() == null || wealthDTO.getType().isEmpty()) {
            throw new IllegalArgumentException("收支類型 (type) 不可為空，必須是 'INCOME' 或 'EXPENSE'");
        }

        Long userId = sessionUtils.getAuthenticatedUserId(session)
                .orElseThrow(() -> new RuntimeException("未登入用戶嘗試新增收支記錄"));
        User user = validateAuthenticatedUser(userId);

        wealthService.createWealth(wealthDTO, user);
        logger.info("成功創建收支記錄");

        return ResponseEntity.ok("新增成功");
    }

    // 更新收支紀錄
    @PutMapping("/records/{id}")
    public ResponseEntity<WealthDTO> updateRecord(@PathVariable Long id, @RequestBody WealthDTO wealthDTO, HttpSession session) {
        logger.info("接收到更新收支記錄請求: ID={}, DTO={}", id, wealthDTO);

        if (wealthDTO.getAmount() < 0) {
            throw new InvalidAmountException("金額不可為負數");
        }

        // 確保 type 欄位不為空
        if (wealthDTO.getType() == null || wealthDTO.getType().isEmpty()) {
            throw new IllegalArgumentException("收支類型 (type) 不可為空，必須是 'INCOME' 或 'EXPENSE'");
        }

        Long userId = sessionUtils.getAuthenticatedUserId(session)
                .orElseThrow(() -> new RuntimeException("未登入，請先登入"));
        User user = validateAuthenticatedUser(userId);

        WealthDTO updatedWealth = wealthService.updateWealth(id, wealthDTO, user);
        logger.info("成功更新收支記錄: {}", updatedWealth);

        return ResponseEntity.ok(updatedWealth);
    }

    // 獲取單筆收支記錄
    @GetMapping("/records/{id}")
    public ResponseEntity<WealthDTO> getRecord(@PathVariable Long id, HttpSession session) {
        logger.info("接收到查詢單筆收支記錄請求: ID={}", id);

        Long userId = sessionUtils.getAuthenticatedUserId(session)
                .orElseThrow(() -> new RuntimeException("未登入，請先登入"));
        validateAuthenticatedUser(userId);

        WealthDTO wealth = wealthService.getWealthById(id);
        logger.info("查詢結果: {}", wealth);

        return ResponseEntity.ok(wealth);
    }

    // 分頁查詢收支記錄
    @GetMapping("/records/json")
    public ResponseEntity<List<WealthDTO>> getRecords(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      HttpSession session) {
        logger.info("接收到分頁查詢請求: Page={}, Size={}", page, size);

        Long userId = sessionUtils.getAuthenticatedUserId(session)
                .orElseThrow(() -> new RuntimeException("未登入，請先登入"));
        User user = validateAuthenticatedUser(userId);

        List<WealthDTO> records = wealthService.getWealths(page, size, user);
        logger.info("查詢結果: {}", records);

        return ResponseEntity.ok(records);
    }

    // 日期範圍查詢
    @GetMapping("/records/date-range")
    public ResponseEntity<List<WealthDTO>> getRecordsByDateRange(@RequestParam String startDate,
                                                                 @RequestParam String endDate,
                                                                 HttpSession session) {
        logger.info("接收到日期範圍查詢請求: StartDate={}, EndDate={}", startDate, endDate);

        Long userId = sessionUtils.getAuthenticatedUserId(session)
                .orElseThrow(() -> new RuntimeException("未登入，請先登入"));
        User user = validateAuthenticatedUser(userId);

        List<WealthDTO> records = wealthService.findByDateRange(
                LocalDate.parse(startDate), LocalDate.parse(endDate), user);
        logger.info("查詢結果: {}", records);

        return ResponseEntity.ok(records);
    }

    // 刪除收支記錄
    @DeleteMapping("/records/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id, HttpSession session) {
        logger.info("接收到刪除收支記錄請求: ID={}", id);

        Long userId = sessionUtils.getAuthenticatedUserId(session)
                .orElseThrow(() -> new RuntimeException("未登入，請先登入"));
        User user = validateAuthenticatedUser(userId);

        wealthService.deleteWealth(id, user);
        logger.info("成功刪除收支記錄: ID={}", id);

        return ResponseEntity.ok("刪除成功");
    }

    // 通用用戶驗證方法
    private User validateAuthenticatedUser(Long userId) {
        return userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("用戶不存在，ID: " + userId));
    }
}
