package com.wealth.demo.mapper;

import com.wealth.demo.model.dto.WealthDTO;
import com.wealth.demo.model.entity.Wealth;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WealthMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Wealth -> WealthDTO
    public WealthDTO toDto(Wealth wealth) {
        return modelMapper.map(wealth, WealthDTO.class);
    }

    // WealthDTO -> Wealth
    public Wealth toEntity(WealthDTO wealthDTO) {
        return modelMapper.map(wealthDTO, Wealth.class);
    }
}
