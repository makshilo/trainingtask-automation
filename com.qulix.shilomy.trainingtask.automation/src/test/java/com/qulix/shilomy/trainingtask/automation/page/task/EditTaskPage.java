package com.qulix.shilomy.trainingtask.automation.page.task;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditTaskPage {

    /**
     * Url страницы изменения задачи
     */
    public static final String URL = "http://localhost:8080/Trainingtask/updateTask";

    /**
     * Лейбл формы добавления задач
     */
    @FindBy(xpath = "//label[contains(text(), 'Форма редактирования задачи')]")
    private WebElement titleLabel;

    /**
     * Поле идентификатора
     */
    @FindBy(xpath = "//div[contains(.,'Идентификатор')]/following::input[@disabled]")
    private WebElement idInput;

    /**
     * Поле Название
     */
    @FindBy(xpath = "//div[contains(.,'Название')]/following::input")
    private WebElement nameInput;

    /**
     * Поле Работа
     */
    @FindBy(xpath = "//div[contains(.,'Работа')]/following::input")
    private WebElement workInput;

    /**
     * Поле Дата начала
     */
    @FindBy(xpath = "//div[contains(.,'Дата начала')]/following::input")
    private WebElement startDateInput;

    /**
     * Поле Дата окончания
     */
    @FindBy(xpath = "//div[contains(.,'Дата окончания')]/following::input")
    private WebElement endDateInput;

    /**
     * Поле Статус
     */
    @FindBy(xpath = "//div[contains(.,'Статус')]/following::select[@name='status']")
    private WebElement statusSelect;

    /**
     * Поле Исполнитель
     */
    @FindBy(xpath = "//div[contains(.,'Исполнитель(и)')]/following::div[contains(type, checkbox)]")
    private WebElement executorField;

    /**
     * Список исполнителей
     */
    @FindBy(xpath = "//div[contains(.,'Исполнитель(и)')]/following::input[@type='checkbox']")
    private List<WebElement> executorCheckboxes;

    /**
     * Поле Проект
     */
    @FindBy(xpath = "//div[contains(.,'Проект')]/following::select[@name='selectProject']")
    private WebElement projectSelect;

    /**
     * Лейбл ошибки длины названия
     */
    @FindBy(xpath = "//label[contains(text(), 'Название не должно быть меньше 2 и больше 255 символов')]")
    private WebElement nameLengthLabel;

    /**
     * Лейбл ошибки длины работы
     */
    @FindBy(xpath = "//label[contains(text(), 'Работа не должна быть меньше 1 и больше 9 символов')]")
    private WebElement workLengthLabel;

    /**
     * Лейбл неверной даты начала
     */
    @FindBy(xpath = "//label[contains(text(), 'Дата начала не может быть раньше 01.01.1990 или позже 31.12.2099')]")
    private WebElement startDateInvalidLabel;

    /**
     * Лейбл неверной даты окончания
     */
    @FindBy(xpath = "//label[contains(text(), 'Дата окончания не может быть раньше 01.01.1990 или позже 31.12.2099')]")
    private WebElement endDateInvalidLabel;

    /**
     * Лейбл неверного статуса
     */
    @FindBy(xpath = "//label[contains(text(), 'Неверный статус')]")
    private WebElement statusInvalidLabel;

    /**
     * Лейбл неверного исполнителя
     */
    @FindBy(xpath = "//label[contains(text(), 'Не выбран исполнитель')]")
    private WebElement executorInvalidLabel;

    /**
     * Кнопка Сохранить
     */
    @FindBy(xpath = "//input[@value='Сохранить']")
    private WebElement saveButton;

    /**
     * Кнопка Отмена
     */
    @FindBy(xpath = "//button[contains(text(), 'Отмена')]")
    private WebElement cancelButton;

    private final WebDriver driver;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public EditTaskPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов формы
     *
     * @return если все элементы отображены true, иначе false
     */
    public boolean elementsDisplayed() {
        return idInput.isDisplayed()
            && titleLabel.isDisplayed()
            && nameInput.isDisplayed()
            && workInput.isDisplayed()
            && startDateInput.isDisplayed()
            && endDateInput.isDisplayed()
            && statusSelect.isDisplayed()
            && executorField.isDisplayed()
            && projectSelect.isDisplayed();
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
    public EditTaskPage enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    /**
     * Ввод работы
     *
     * @param work работа
     * @return текущее состояние страницы
     */
    public EditTaskPage enterWork(String work) {
        workInput.clear();
        workInput.sendKeys(work);
        return this;
    }

    /**
     * Ввод даты начала
     *
     * @param startDate дата начала
     * @return текущее состояние страницы
     */
    public EditTaskPage enterStartDate(String startDate) {
        startDateInput.clear();
        startDateInput.sendKeys(startDate);
        return this;
    }

    /**
     * Ввод даты окончания
     *
     * @param endDate дата окончания
     * @return текущее состояние страницы
     */
    public EditTaskPage enterEndDate(String endDate) {
        endDateInput.clear();
        endDateInput.sendKeys(endDate);
        return this;
    }

    /**
     * Выбор статуса
     *
     * @param status статус
     * @return текущее состояние страницы
     */
    public EditTaskPage selectStatusByText(String status) {
        Select statusChoice = new Select(statusSelect);
        statusChoice.selectByVisibleText(status);
        return this;
    }

    /**
     * Выбор исполнителя по индексу
     *
     * @param index индекс
     * @return текущее состояние страницы
     */
    public EditTaskPage selectExecutorByIndex(int index) {
        executorCheckboxes.get(index).click();
        return this;
    }

    /**
     * Выбор проекта по индексу
     *
     * @param index индекс
     * @return текущее состояние страницы
     */
    public EditTaskPage selectProjectByIndex(int index) {
        Select projectChoice = new Select(projectSelect);
        projectChoice.selectByIndex(index);
        return this;
    }

    /**
     * Удаление данных из полей
     *
     * @return текущее состояние страницы
     */
    public EditTaskPage clearInputs() {
        Select statusChoice = new Select(statusSelect);
        Select projectChoice = new Select(projectSelect);

        nameInput.clear();
        workInput.clear();
        startDateInput.clear();
        endDateInput.clear();
        statusChoice.selectByIndex(0);
        executorCheckboxes.stream().filter(WebElement::isSelected).forEach(WebElement::click);
        projectChoice.selectByIndex(0);
        return this;
    }

    /**
     * Клик по кнопке сохранить
     *
     * @return текущее состояние страницы
     */
    public EditTaskPage clickSaveButton() {
        saveButton.click();
        return this;
    }

    /**
     * Клик по кнопке отмена
     *
     * @return страница списка задач
     */
    public TaskListPage clickCancelButton() {
        cancelButton.click();
        return new TaskListPage(driver);
    }

    /**
     * Проверка отображения всех лейблов
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean allValidationMessagesDisplayed() {
        return nameLengthLabel.isDisplayed()
            && workLengthLabel.isDisplayed()
            && startDateInvalidLabel.isDisplayed()
            && endDateInvalidLabel.isDisplayed()
            && statusInvalidLabel.isDisplayed()
            && executorInvalidLabel.isDisplayed();
    }
}
