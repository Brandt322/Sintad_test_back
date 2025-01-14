package com.Sintad_test.users.models.mapper;

import com.Sintad_test.users.interfaces.UserMapEntityToDto;
import com.Sintad_test.users.models.entities.User;
import com.Sintad_test.users.models.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements UserMapEntityToDto {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse entityToDto(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
