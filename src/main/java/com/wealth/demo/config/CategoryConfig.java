package com.wealth.demo.config;


import com.wealth.demo.model.entity.Category;
import com.wealth.demo.model.entity.WealthType;
import com.wealth.demo.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryConfig {

    private final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryConfig.class);

    @PostConstruct
    public void initializeDefaultCategories() {
        if (categoryRepository.count() == 0) { // 如果資料表為空，才初始化預設分類
            List<Category> defaultCategories = List.of(
                    new Category("薪資", WealthType.INCOME, "💼"),
                    new Category("投資", WealthType.INCOME, "📈"),
                    new Category("獎金", WealthType.INCOME, "🏆"),
                    new Category("餐飲", WealthType.EXPENSE, "🍔"),
                    new Category("交通", WealthType.EXPENSE, "🚗"),
                    new Category("娛樂", WealthType.EXPENSE, "🎮"),
                    new Category("房租", WealthType.EXPENSE, "🏠"),
                    new Category("水電", WealthType.EXPENSE, "💡")
            );
            categoryRepository.saveAll(defaultCategories);
            logger.info("預設分類初始化完成: {}", defaultCategories);
        } else {
            logger.info("分類已存在，無需初始化");
        }
    }
}
