package com.wealth.demo.service;


import com.wealth.demo.exception.ResourceNotFoundException;
import com.wealth.demo.model.dto.CategoryDTO;
import com.wealth.demo.model.entity.Category;
import com.wealth.demo.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    // 透過構造函數進行依賴注入
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 創建新的分類。
     * 若名稱為空或無效，將拋出異常。
     */
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        logger.info("創建新的分類: {}", categoryDTO);

        // 驗證邏輯：檢查名稱是否有效
        if (categoryDTO.getName() == null || categoryDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("分類名稱不能為空。");
        }

        Category category = modelMapper.map(categoryDTO, Category.class);
        category = categoryRepository.save(category);

        logger.info("成功創建分類，ID: {}", category.getId());
        return modelMapper.map(category, CategoryDTO.class);
    }

    /**
     * 獲取所有分類。
     */
    public List<CategoryDTO> getAllCategories() {
        logger.info("獲取所有分類。");

        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * 根據 ID 刪除分類。
     * 若分類不存在，將拋出資源未找到異常。
     */
    @Transactional
    public void deleteCategory(Long id) {
        logger.info("嘗試刪除分類，ID: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("找不到對應的分類，ID: " + id));

        categoryRepository.delete(category);
        logger.info("成功刪除分類，ID: {}", id);
    }
}
