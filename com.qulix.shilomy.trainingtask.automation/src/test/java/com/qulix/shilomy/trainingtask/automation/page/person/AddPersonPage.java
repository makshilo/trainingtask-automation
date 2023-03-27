package com.qulix.shilomy.trainingtask.automation.page.person;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.component.button.impl.CancelButton;
import com.qulix.shilomy.trainingtask.automation.component.button.impl.SaveButton;
import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.page.BasePage;

/**
 * Объектная модель страницы добавления персоны
 */
public class AddPersonPage extends BasePage<AddPersonPage> {

    /**
     * Путь страницы добавления персоны
     */
    private static final String PATH = "/addEmployee";

    /**
     * Url страницы добавления персоны
     */
    public static final String URL = ROOT_URL + PATH;

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
     * Кнопка сохранить
     */
    public final SaveButton saveButton = new SaveButton(driver);

    /**
     * Кнопка Отмена
     */
    public final CancelButton cancelButton = new CancelButton(driver);

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public AddPersonPage(WebDriver driver) {
        super(URL, driver);
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
     * Ввод данных из модели персоны
     *
     * @param person персона
     * @return текущее состояние страницы
     */
    public AddPersonPage enterPerson(Person person) {
        surnameInput.clear();
        nameInput.clear();
        patronymicInput.clear();
        positionInput.clear();

        surnameInput.sendKeys(person.getSurname());
        nameInput.sendKeys(person.getName());
        patronymicInput.sendKeys(person.getPatronymic());
        positionInput.sendKeys(person.getPosition());

        return this;
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