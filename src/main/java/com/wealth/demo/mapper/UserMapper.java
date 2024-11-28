package com.wealth.demo.mapper;

import com.wealth.demo.model.dto.UserDTO;
import com.wealth.demo.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    // User -> UserDTO
    public UserDTO toDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    // UserDTO -> User
    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
