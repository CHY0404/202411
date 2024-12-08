package com.wealth.demo.controller;

import com.wealth.demo.model.dto.WealthDTO;
import com.wealth.demo.service.WealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wealth")
public class WealthController {

    @Autowired
    private  WealthService wealthService;

    // 1. 新增收支記錄
    @PostMapping("/records")
    public ResponseEntity<WealthDTO> addRecord(@RequestBody WealthDTO wealthDTO) {
        WealthDTO createdWealth = wealthService.createWealth(wealthDTO);
        return ResponseEntity.ok(createdWealth);
    }

    // 2. 獲取單筆收支記錄
    @GetMapping("/records/{id}")
    public ResponseEntity<WealthDTO> getRecord(@PathVariable Long id) {
        WealthDTO wealth = wealthService.getWealthById(id);
        return ResponseEntity.ok(wealth);
    }

    // 3. 分頁查詢收支記錄
    @GetMapping("/records")
    public ResponseEntity<List<WealthDTO>> getRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<WealthDTO> records = wealthService.getWealths(page, size);
        return ResponseEntity.ok(records);
    }

    // 4. 刪除收支記錄
    @DeleteMapping("/records/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        wealthService.deleteWealth(id);
        return ResponseEntity.ok("刪除成功");
    }
}
