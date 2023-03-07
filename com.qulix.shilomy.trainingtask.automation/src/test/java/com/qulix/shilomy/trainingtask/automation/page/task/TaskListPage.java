package com.qulix.shilomy.trainingtask.automation.page.task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;

public class TaskListPage {

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
     * Последний идентификатор задачи
     */
    @FindBy(xpath = "//tbody/tr[last()]/td")
    private WebElement lastId;

    /**
     * Последняя строка таблицы
     */
    @FindBy(xpath = "//tbody/tr[last()]")
    private WebElement lastRow;

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
     * @return объект ProjectListPage
     */
    public ProjectListPage clickDeleteButton() {
        deleteButton.click();
        return new ProjectListPage(driver);
    }

    /**
     * Получение последнего идентификатора задачи
     *
     * @return идентификатор
     */
    public int getLastId() {
        return Integer.parseInt(lastId.getText());
    }

    /**
     * Получение последней строки таблицы
     * @return текст последней строки таблицы
     */
    public String getLastRow() {
        return lastRow.getText();
    }
}
