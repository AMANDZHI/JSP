package com.company.repository;

import com.company.api.Repository;
import com.company.model.Project;
import com.company.model.User;

import java.util.HashMap;
import java.util.Map;

public class ProjectRepositoryImpl implements Repository<String, Project> {
    private  Map<String, Project> map = new HashMap<>();
    private static Repository<String, Project> projectRepository;

    private ProjectRepositoryImpl() {
        save(new Project("project1", "project1", new User("admin", "admin", "admin")));
        save(new Project("project2", "project2", new User("admin", "admin", "admin")));
    }

    public static Repository<String, Project> getProjectRepository() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepositoryImpl();
        }
        return projectRepository;
    }

    @Override
    public void save(Project object) {
        map.put(object.getId(), object);
    }

    @Override
    public Project findByName(String name) {
        for (Map.Entry<String, Project> pair: map.entrySet()) {
            if (pair.getValue().getName().equals(name)) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public Project update(Project object) {
        return map.put(object.getId(), object);
    }

    @Override
    public boolean removeByName(String name) {
        Project project = findByName(name);
        map.remove(project.getId());
        return true;
    }

    @Override
    public Map<String, Project> getMap() {
        return map;
    }
}