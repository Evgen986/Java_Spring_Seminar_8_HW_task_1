package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.dto.DtoMapper;
import com.example.sem3HomeTask.dto.UserDTO;
import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.DataProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Контроллер обработки задач.
 */
@RestController
@RequestMapping("/tasks")
@Tag(name = "Контроллер задач",
        description = "Обеспечивает работу с пользователями")
@AllArgsConstructor
public class TaskController {

    /**
     * Сервис обработки задач.
     */
    private final DataProcessingService service;
    /**
     * Объект преобразования dao в сущность и наоборот.
     */
    private final DtoMapper dtoMapper;

    /**
     * Получение списка поддерживаемых задач.
     * @return список задач.
     */
    @GetMapping
    @Operation(summary = "Вывод доступных задач",
                responses = @ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content = @Content(
                                examples = @ExampleObject(
                                        value = "[ \"sort\",\n  \"filter\",\n  \"calc\"\n]"
                                )
                        )
                ))
    public List<String> getAllTasks()
    {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return  tasks;
    }

    /**
     * Получение списка пользователей отсортированных по возрасту.
     * @return JSON ответ со списком пользователей.
     */
    @GetMapping("/sort")
    @Operation(summary = "Отсортированный по возрасту список пользователей")
    public List<UserDTO> sortUsersByAge()
    {
        return service.sortUsersByAge(service.getAllUsers()).stream()
                .map(dtoMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Получение списка пользователей старше заданного возраста.
     * @param age возраст пользователей.
     * @return JSON ответ со списком пользователей.
     */
    @GetMapping("/filter/{age}")
    @Operation(summary = "Список пользователей старше указанного возраста")
    public List<UserDTO> filterUsersByAge(@PathVariable("age") int age){
        List<User> users = service.getAllUsers();
        return service.filterUsersByAge(users, age).stream()
                .map(dtoMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Получение среднего возраста пользователей.
     * @return JSON ответ со средним возрастом.
     */
    @GetMapping("/calc")
    @Operation(summary = "Средний возраст пользователей")
    public ResponseEntity<Map<String, Double>> calculateAverageAge(){
        List<User> users = service.getAllUsers();
        Map<String, Double> request = new HashMap<>();
        request.put("average", service.calculateAverageAge(users));
        return ResponseEntity.ok().body(request);
    }
}
