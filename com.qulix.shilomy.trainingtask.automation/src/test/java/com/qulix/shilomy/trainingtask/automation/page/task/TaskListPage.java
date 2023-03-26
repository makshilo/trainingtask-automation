package com.qulix.shilomy.trainingtask.automation.page.task;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.component.button.impl.AddButton;
import com.qulix.shilomy.trainingtask.automation.component.button.impl.DeleteButton;
import com.qulix.shilomy.trainingtask.automation.component.button.impl.EditButton;
import com.qulix.shilomy.trainingtask.automation.model.Task;
import com.qulix.shilomy.trainingtask.automation.page.BasePage;

/**
 * Объектная модель страницы списка задач
 */
public class TaskListPage extends BasePage {

    /**
     * Путь списка задач
     */
    private static final String PATH = "/tasks";

    /**
     * Url списка задач
     */
    public static final String URL = ROOT_URL + PATH;


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
     * Кнопка добавления задачи
     */
    public AddButton addButton = new AddButton(driver);

    /**
     * Список строк таблицы
     */
    private final List<WebElement> tableRows = driver.findElements(By.xpath("//tbody/tr"));

    /**
     * Последняя кнопка Изменить
     */
    public EditButton editButton = new EditButton(tableRows.size(), driver);

    /**
     * Первая кнопка Удалить
     */
    public DeleteButton deleteButton = new DeleteButton(FIRST_INDEX, driver);

    private static final String EDIT_BUTTON = "//tbody/tr[%d]//button[contains(text(), 'Изменить')]";

    private static final int FIRST_INDEX = 1;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public TaskListPage(WebDriver driver) {
        super(driver);
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
