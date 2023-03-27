package com.qulix.shilomy.trainingtask.automation.page.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qulix.shilomy.trainingtask.automation.component.button.impl.CancelButton;
import com.qulix.shilomy.trainingtask.automation.component.button.impl.SaveButton;
import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.model.Project;
import com.qulix.shilomy.trainingtask.automation.model.Task;
import com.qulix.shilomy.trainingtask.automation.model.TaskStatus;
import com.qulix.shilomy.trainingtask.automation.page.BasePage;
import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;

/**
 * Объектная модель страницы редактирования задачи
 */
public class EditTaskPage extends BasePage<EditTaskPage> {

    /**
     * Путь страницы изменения задачи
     */
    private static final String PATH = "/updateTask";

    /**
     * Url страницы изменения задачи
     */
    public static final String URL = ROOT_URL + PATH;

    private static final String VALUE = "value";
    private static final String INITIAL_DATE_FORMAT = "yyyy-MM-dd";
    private static final String TARGET_DATE_FORMAT = "dd.MM.yyyy";

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
    public EditTaskPage(WebDriver driver) {
        super(URL, driver);
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
     * Ввод данных из модели задачи
     *
     * @param task задача
     * @return текущее состояние страницы
     */
    public EditTaskPage enterTask(Task task) {
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
     * Выбор статуса по индексу
     *
     * @param index индекс
     * @return текущее состояние страницы
     */
    public EditTaskPage selectStatusByIndex(int index) {
        Select statusChoice = new Select(statusSelect);
        statusChoice.selectByIndex(index);
        return this;
    }

    /**
     * Выбор исполнителей из списка
     *
     * @param executors список исполнителей
     * @return текущее состояние страницы
     */
    public EditTaskPage selectExecutorsFromList(List<Person> executors) {
        executorCheckboxes.forEach(c -> executors.forEach(e -> {
            if (c.getAttribute("value").equals(e.getId().toString())) {
                c.click();
            }
        }));
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
     * Выбор проекта по идентификатору
     *
     * @param id идентификатор
     * @return текущее состояние страницы
     */
    public EditTaskPage selectProjectById(Long id) {
        Select projects = new Select(projectSelect);
        projects.selectByValue(id.toString());
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

    /**
     * Получение выбранного проекта
     *
     * @return проект
     */
    public Project getSelectedProject() {
        //Получение идентификатора выбранного проекта
        Select select = new Select(projectSelect);
        Long projectId = Long.parseLong(select.getFirstSelectedOption().getAttribute(VALUE));

        //Переход на страницу списка проектов
        driver.get(ProjectListPage.URL);
        ProjectListPage projectListPage = new ProjectListPage(driver);

        //Проект по идентификатору
        Project project = projectListPage.getProjectById(projectId).orElseThrow();

        driver.navigate().back();
        return project;
    }

    /**
     * Получение выбранных исполнителей
     *
     * @return список исполнителей
     */
    public List<Person> getSelectedExecutors() {
        //Список идентификаторов выбранных персон
        List<Long> executorIds = executorCheckboxes
            .stream()
            .filter(WebElement::isSelected)
            .map(e -> Long.parseLong(e.getAttribute(VALUE)))
            .collect(Collectors.toList());

        //Переход на страницу списка персон
        driver.get(PersonListPage.URL);
        PersonListPage personListPage = new PersonListPage(driver);

        //Список персон по идентификаторам
        List<Person> selectedPersons = executorIds
            .stream()
            .map(e -> personListPage.getPersonById(e).orElseThrow())
            .collect(Collectors.toList());

        driver.navigate().back();
        return selectedPersons;
    }

    /**
     * Получение задачи
     *
     * @return задача с данными со страницы
     */
    public Task getTask() {
        Select status = new Select(statusSelect);

        return new Task(
            Long.parseLong(idInput.getAttribute(VALUE)),
            getSelectedProject(),
            nameInput.getAttribute(VALUE),
            workInput.getAttribute(VALUE),
            LocalDate.parse(startDateInput.getAttribute(VALUE), DateTimeFormatter.ofPattern(INITIAL_DATE_FORMAT))
                .format(DateTimeFormatter.ofPattern(TARGET_DATE_FORMAT)),
            LocalDate.parse(endDateInput.getAttribute(VALUE), DateTimeFormatter.ofPattern(INITIAL_DATE_FORMAT))
                .format(DateTimeFormatter.ofPattern(TARGET_DATE_FORMAT)),
            getSelectedExecutors(),
            TaskStatus.of(status.getFirstSelectedOption().getText()));
    }
}
