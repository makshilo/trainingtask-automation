package com.qulix.shilomy.trainingtask.automation.model;

import java.util.Objects;

/**
 * Проект
 */
public class Project {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Название
     */
    private String name;

    /**
     * Сокращенное название
     */
    private String shortName;

    /**
     * Описание
     */
    private String description;

    public Project(Long id, String name, String shortName, String description) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
    }

    public Project(String name, String shortName, String description) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Project project = (Project) o;

        if (!Objects.equals(id, project.id)) {
            return false;
        }
        if (!name.equals(project.name)) {
            return false;
        }
        if (!shortName.equals(project.shortName)) {
            return false;
        }
        return Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
