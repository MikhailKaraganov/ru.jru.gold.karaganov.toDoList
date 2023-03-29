package ru.jru.golf.karaganov.contoller;

import lombok.Data;
import ru.jru.golf.karaganov.entity.Status;

@Data
public class TaskInfo {
    private String description;
    private Status status;
}
