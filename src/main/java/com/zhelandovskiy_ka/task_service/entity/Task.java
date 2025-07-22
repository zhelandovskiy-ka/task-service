package com.zhelandovskiy_ka.task_service.entity;

import com.zhelandovskiy_ka.task_service.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String description;
    private long duration;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
