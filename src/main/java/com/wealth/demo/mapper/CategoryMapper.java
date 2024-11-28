package com.wealth.demo.mapper;

import com.wealth.demo.model.dto.CategoryDTO;
import com.wealth.demo.model.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Category -> CategoryDTO
    public CategoryDTO toDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    // CategoryDTO -> Category
    public Category toEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}
