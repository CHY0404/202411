package com.wealth.demo.service;

import com.wealth.demo.exception.ResourceNotFoundException;
import com.wealth.demo.model.dto.WealthDTO;
import com.wealth.demo.model.entity.Wealth;
import com.wealth.demo.repository.WealthRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WealthService {
    private static final Logger logger = LoggerFactory.getLogger(WealthService.class);

    @Autowired
    private WealthRepository wealthRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 創建新的收支記錄。
     * 若輸入的數據無效（例如金額為負數），將拋出異常。
     */
    @Transactional
    public WealthDTO createWealth(WealthDTO wealthDTO) {
        logger.info("嘗試創建新的收支記錄: {}", wealthDTO);

        // 驗證邏輯：檢查金額是否大於 0
        if (wealthDTO.getAmount() == null || wealthDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("金額必須大於 0。");
        }

        Wealth wealth = modelMapper.map(wealthDTO, Wealth.class);
        wealth = wealthRepository.save(wealth);

        logger.info("成功創建收支記錄，ID: {}", wealth.getId());
        return modelMapper.map(wealth, WealthDTO.class);
    }

    /**
     * 根據 ID 獲取收支記錄。
     * 若記錄不存在，將拋出資源未找到異常。
     */
    public WealthDTO getWealthById(Long id) {
        logger.info("獲取收支記錄，ID: {}", id);

        Wealth wealth = wealthRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("找不到對應的收支記錄，ID: " + id));

        logger.info("找到收支記錄: {}", wealth);
        return modelMapper.map(wealth, WealthDTO.class);
    }

    /**
     * 分頁獲取收支記錄。
     * 使用分頁功能以避免一次性加載過多數據。
     */
    public List<WealthDTO> getWealths(int page, int size) {
        logger.info("分頁查詢收支記錄: page={}, size={}", page, size);

        Page<Wealth> wealthPage = wealthRepository.findAll(PageRequest.of(page, size));
        List<WealthDTO> wealthDTOList = wealthPage.getContent()
                .stream()
                .map(wealth -> modelMapper.map(wealth, WealthDTO.class))
                .collect(Collectors.toList());

        logger.info("共查詢到 {} 條收支記錄。", wealthDTOList.size());
        return wealthDTOList;
    }

    /**
     * 根據 ID 刪除收支記錄。
     * 若記錄不存在，將拋出資源未找到異常。
     */
    @Transactional
    public void deleteWealth(Long id) {
        logger.info("嘗試刪除收支記錄，ID: {}", id);

        Wealth wealth = wealthRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("找不到對應的收支記錄，ID: " + id));

        wealthRepository.delete(wealth);
        logger.info("成功刪除收支記錄，ID: {}", id);
    }
}
