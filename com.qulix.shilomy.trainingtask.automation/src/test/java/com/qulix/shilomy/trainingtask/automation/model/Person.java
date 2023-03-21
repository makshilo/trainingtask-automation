package com.qulix.shilomy.trainingtask.automation.model;

import java.util.Objects;

/**
 * Персона
 */
public class Person {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Имя
     */
    private String name;

    /**
     * Отчество
     */
    private String patronymic;

    /**
     * Должность
     */
    private String position;

    public Person(Long id, String surname, String name, String patronymic, String position) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.position = position;
    }

    public Person(String surname, String name, String patronymic, String position) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        if (!Objects.equals(id, person.id)) {
            return false;
        }
        if (!surname.equals(person.surname)) {
            return false;
        }
        if (!name.equals(person.name)) {
            return false;
        }
        if (!patronymic.equals(person.patronymic)) {
            return false;
        }
        return position.equals(person.position);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + surname.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + patronymic.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }
}
