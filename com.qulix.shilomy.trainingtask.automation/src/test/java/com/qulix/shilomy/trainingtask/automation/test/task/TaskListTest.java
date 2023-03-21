package com.qulix.shilomy.trainingtask.automation.test.task;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.task.AddTaskPage;
import com.qulix.shilomy.trainingtask.automation.page.task.EditTaskPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тесты формы списка задач
 */
public class TaskListTest {

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
    @DisplayName("Отображение элементов формы \"Список задач\"")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Переход на форму добавления задачи")
    public void toAddTaskForm() {
        mainPage
            .clickTasksButton()
            .clickAddButton();

        assertTrue(driver.getCurrentUrl().contains(AddTaskPage.URL));
    }

    @Test
    @DisplayName("Переход на форму редактирования задачи")
    public void toEditTaskForm() {
        mainPage
            .clickTasksButton()
            .clickEditButton();

        assertEquals(driver.getCurrentUrl(), EditTaskPage.URL);
    }

    @Test
    @DisplayName("Удаление задачи")
    public void deleteTask() {
        Long lastId = mainPage
            .clickTasksButton()
            .getLastTask()
            .getId();

        Long newLastId = mainPage
            .clickTasksButton()
            .clickDeleteButton()
            .getLastTask()
            .getId();

        assertNotSame(lastId, newLastId);
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
