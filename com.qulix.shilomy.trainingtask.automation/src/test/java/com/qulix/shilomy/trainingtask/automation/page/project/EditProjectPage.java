package com.qulix.shilomy.trainingtask.automation.page.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProjectPage {

    /**
     * Url страницы изменения проекта
     */
    public static final String URL = "http://localhost:8080/Trainingtask/updateProject";

    /**
     * Лейбл формы добавления проектов
     */
    @FindBy(xpath = "//label[contains(text(), 'Форма редактирования проекта')]")
    private WebElement titleLabel;

    /**
     * Поле идентификатора
     */
    @FindBy(xpath = "//div[contains(.,'Идентификатор')]/following::input[@disabled]")
    private WebElement idInput;

    /**
     * Поле названия
     */
    @FindBy(xpath = "//div[contains(.,'Название')]/following::input")
    private WebElement nameInput;

    /**
     * Поле сокращённого названия
     */
    @FindBy(xpath = "//div[contains(.,'Сокращённое название')]/following::input")
    private WebElement shortnameInput;

    /**
     * Поле описания
     */
    @FindBy(xpath = "//div[contains(.,'Описание')]/following::input")
    private WebElement descriptionInput;

    /**
     * Лейбл ошибки длины названия
     */
    @FindBy(xpath = "//label[contains(text(), 'Название не должно быть меньше 2 и больше 50 символов')]")
    private WebElement nameLengthLabel;

    /**
     * Лейбл ошибки длины сокращённого названия
     */
    @FindBy(xpath = "//label[contains(text(), 'Сокращённое название не должно быть меньше 2 и больше 40 символов')]")
    private WebElement shortNameLengthLabel;

    /**
     * Лейбл ошибки длины описания
     */
    @FindBy(xpath = "//label[contains(text(), 'Описание не должно превышать 255 символов')]")
    private WebElement descriptionLengthLabel;

    /**
     * Лейбл недопустимого символа описания
     */
    @FindBy(xpath = "//label[contains(text(), 'Описание содержит недопустимый символ')]")
    private WebElement descriptionInvalidLabel;

    /**
     * Кнопка сохранить
     */
    @FindBy(xpath = "//input[@value='Сохранить']")
    private WebElement saveButton;

    /**
     * Кнопка отмена
     */
    @FindBy(xpath = "//button[contains(text(), 'Отмена')]")
    private WebElement cancelButton;

    private final WebDriver driver;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public EditProjectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов формы добавления проекта
     *
     * @return если все элементы отображены true, иначе false
     */
    public boolean elementsDisplayed() {
        return titleLabel.isDisplayed()
            && idInput.isDisplayed()
            && nameInput.isDisplayed()
            && shortnameInput.isDisplayed()
            && descriptionInput.isDisplayed()
            && saveButton.isDisplayed()
            && cancelButton.isDisplayed();
    }

    /**
     * Проверка доступности поля идентификатора
     *
     * @return true если поле включено, иначе false
     */
    public boolean isIdInputEnabled() {
        return idInput.isEnabled();
    }

    /**
     * Ввод названия
     *
     * @param name название
     * @return текущее состояние страницы
     */
    public EditProjectPage enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    /**
     * Ввод сокращённого имени
     *
     * @param shortname сокращённое имя
     * @return текущее состояние страницы
     */
    public EditProjectPage enterShortName(String shortname) {
        shortnameInput.clear();
        shortnameInput.sendKeys(shortname);
        return this;
    }

    /**
     * Ввод описания
     *
     * @param description описание
     * @return текущее состояние страницы
     */
    public EditProjectPage enterDescription(String description) {
        descriptionInput.clear();
        descriptionInput.sendKeys(description);
        return this;
    }

    /**
     * Клик по кнопке сохранить
     *
     * @return текущее состояние страницы
     */
    public EditProjectPage clickSaveButton() {
        saveButton.click();
        return this;
    }

    /**
     * Клик по кнопке отмена
     *
     * @return текущее состояние страницы
     */
    public ProjectListPage clickCancelButton() {
        cancelButton.click();
        return new ProjectListPage(driver);
    }

    /**
     * Удаление данных из полей
     *
     * @return текущее состояние страницы
     */
    public EditProjectPage clearInputs() {
        nameInput.clear();
        shortnameInput.clear();
        descriptionInput.clear();
        return this;
    }

    /**
     * Проверка отображения минимальных лейблов длины
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean minLengthLabelsDisplayed() {
        return nameLengthLabel.isDisplayed()
            && shortNameLengthLabel.isDisplayed();
    }
}
