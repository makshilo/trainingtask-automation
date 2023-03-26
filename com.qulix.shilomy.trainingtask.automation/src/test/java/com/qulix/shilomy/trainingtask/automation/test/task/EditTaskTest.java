package com.qulix.shilomy.trainingtask.automation.test.task;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.model.Project;
import com.qulix.shilomy.trainingtask.automation.model.Task;
import com.qulix.shilomy.trainingtask.automation.model.TaskStatus;
import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;
import com.qulix.shilomy.trainingtask.automation.page.task.EditTaskPage;
import com.qulix.shilomy.trainingtask.automation.page.task.TaskListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тесты формы редактирования задачи
 */
public class EditTaskTest {

    /**
     * Верные входные данные
     */
    private static final String NAME = "Задача13579";
    private static final String WORK = "54321";
    private static final String START_DATE = "11.11.2038";
    private static final String END_DATE = "02.12.2039";
    private static final String STATUS_NOT_STARTED = "Не начата";
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;

    private static WebDriver driver;

    /**
     * Объект главной страницы
     */
    private MainPage mainPage;

    /**
     * Перед всеми тестами
     */
    @BeforeAll
    public static void init() {
        driver = DriverManager.getInstance().getDriver();
    }

    /**
     * Перед каждым тестом
     */
    @BeforeEach
    public void setUp() {
        driver.get(MainPage.URL);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Отображение элементов формы редактирования задачи")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .header
                .clickTasksButton()
                .editButton.click(EditTaskPage.class)
                .elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Отмена редактирования задачи")
    public void editTaskCancel() {
        Task task = mainPage.header.clickTasksButton().getLastTask();

        Task newLastTask = mainPage
            .header
            .clickTasksButton()
            .editButton.click(EditTaskPage.class)
            .enterTask(
                new Task(
                    getProject(FIRST_INDEX),
                    NAME,
                    WORK,
                    START_DATE,
                    END_DATE,
                    List.of(getPerson(FIRST_INDEX)),
                    TaskStatus.of(STATUS_NOT_STARTED)
                )
            )
            .cancelButton.click(TaskListPage.class)
            .getLastTask();

        assertEquals(task, newLastTask);
    }

    @Test
    @DisplayName("Недоступность для редактирования поля \"Идентификатор\"")
    public void idInputDisabled() {
        assertFalse(
            mainPage
                .header
                .clickTasksButton()
                .editButton.click(EditTaskPage.class)
                .isIdInputEnabled()
        );
    }

    @Test
    @DisplayName("Возможность редактировать поля \"Название\", \"Работа\", \"Дата начала\", \"Дата окончания\", \"Статус\", \"Исполнитель\"")
    public void editAllowed() {
        Task task = mainPage.header.clickTasksButton().getLastTask();

        Task updatedTask = mainPage
            .header
            .clickTasksButton()
            .editButton.click(EditTaskPage.class)
            .enterTask(
                new Task(
                    getProject(SECOND_INDEX),
                    NAME,
                    WORK,
                    START_DATE,
                    END_DATE,
                    List.of(getPerson(SECOND_INDEX)),
                    TaskStatus.of(STATUS_NOT_STARTED)
                )
            )
            .saveButton.click(TaskListPage.class)
            .getLastTask();

        assertNotEquals(task, updatedTask);
    }

    @Test
    @DisplayName("Отсутствие возможности сохранения при удалении данных из полей \"Название\", \"Работа\", \"Дата начала\", \"Дата окончания\", \"Статус\", \"Исполнитель\"")
    public void emptyValidation() {
        assertTrue(
            mainPage
                .header
                .clickTasksButton()
                .editButton.click(EditTaskPage.class)
                .clearInputs()
                .saveButton.click(EditTaskPage.class)
                .allValidationMessagesDisplayed()
        );
    }

    /**
     * Получение персоны по индексу
     *
     * @param index индекс
     * @return персона
     */
    private Person getPerson(int index) {
        driver.get(PersonListPage.URL);
        PersonListPage persons = new PersonListPage(driver);
        Person person = persons.getPersonByIndex(index);
        driver.navigate().back();
        return person;
    }

    /**
     * Получение проекта по индексу
     *
     * @param index индекс
     * @return проект
     */
    private Project getProject(int index) {
        driver.get(ProjectListPage.URL);
        ProjectListPage projects = new ProjectListPage(driver);
        Project project = projects.getProjectByIndex(index);
        driver.navigate().back();
        return project;
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
