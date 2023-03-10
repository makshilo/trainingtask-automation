package com.qulix.shilomy.trainingtask.automation.page.person;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Объектная модель страницы добавления персоны
 */
public class AddPersonPage {

    /**
     * Url страницы добавления персоны
     */
    public static final String URL = "http://localhost:8080/Trainingtask/addEmployee";

    /**
     * Заголовочный лейбл формы добавления
     */
    @FindBy(xpath = "//label[contains(text(), 'Форма добавления персон')]")
    private WebElement addPersonTitleLabel;

    /**
     * Поле ввода фамилии
     */
    @FindBy(xpath = "//div[contains(.,'Фамилия')]/following::input[@placeholder='Введите Фамилию']")
    private WebElement surnameInput;

    /**
     * Поле ввода имени
     */
    @FindBy(xpath = "//div[contains(.,'Имя')]/following::input[@placeholder='Введите имя']")
    private WebElement nameInput;

    /**
     * Поле ввода отчества
     */
    @FindBy(xpath = "//div[contains(.,'Отчество')]/following::input[@placeholder='Введите отчество']")
    private WebElement patronymicInput;

    /**
     * Поле ввода должности
     */
    @FindBy(xpath = "//div[contains(.,'Должность')]/following::input[@placeholder='Введите должность']")
    private WebElement positionInput;

    /**
     * Лейбл ошибки длины фамилии
     */
    @FindBy(xpath = "//label[contains(text(), 'Фамилия не может быть меньше 2 и больше 30 символов')]")
    private WebElement surnameLengthLabel;

    /**
     * Лейбл ошибки длины имени
     */
    @FindBy(xpath = "//label[contains(text(), 'Имя не может быть меньше 2 и больше 30 символов')]")
    private WebElement nameLengthLabel;

    /**
     * Лейбл ошибки длины отчества
     */
    @FindBy(xpath = "//label[contains(text(), 'Отчество не может быть меньше 2 и больше 30 символов')]")
    private WebElement patronymicLengthLabel;

    /**
     * Лейбл ошибки длины должности
     */
    @FindBy(xpath = "//label[contains(text(), 'Должность не может быть меньше 2 и больше 30 символов')]")
    private WebElement positionLengthLabel;

    /**
     * Лейбл недопустимого символа фамилии
     */
    @FindBy(xpath = "//label[contains(text(), 'Фамилия содержит недопустимый символ')]")
    private WebElement surnameInvalidLabel;

    /**
     * Лейбл недопустимого символа имени
     */
    @FindBy(xpath = "//label[contains(text(), 'Имя содержит недопустимый символ')]")
    private WebElement nameInvalidLabel;

    /**
     * Лейбл недопустимого символа отчества
     */
    @FindBy(xpath = "//label[contains(text(), 'Отчество содержит недопустимый символ')]")
    private WebElement patronymicInvalidLabel;

    /**
     * Лейбл недопустимого символа должности
     */
    @FindBy(xpath = "//label[contains(text(), 'Должность содержит недопустимый символ')]")
    private WebElement positionInvalidLabel;

    /**
     * Кнопка сохранения персоны
     */
    @FindBy(xpath = "//input[@value='Сохранить']")
    private WebElement saveButton;

    /**
     * Кнопка отмены сохранения персоны
     */
    @FindBy(xpath = "//button[contains(text(), 'Отмена')]")
    private WebElement cancelButton;

    private final WebDriver driver;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public AddPersonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов формы добавления персоны
     *
     * @return если все элементы отображены true, иначе false
     */
    public boolean elementsDisplayed() {
        return addPersonTitleLabel.isDisplayed()
            && surnameInput.isDisplayed()
            && nameInput.isDisplayed()
            && patronymicInput.isDisplayed()
            && positionInput.isDisplayed()
            && saveButton.isDisplayed()
            && cancelButton.isDisplayed();
    }

    /**
     * Ввод фамилии
     *
     * @param surname фамилия
     * @return текущее состояние страницы
     */
    public AddPersonPage enterSurname(String surname) {
        surnameInput.clear();
        surnameInput.sendKeys(surname);
        return this;
    }

    /**
     * Ввод имени
     *
     * @param name имя
     * @return текущее состояние страницы
     */
    public AddPersonPage enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    /**
     * Ввод отчества
     *
     * @param patronymic отчество
     * @return текущее состояние страницы
     */
    public AddPersonPage enterPatronymic(String patronymic) {
        patronymicInput.clear();
        patronymicInput.sendKeys(patronymic);
        return this;
    }

    /**
     * Ввод должности
     *
     * @param position должность
     * @return текущее состояние страницы
     */
    public AddPersonPage enterPosition(String position) {
        positionInput.clear();
        positionInput.sendKeys(position);
        return this;
    }

    /**
     * Клик по кнопке сохранения персоны
     *
     * @return текущее состояние страницы
     */
    public AddPersonPage clickSaveButton() {
        saveButton.click();
        return this;
    }

    /**
     * Клик по кнопке отмены
     *
     * @return страница списка персон
     */
    public PersonListPage clickCancelButton() {
        cancelButton.click();
        return new PersonListPage(driver);
    }

    /**
     * Проверка отображения лейбла длины фамилии
     *
     * @return если лейбл отображён true, иначе false
     */
    public boolean surnameLengthLabelDisplayed() {
        return surnameLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла длины имени
     *
     * @return если лейбл отображён true, иначе false
     */
    public boolean nameLengthLabelDisplayed() {
        return nameLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла длины отчества
     *
     * @return если лейбл отображён true, иначе false
     */
    public boolean patronymicLengthLabelDisplayed() {
        return patronymicLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла длины должности
     *
     * @return если лейбл отображён true, иначе false
     */
    public boolean positionLengthLabelDisplayed() {
        return positionLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения всех лейблов длины
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean allLengthLabelsDisplayed() {
        return surnameLengthLabel.isDisplayed()
            && nameLengthLabel.isDisplayed()
            && patronymicLengthLabel.isDisplayed()
            && positionLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения всех лейблов недопустимого символа
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean allInvalidLabelsDisplayed() {
        return surnameInvalidLabel.isDisplayed()
            && nameInvalidLabel.isDisplayed()
            && patronymicInvalidLabel.isDisplayed()
            && positionInvalidLabel.isDisplayed();
    }
}