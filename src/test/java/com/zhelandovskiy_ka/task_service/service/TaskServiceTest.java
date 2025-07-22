package com.zhelandovskiy_ka.task_service.service;

import com.zhelandovskiy_ka.task_service.dto.TaskCreateDto;
import com.zhelandovskiy_ka.task_service.entity.Task;
import com.zhelandovskiy_ka.task_service.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void testTaskLifecycle() throws InterruptedException {
        TaskCreateDto request = new TaskCreateDto();
        request.setDescription("Test");
        request.setDuration(2);

        Task task = taskService.createTask(request);
        Assertions.assertEquals(Status.IN_PROGRESS, task.getStatus());
        log.info("Task created: {}", task);

        Thread.sleep(3000);

        Task updated = taskService.getTask(task.getId());
        Assertions.assertEquals(Status.DONE, updated.getStatus());
        log.info("Task after sleep: {}", updated);

        Assertions.assertThrows(IllegalStateException.class, () -> taskService.cancelTask(task.getId()));
    }
}