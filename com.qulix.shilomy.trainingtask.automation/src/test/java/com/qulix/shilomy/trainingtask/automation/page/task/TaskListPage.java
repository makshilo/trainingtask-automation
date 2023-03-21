package com.qulix.shilomy.trainingtask.automation.page.task;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.model.Task;

public class TaskListPage {

    /**
     * Корневой url приложения
     */
    private static final String ROOT_URL_PROPERTY = "rootUrl";

    /**
     * Путь списка задач
     */
    private static final String PATH = "/tasks";

    /**
     * Url списка задач
     */
    public static final String URL = System.getenv(ROOT_URL_PROPERTY) + PATH;

    /**
     * Колонка Идентификатор
     */
    @FindBy(xpath = "//th[contains(text(), 'Идентификатор')]")
    private WebElement idColumn;

    /**
     * Колонка Проект
     */
    @FindBy(xpath = "//th[contains(text(), 'Проект')]")
    private WebElement projectColumn;

    /**
     * Колонка Название
     */
    @FindBy(xpath = "//th[contains(text(), 'Название')]")
    private WebElement nameColumn;

    /**
     * Колонка Дата начала
     */
    @FindBy(xpath = "//th[contains(text(), 'Дата начала')]")
    private WebElement startDateColumn;

    /**
     * Колонка Дата окончания
     */
    @FindBy(xpath = "//th[contains(text(), 'Дата окончания')]")
    private WebElement endDateColumn;

    /**
     * Колонка Исполнитель(и)
     */
    @FindBy(xpath = "//th[contains(text(), 'Исполнитель(и)')]")
    private WebElement executorColumn;

    /**
     * Колонка Статус
     */
    @FindBy(xpath = "//th[contains(text(), 'Статус')]")
    private WebElement statusColumn;

    /**
     * Кнопка Добавить
     */
    @FindBy(xpath = "//button[contains(text(),'Добавить')]")
    private WebElement addButton;

    /**
     * Кнопка Изменить
     */
    @FindBy(xpath = "//tbody/tr[last()]//button[contains(text(), 'Изменить')]")
    private WebElement editButton;

    /**
     * Кнопка Удалить
     */
    @FindBy(xpath = "//tbody/tr[last()]//button[contains(text(), 'Удалить')]")
    private WebElement deleteButton;

    /**
     * Строка таблицы
     */
    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> tableRows;
    
    private static final String EDIT_BUTTON = "//tbody/tr[%d]//button[contains(text(), 'Изменить')]";

    private final WebDriver driver;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public TaskListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов страницы
     *
     * @return если все элементы отображены true, иначе false
     */
    public boolean elementsDisplayed() {
        return idColumn.isDisplayed()
            && projectColumn.isDisplayed()
            && nameColumn.isDisplayed()
            && startDateColumn.isDisplayed()
            && endDateColumn.isDisplayed()
            && executorColumn.isDisplayed()
            && statusColumn.isDisplayed()
            && addButton.isDisplayed()
            && editButton.isDisplayed()
            && deleteButton.isDisplayed();
    }

    /**
     * Клик по кнопке добавления задачи
     *
     * @return объект AddTaskPage
     */
    public AddTaskPage clickAddButton() {
        addButton.click();
        return new AddTaskPage(driver);
    }

    /**
     * Клик по кнопке Изменить
     *
     * @return объект EditProjectPage
     */
    public EditTaskPage clickEditButton() {
        editButton.click();
        return new EditTaskPage(driver);
    }

    /**
     * Клик по кнопке Удалить
     *
     * @return объект TaskListPage
     */
    public TaskListPage clickDeleteButton() {
        deleteButton.click();
        return new TaskListPage(driver);
    }

    /**
     * Получение задачи по индексу
     *
     * @param index индекс
     * @return задача
     */
    public Task getTaskByIndex(int index) {
        driver.findElement(By.xpath(String.format(EDIT_BUTTON, index))).click();
        EditTaskPage editTaskPage = new EditTaskPage(driver);
        Task task = editTaskPage.getTask();
        driver.navigate().back();
        return task;
    }

    /**
     * Получение последнего идентификатора задачи
     *
     * @return идентификатор
     */
    public Task getLastTask() {
        return getTaskByIndex(tableRows.size());
    }
}
