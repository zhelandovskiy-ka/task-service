package com.zhelandovskiy.t1_task_service.service;

import com.zhelandovskiy.t1_task_service.dto.TaskCreateUpdateDto;
import com.zhelandovskiy.t1_task_service.dto.TaskDto;

import java.util.List;

/**
 * Сервис для управления задачами.
 * Предоставляет методы для получения, создания, обновления и удаления задач.
 */
public interface TaskService {

    /**
     * Возвращает список всех задач.
     *
     * @return список {@link TaskDto} объектов, представляющих задачи.
     * Если задачи отсутствуют, возвращается пустой список.
     */
    List<TaskDto> getAll();

    /**
     * Возвращает задачу по её id.
     *
     * @param id идентификатор задачи.
     * @return объект {@link TaskDto}, представляющий задачу.
     * @throws com.zhelandovskiy.t1_task_service.exception.TaskNotFoundException если задача с указанным id не найдена.
     */
    TaskDto getById(Long id);

    /**
     * Создает новую задачу на основе переданных данных.
     *
     * @param task объект {@link TaskCreateUpdateDto}, содержащий данные для создания задачи.
     * @return объект {@link TaskDto}, представляющий созданную задачу.
     */
    TaskDto create(TaskCreateUpdateDto task);

    /**
     * Обновляет существующую задачу по её id.
     *
     * @param task объект {@link TaskCreateUpdateDto}, содержащий новые данные для задачи.
     * @param id   идентификатор задачи, которую необходимо обновить.
     * @return объект {@link TaskDto}, представляющий обновленную задачу.
     * @throws com.zhelandovskiy.t1_task_service.exception.TaskNotFoundException если задача с указанным идентификатором не найдена.
     */
    TaskDto update(TaskCreateUpdateDto task, Long id);

    /**
     * Удаляет задачу по её id.
     *
     * @param id идентификатор задачи, которую необходимо удалить.
     * @return {@code true}, если задача была успешно удалена.
     * @throws com.zhelandovskiy.t1_task_service.exception.TaskNotFoundException если задача с указанным идентификатором не найдена.
     */
    boolean delete(Long id);
}


