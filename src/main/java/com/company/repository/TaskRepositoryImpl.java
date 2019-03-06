package com.company.repository;

import com.company.api.Repository;
import com.company.model.Project;
import com.company.model.Task;
import com.company.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepositoryImpl implements Repository<String, Task> {
    private Map<String, Task> map = new HashMap<>();
    private static Repository<String, Task> taskRepository;

    private TaskRepositoryImpl() {
        Project project = new Project("project1", "project1", new User("admin", "admin", "admin"));
        Task task = new Task("task", "descr", project);
        save(task);
    }

    public static Repository<String, Task> getTaskRepository() {
        if (taskRepository == null) {
            taskRepository = new TaskRepositoryImpl();
        }
        return taskRepository;
    }


    @Override
    public void save(Task object) {
        map.put(object.getId(), object);
    }

    @Override
    public Task findByName(String name) {
        for (Map.Entry<String, Task> pair: map.entrySet()) {
            if (pair.getValue().getName().equals(name)) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public Task update(Task object) {
        return map.put(object.getId(), object);
    }

    @Override
    public boolean removeByName(String name) {
        Task task = findByName(name);
        map.remove(task.getId());
        return true;
    }

    @Override
    public Map<String, Task> getMap() {
        return map;
    }
}