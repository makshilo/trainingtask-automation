package com.qulix.shilomy.trainingtask.automation.model;

/**
 * Статусы задачи
 */
public enum TaskStatus {
    NOT_STARTED("Не начата"),
    IN_PROCESS("В процессе"),
    COMPLETED("Завершена"),
    POSTPONED("Отложена");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    /**
     * Метод получения статуса по имени
     *
     * @param name имя статуса
     * @return status
     */
    public static TaskStatus of(String name) {
        switch (name) {
            case "Не начата":
                return NOT_STARTED;
            case "В процессе":
                return IN_PROCESS;
            case "Завершена":
                return COMPLETED;
            case "Отложена":
                return POSTPONED;
            default:
                throw new IllegalArgumentException("Неизвестный статус: " + name);
        }
    }

    /**
     * Метод получения строки из объекта
     *
     * @return поле статуса
     */
    public String getStatus() {
        return status;
    }
}