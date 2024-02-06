package com.example.sem3HomeTask.utils;

import com.example.sem3HomeTask.dto.DtoMapper;
import com.example.sem3HomeTask.dto.UserDTO;
import com.example.sem3HomeTask.domain.User;
import org.springframework.stereotype.Component;

@Component
public class DtoMapperImpl implements DtoMapper {
    /**
     * Преобразование dao в сущность.
     * @param userDTO объект dao.
     * @return сущность пользователя.
     */
    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setEmail(user.getEmail());
        return user;
    }

    /**
     * Преобразование dao в сущность.
     * @param user сущность пользователя.
     * @return объект dao пользователя.
     */
    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
