package com.zhelandovskiy_ka.task_service.controller;

import com.zhelandovskiy_ka.task_service.service.TaskService;
import com.zhelandovskiy_ka.task_service.dto.TaskCreateDto;
import com.zhelandovskiy_ka.task_service.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Task create(@RequestBody TaskCreateDto taskCreateDto) {
        return taskService.createTask(taskCreateDto);
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @DeleteMapping("/{id}")
    public Task cancel(@PathVariable Long id) {
        return taskService.cancelTask(id);
    }
}
