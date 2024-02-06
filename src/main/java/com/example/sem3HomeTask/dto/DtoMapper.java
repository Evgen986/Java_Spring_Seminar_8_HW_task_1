package com.example.sem3HomeTask.dto;

import com.example.sem3HomeTask.domain.User;

/**
 * Интерфейс преобразование объектов dao в сущности и наоборот.
 */
public interface DtoMapper {
    /**
     * Преобразование dto в сущность.
     * @param userDTO объект dao.
     * @return сущность user.
     */
    User toEntity(UserDTO userDTO);

    /**
     * Преобразование сущности в dto.
     * @param user сущность пользователя.
     * @return объект dto.
     */
    UserDTO toDto(User user);
}
