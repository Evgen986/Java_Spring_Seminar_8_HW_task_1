package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.aspect.TrackUserAction;
import com.example.sem3HomeTask.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис регистрации новых пользователей.
 */
@Service
@AllArgsConstructor
public class RegistrationService {

    /**
     * Поле сервиса работы с хранилищем пользователей.
     */
    private final DataProcessingService dataProcessingService;

    /**
     * Поле сервиса создания пользователей.
     */
    private final UserService userService;

    /**
     * Получение сервиса работы с хранилищем пользователей.
     * @return
     */
    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    /**
     * Сохранение пользователя в БД.
     * @param name имя пользователя.
     * @param age возраст пользователя.
     * @param email email пользователя.
     */
    @TrackUserAction
    public void processRegistration(String name, int age, String email){
        User createUser = userService.createUser(name, age, email);
        dataProcessingService.addUser(createUser);
    }
}
