package com.qulix.shilomy.trainingtask.automation.page.task;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qulix.shilomy.trainingtask.automation.component.button.impl.CancelButton;
import com.qulix.shilomy.trainingtask.automation.component.button.impl.SaveButton;
import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.model.Task;
import com.qulix.shilomy.trainingtask.automation.page.BasePage;

/**
 * Объектная модель страницы добавления задачи
 */
public class AddTaskPage extends BasePage<AddTaskPage> {

    /**
     * Путь страницы добавления задачи
     */
    private static final String PATH = "/addTask";

    /**
     * Url страницы добавления задачи
     */
    public static final String URL = ROOT_URL + PATH;

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
     * Кнопка сохранить
     */
    public final SaveButton saveButton = new SaveButton(driver);

    /**
     * Кнопка Отмена
     */
    public final CancelButton cancelButton = new CancelButton(driver);

    private static final String VALUE = "value";

    public AddTaskPage(WebDriver driver) {
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
            && workInput.isDisplayed()
            && startDateInput.isDisplayed()
            && endDateInput.isDisplayed()
            && statusSelect.isDisplayed()
            && executorField.isDisplayed()
            && projectSelect.isDisplayed();
    }

    /**
     * Ввод данных из модели задачи
     *
     * @param task задача
     * @return текущее состояние страницы
     */
    public AddTaskPage enterTask(Task task) {
        nameInput.clear();
        workInput.clear();
        startDateInput.clear();
        endDateInput.clear();

        nameInput.sendKeys(task.getName());
        workInput.sendKeys(task.getWork());
        startDateInput.sendKeys(task.getStartDate());
        endDateInput.sendKeys(task.getEndDate());
        Optional.ofNullable(task.getStatus()).ifPresentOrElse(status -> selectStatusByText(status.getStatus()), () -> selectStatusByIndex(0));
        selectExecutorsFromList(task.getExecutors());
        Optional.ofNullable(task.getProject()).ifPresentOrElse(project -> selectProjectById(project.getId()), () -> selectProjectByIndex(0));

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
     * Выбор статуса по индексу
     *
     * @param index индекс
     * @return текущее состояние страницы
     */
    public AddTaskPage selectStatusByIndex(int index) {
        Select statusChoice = new Select(statusSelect);
        statusChoice.selectByIndex(index);
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
     * Выбор исполнителей из списка
     *
     * @param executors список исполнителей
     * @return текущее состояние страницы
     */
    public AddTaskPage selectExecutorsFromList(List<Person> executors) {
        executorCheckboxes.forEach(c -> executors.forEach(e -> {
            if (c.getAttribute(VALUE).equals(e.getId().toString())) {
                c.click();
            }
        }));
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
     * Выбор проекта по идентификатору
     *
     * @param id идентификатор
     * @return текущее состояние страницы
     */
    public AddTaskPage selectProjectById(Long id) {
        Select projects = new Select(projectSelect);
        projects.selectByValue(id.toString());
        return this;
    }

    /**
     * Получение идентификатора выбранного проекта
     *
     * @return идентификатор
     */
    public Long getSelectedProjectId() {
        Select projectChoice = new Select(projectSelect);
        return Long.parseLong(projectChoice.getFirstSelectedOption().getAttribute(VALUE));
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
