package com.qulix.shilomy.trainingtask.automation.model;

import java.util.List;
import java.util.Objects;

/**
 * Задача
 */
public class Task {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Название
     */
    private String name;

    /**
     * Работа
     */
    private String work;

    /**
     * Дата начала
     */
    private String startDate;

    /**
     * Дата окончания
     */
    private String endDate;

    /**
     * Статус
     */
    private TaskStatus status;

    /**
     * Исполнители
     */
    private List<Person> executors;

    /**
     * Проект
     */
    private Project project;

    public Task(Project project, String name, String work, String startDate, String endDate, List<Person> executors, TaskStatus status) {
        this.project = project;
        this.name = name;
        this.work = work;
        this.startDate = startDate;
        this.endDate = endDate;
        this.executors = executors;
        this.status = status;
    }

    public Task(Long id, Project project, String name, String work, String startDate, String endDate, List<Person> executors, TaskStatus status) {
        this.id = id;
        this.project = project;
        this.name = name;
        this.work = work;
        this.startDate = startDate;
        this.endDate = endDate;
        this.executors = executors;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWork() {
        return work;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public List<Person> getExecutors() {
        return executors;
    }

    public Project getProject() {
        return project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setExecutors(List<Person> executors) {
        this.executors = executors;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (!Objects.equals(id, task.id)) {
            return false;
        }
        if (!name.equals(task.name)) {
            return false;
        }
        if (!work.equals(task.work)) {
            return false;
        }
        if (!startDate.equals(task.startDate)) {
            return false;
        }
        if (!endDate.equals(task.endDate)) {
            return false;
        }
        if (status != task.status) {
            return false;
        }
        if (!executors.equals(task.executors)) {
            return false;
        }
        return project.equals(task.project);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + work.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + executors.hashCode();
        result = 31 * result + project.hashCode();
        return result;
    }
}
