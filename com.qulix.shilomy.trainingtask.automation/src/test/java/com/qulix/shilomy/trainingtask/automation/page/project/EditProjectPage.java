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
 * Объектная модель страницы редактирования проекта
 */
public class EditProjectPage extends BasePage {

    /**
     * Путь страницы изменения проекта
     */
    private static final String PATH = "/updateProject";

    /**
     * Url страницы изменения проекта
     */
    public static final String URL = ROOT_URL + PATH;

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
    public EditProjectPage(WebDriver driver) {
        super(driver);
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
     * Ввод данных из модели проекта
     *
     * @param project проект
     * @return текущее состояние страницы
     */
    public EditProjectPage enterProject(Project project) {
        nameInput.clear();
        shortnameInput.clear();
        descriptionInput.clear();

        nameInput.sendKeys(project.getName());
        shortnameInput.sendKeys(project.getShortName());
        descriptionInput.sendKeys(project.getDescription());

        return this;
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
