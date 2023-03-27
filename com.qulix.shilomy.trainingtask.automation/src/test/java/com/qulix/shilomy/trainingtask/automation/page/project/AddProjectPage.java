package com.qulix.shilomy.trainingtask.automation.page.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.component.button.impl.CancelButton;
import com.qulix.shilomy.trainingtask.automation.component.button.impl.SaveButton;
import com.qulix.shilomy.trainingtask.automation.model.Project;
import com.qulix.shilomy.trainingtask.automation.page.BasePage;

/**
 * Объектная модель страницы добавления проекта
 */
public class AddProjectPage extends BasePage<AddProjectPage> {

    /**
     * Путь страницы добавления проекта
     */
    private static final String PATH = "/addProject";

    /**
     * Url страницы добавления проекта
     */
    public static final String URL = ROOT_URL + PATH;

    /**
     * Лейбл формы добавления проектов
     */
    @FindBy(xpath = "//label[contains(text(), 'Форма добавления проекта')]")
    private WebElement titleLabel;

    /**
     * Поле названия
     */
    @FindBy(xpath = "//div[contains(.,'Название')]/following::input[@placeholder='Укажите название проекта']")
    private WebElement nameInput;

    /**
     * Поле сокращённого названия
     */
    @FindBy(xpath = "//div[contains(.,'Сокращённое название')]/following::input[@placeholder='Укажите сокращённое название проекта']")
    private WebElement shortnameInput;

    /**
     * Поле описания
     */
    @FindBy(xpath = "//div[contains(.,'Описание')]/following::input[@placeholder='Укажите описание проекта']")
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
     * Лейбл недопустимого символа названия
     */
    @FindBy(xpath = "//label[contains(text(), 'Название содержит недопустимый символ')]")
    private WebElement nameInvalidLabel;

    /**
     * Лейбл недопустимого символа сокращённого названия
     */
    @FindBy(xpath = "//label[contains(text(), 'Сокращённое название содержит недопустимый символ')]")
    private WebElement shortNameInvalidLabel;

    /**
     * Лейбл недопустимого символа описания
     */
    @FindBy(xpath = "//label[contains(text(), 'Описание содержит недопустимый символ')]")
    private WebElement descriptionInvalidLabel;

    /**
     * Лейбл ошибки уникальности названия
     */
    @FindBy(xpath = "//label[contains(text(), 'Проект с таким названием уже существует')]")
    private WebElement nameExistsLabel;

    /**
     * Лейбл ошибки уникальности сокращённого названия
     */
    @FindBy(xpath = "//label[contains(text(), 'Проект с таким сокращенным названием уже существует')]")
    private WebElement shortNameExistsLabel;

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
    public AddProjectPage(WebDriver driver) {
        super(URL, driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов формы
     *
     * @return если все элементы отображены true, иначе false
     */
    public boolean elementsDisplayed() {
        return titleLabel.isDisplayed()
            && nameInput.isDisplayed()
            && shortnameInput.isDisplayed()
            && descriptionInput.isDisplayed()
            && saveButton.isDisplayed()
            && cancelButton.isDisplayed();
    }

    /**
     * Ввод данных из модели проекта
     *
     * @param project проект
     * @return текущее состояние страницы
     */
    public AddProjectPage enterProject(Project project) {
        nameInput.clear();
        shortnameInput.clear();
        descriptionInput.clear();

        nameInput.sendKeys(project.getName());
        shortnameInput.sendKeys(project.getShortName());
        descriptionInput.sendKeys(project.getDescription());

        return this;
    }

    /**
     * Проверка отображения лейбла длины названия
     *
     * @return если лейбл отображён true, иначе false
     */
    public boolean nameLengthLabelDisplayed() {
        return nameLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла длины сокращённого названия
     *
     * @return если лейбл отображён true, иначе false
     */
    public boolean shortNameLengthLabelDisplayed() {
        return shortNameLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла уникальности названия
     *
     * @return если лейбл отображён true, иначе false
     */
    public boolean nameExistsLabelDisplayed() {
        return nameExistsLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла уникальности сокращённого названия
     *
     * @return если лейбл отображён true, иначе false
     */
    public boolean shortNameExistsLabelDisplayed() {
        return shortNameExistsLabel.isDisplayed();
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

    /**
     * Проверка отображения всех лейблов длины
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean allLengthLabelsDisplayed() {
        return nameLengthLabel.isDisplayed()
            && shortNameLengthLabel.isDisplayed()
            && descriptionLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения всех лейблов недопустимого символа
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean allInvalidLabelsDisplayed() {
        return nameInvalidLabel.isDisplayed()
            && shortNameInvalidLabel.isDisplayed()
            && descriptionInvalidLabel.isDisplayed();
    }
}
