package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.dto.DtoMapper;
import com.example.sem3HomeTask.dto.UserDTO;
import com.example.sem3HomeTask.services.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Контроллер пользователей.
 */

@RestController
@RequestMapping("/users")
@Tag(name = "Контроллер пользователей",
description = "Осуществляет вывод пользователей, добавление пользователей")
@AllArgsConstructor
public class UserController {

    /**
     * Сервис регистрации пользователей.
     */
    private final RegistrationService service;
    /**
     * Объект преобразования dao в сущность и наоборот.
     */
    private final DtoMapper dtoMapper;
    /**
     * Поле объекта валидатора.
     */
    private final Validator validator;

    /**
     * Получение списка пользователей.
     * @return JSON ответ со списком пользователей.
     */
    @GetMapping
    @Operation(summary = "Вывод всех пользователей")
    public List<UserDTO> userList() {
        return service.getDataProcessingService()
                .getAllUsers().stream()
                .map(dtoMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Добавление нового пользователя.
     * @param requestBody тело запроса.
     * @return подтверждение добавления пользователя.
     */
    @PostMapping("/body")
    @Operation(summary = "Добавление нового пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class),
                            examples = @ExampleObject(name = "Example 1",
                                    value = "{ \"name\": \"Иван\", \"age\": 25, \"email\": \"ivan@mail.ru\" }")
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            examples = @ExampleObject(value = "User added from body!")
                    )
            )
        )
    public ResponseEntity<String> userAddFromBody(
            @RequestBody Map<String,Object> requestBody) {
        // Получаем данные из запроса
        String name = (String) requestBody.get("name");
        Integer age = (Integer) requestBody.get("age");
        String email = (String) requestBody.get("email");

        // Создаем объект dto для последующей валидации
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setAge(age);
        userDTO.setEmail(email);

        // Проведение валидации
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Если в ходе валидации выявлены ошибки отправляем bad request и ошибки
        if(!violations.isEmpty()){
            StringBuilder builder = new StringBuilder("Validations errors:\n");
            violations.forEach(v -> builder.append(v.getMessage()).append("\n"));
            return ResponseEntity.badRequest().body(builder.toString());
        }
        // Иначе подтверждаем регистрацию пользователя
        service.processRegistration(name, age, email);
        return ResponseEntity.ok("User added from body!");
    }
}
