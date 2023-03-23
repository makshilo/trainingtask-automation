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
 * Объектная модель страницы редактирования персоны
 */
public class EditPersonPage extends BasePage {

    /**
     * Путь страницы изменения персоны
     */
    private static final String PATH = "/updateEmployee";

    /**
     * Url страницы изменения персоны
     */
    public static final String URL = ROOT_URL + PATH;

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
    public EditPersonPage(WebDriver driver) {
        super(driver);
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
     * Ввод данных из модели персоны
     *
     * @param person персона
     * @return текущее состояние страницы
     */
    public EditPersonPage enterPerson(Person person) {
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
