package com.qulix.shilomy.trainingtask.automation.page.person;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Объектная модель страницы редактирования персоны
 */
public class EditPersonPage {

    /**
     * Корневой url приложения
     */
    private static final String ROOT_URL_PROPERTY = "rootUrl";

    /**
     * Путь страницы изменения персоны
     */
    private static final String PATH = "/updateEmployee";

    /**
     * Url страницы изменения персоны
     */
    public static final String URL = System.getenv(ROOT_URL_PROPERTY) + PATH;

    /**
     * Заголовочный лейбл формы редактирования
     */
    @FindBy(xpath = "//label[contains(text(), 'Форма редактирования персоны')]")
    private WebElement editPersonTitleLabel;

    /**
     * Поле идентификатора
     */
    @FindBy(xpath = "//div[contains(.,'Идентификатор')]/following::input[@disabled]")
    private WebElement idInput;

    /**
     * Поле фамилии
     */
    @FindBy(xpath = "//div[contains(.,'Фамилия')]/following::input")
    private WebElement surnameInput;

    /**
     * Поле имени
     */
    @FindBy(xpath = "//div[contains(.,'Имя')]/following::input")
    private WebElement nameInput;

    /**
     * Поле отчества
     */
    @FindBy(xpath = "//div[contains(.,'Отчество')]/following::input")
    private WebElement patronymicInput;

    /**
     * Поле должности
     */
    @FindBy(xpath = "//div[contains(.,'Должность')]/following::input")
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
    public EditPersonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов формы редактирования персоны
     *
     * @return если все элементы отображены true, иначе false
     */
    public boolean elementsDisplayed() {
        return editPersonTitleLabel.isDisplayed()
            && idInput.isDisplayed()
            && surnameInput.isDisplayed()
            && nameInput.isDisplayed()
            && patronymicInput.isDisplayed()
            && positionInput.isDisplayed()
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
     * Ввод фамилии
     *
     * @param surname фамилия
     * @return текущее состояние страницы
     */
    public EditPersonPage enterSurname(String surname) {
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
    public EditPersonPage enterName(String name) {
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
    public EditPersonPage enterPatronymic(String patronymic) {
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
    public EditPersonPage enterPosition(String position) {
        positionInput.clear();
        positionInput.sendKeys(position);
        return this;
    }

    /**
     * Клик по кнопке сохранения персоны
     *
     * @return текущее состояние страницы
     */
    public EditPersonPage clickSaveButton() {
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
     * Удаление данных из полей
     *
     * @return текущее состояние страницы
     */
    public EditPersonPage clearInputs() {
        surnameInput.clear();
        nameInput.clear();
        patronymicInput.clear();
        positionInput.clear();
        return this;
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
}
