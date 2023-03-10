package com.qulix.shilomy.trainingtask.automation.page.task;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddTaskPage {

    /**
     * Url страницы добавления задачи
     */
    public static final String URL = "http://localhost:8080/Trainingtask/addTask";

    /**
     * Лейбл формы добавления задач
     */
    @FindBy(xpath = "//label[contains(text(), 'Форма добавления задания')]")
    private WebElement titleLabel;

    /**
     * Поле Название
     */
    @FindBy(xpath = "//div[contains(.,'Название')]/following::input[@placeholder='Поле для ввода названия']")
    private WebElement nameInput;

    /**
     * Поле Работа
     */
    @FindBy(xpath = "//div[contains(.,'Работа')]/following::input[@placeholder='Поле для ввода времени']")
    private WebElement workInput;

    /**
     * Поле Дата начала
     */
    @FindBy(xpath = "//div[contains(.,'Дата начала')]/following::input[@type='date']")
    private WebElement startDateInput;

    /**
     * Поле Дата окончания
     */
    @FindBy(xpath = "//div[contains(.,'Дата окончания')]/following::input[@type='date']")
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
     * Лейбл недопустимого символа названия
     */
    @FindBy(xpath = "//label[contains(text(), 'Название содержит недопустимый символ')]")
    private WebElement nameInvalidLabel;

    /**
     * Лейбл недопустимого символа работы
     */
    @FindBy(xpath = "//label[contains(text(), 'Работа содержит недопустимый символ')]")
    private WebElement workInvalidLabel;

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
     * Лейбл пересечения дат
     */
    @FindBy(xpath = "//label[contains(text(), 'Дата окончания не может быть раньше даты начала!')]")
    private WebElement dateCollisionLabel;

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
     * Лейбл неверного проекта
     */
    @FindBy(xpath = "//label[contains(text(), 'Не выбран проект')]")
    private WebElement projectInvalidLabel;

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

    public AddTaskPage(WebDriver driver) {
        this.driver = driver;
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
            && workInput.isDisplayed()
            && startDateInput.isDisplayed()
            && endDateInput.isDisplayed()
            && statusSelect.isDisplayed()
            && executorField.isDisplayed()
            && projectSelect.isDisplayed();
    }

    /**
     * Ввод названия
     *
     * @param name название
     * @return текущее состояние страницы
     */
    public AddTaskPage enterName(String name) {
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
    public AddTaskPage enterWork(String work) {
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
    public AddTaskPage enterStartDate(String startDate) {
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
    public AddTaskPage enterEndDate(String endDate) {
        endDateInput.clear();
        endDateInput.sendKeys(endDate);
        return this;
    }

    /**
     * Выбор статуса по тексту
     *
     * @param status статус
     * @return текущее состояние страницы
     */
    public AddTaskPage selectStatusByText(String status) {
        Select statusChoice = new Select(statusSelect);
        statusChoice.selectByVisibleText(status);
        return this;
    }

    /**
     * Получение выбранного статуса
     *
     * @return статус
     */
    public String getSelectedStatus() {
        Select statusChoice = new Select(statusSelect);
        return statusChoice.getFirstSelectedOption().getText();
    }

    /**
     * Выбор исполнителя по индексу
     *
     * @param index индекс
     * @return текущее состояние страницы
     */
    public AddTaskPage selectExecutorByIndex(int index) {
        executorCheckboxes.get(index - 1).click();
        return this;
    }

    /**
     * Выбор всех тсполнителей
     *
     * @return текущее состояние страницы
     */
    public AddTaskPage selectAllExecutors() {
        executorCheckboxes.forEach(WebElement::click);
        return this;
    }

    /**
     * Выбор проекта по индексу
     *
     * @param index индекс
     * @return текущее состояние страницы
     */
    public AddTaskPage selectProjectByIndex(int index) {
        Select projectChoice = new Select(projectSelect);
        projectChoice.selectByIndex(index);
        return this;
    }

    /**
     * Получение выбранного проекта
     *
     * @return проект
     */
    public String getSelectedProject() {
        Select projectChoice = new Select(projectSelect);
        return projectChoice.getFirstSelectedOption().getText();
    }

    /**
     * Клик по кнопке сохранить
     *
     * @return текущее состояние страницы
     */
    public AddTaskPage clickSaveButton() {
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
     * Проверка отображения лейбла ошибки названия
     *
     * @return усли лейбл отображён true, иначе false
     */
    public boolean nameLengthLabelDisplayed() {
        return nameLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла ошибки работы
     *
     * @return усли лейбл отображён true, иначе false
     */
    public boolean workLengthLabelDisplayed() {
        return workLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла ошибки даты начала
     *
     * @return усли лейбл отображён true, иначе false
     */
    public boolean startDateInvalidLabelDisplayed() {
        return startDateInvalidLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла ошибки даты окончания
     *
     * @return усли лейбл отображён true, иначе false
     */
    public boolean endDateInvalidLabelDisplayed() {
        return endDateInvalidLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла ошибки статуса
     *
     * @return усли лейбл отображён true, иначе false
     */
    public boolean statusInvalidLabelDisplayed() {
        return statusInvalidLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла ошибки исполнителя
     *
     * @return усли лейбл отображён true, иначе false
     */
    public boolean executorInvalidLabelDisplayed() {
        return executorInvalidLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла ошибки проекта
     *
     * @return усли лейбл отображён true, иначе false
     */
    public boolean projectInvalidLabelDisplayed() {
        return projectInvalidLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейблов неверного символа названия и работы
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean nameWorkInvalidLabelDisplayed() {
        return nameInvalidLabel.isDisplayed()
            && workInvalidLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейблов неверного символа названия и работы
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean nameWorkLengthLabelDisplayed() {
        return nameLengthLabel.isDisplayed()
            && workLengthLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейбла пересечения дат
     *
     * @return усли лейбл отображён true, иначе false
     */
    public boolean dateCollisionLabelDisplayed() {
        return dateCollisionLabel.isDisplayed();
    }

    /**
     * Проверка отображения лейблов неверных дат
     *
     * @return если все лейблы отображены true, иначе false
     */
    public boolean allDateInvalidLabelsDisplayed() {
        return startDateInvalidLabel.isDisplayed()
            && endDateInvalidLabel.isDisplayed();
    }
}
