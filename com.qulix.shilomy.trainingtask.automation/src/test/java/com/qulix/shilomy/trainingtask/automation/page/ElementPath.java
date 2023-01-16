package com.qulix.shilomy.trainingtask.automation.page;

/**
 * Пути к элементам страниц
 */
public class ElementPath {
    /**
     * Ссылка на список персон
     */
    public static final String PERSONS_LINK = "//a[@href='/trainingtask1/employees']";

    /**
     * Кнопка добавления персоны
     */
    public static final String ADD_PERSON_BUTTON = "//a[@href='/trainingtask1/addEmployee']";

    /**
     * Последний идентификатор персоны
     */
    public static final String LAST_PERSON_ID = "//tbody/tr[last()]/td";

    /**
     * Лейбл форма добавления персон
     */
    public static final String TITLE_LABEL = "//label[contains(text(), 'Форма добавления персон')]";

    /**
     * Текстовое поле фамилии
     */
    public static final String SURNAME_INPUT = "//div[contains(.,'Фамилия')]/following::input[@placeholder='Введите Фамилию']";

    /**
     * Текстовое поле имени
     */
    public static final String NAME_INPUT = "//div[contains(.,'Имя')]/following::input[@placeholder='Введите имя']";

    /**
     * Текстовое поле отчества
     */
    public static final String PATRONYMIC_INPUT = "//div[contains(.,'Отчество')]/following::input[@placeholder='Введите отчество']";

    /**
     * Текстовое поле должности
     */
    public static final String POSITION_INPUT = "//div[contains(.,'Должность')]/following::input[@placeholder='Введите должность']";

    /**
     * Лейбл ошибки должности
     */
    public static final String POSITION_ERROR_LABEL = "//label[contains(text(), 'Должность содержит недопустимый символ')]";

    /**
     * Кнопка сохранения персоны
     */
    public static final String SAVE_PERSON_BUTTON = "//input[@value='Сохранить']";

    /**
     * Кнопка отмены сохранения персоны
     */
    public static final String CANCEL_PERSON_BUTTON = "//button[contains(text(), 'Отмена')]";
}
