package com.zhelandovskiy_ka.task_service.dto;

import lombok.Data;

@Data
public class TaskCreateDto {
    private String description;
    private long duration; //seconds
}
