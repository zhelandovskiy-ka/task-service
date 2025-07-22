package com.zhelandovskiy_ka.task_service.service;

import com.zhelandovskiy_ka.task_service.dto.TaskCreateDto;
import com.zhelandovskiy_ka.task_service.entity.Task;
import com.zhelandovskiy_ka.task_service.enums.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Task createTask(TaskCreateDto taskCreateDto) {
        long id = counter.incrementAndGet();
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task(id, taskCreateDto.getDescription(), taskCreateDto.getDuration(), Status.IN_PROGRESS, now, now);
        tasks.put(id, task);
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTask(Long id) {
        Task task = tasks.get(id);
        if (task == null) throw new NoSuchElementException("Task not found");
        return task;
    }

    public Task cancelTask(Long id) {
        Task task = getTask(id);
        if (task.getStatus() == Status.DONE)
            throw new IllegalStateException("Cannot cancel DONE task");
        task.setStatus(Status.CANCELED);
        task.setModifiedDate(LocalDateTime.now());
        return task;
    }

    @Scheduled(fixedRate = 1000)
    public void updateTasksStatus() {
        LocalDateTime now = LocalDateTime.now();
        for (Task task : tasks.values()) {
            if (task.getStatus() == Status.IN_PROGRESS) {
                LocalDateTime endTime = task.getCreatedDate().plusSeconds(task.getDuration());
                if (now.isAfter(endTime)) {
                    task.setStatus(Status.DONE);
                    task.setModifiedDate(now);
                }
            }
        }
    }
}
