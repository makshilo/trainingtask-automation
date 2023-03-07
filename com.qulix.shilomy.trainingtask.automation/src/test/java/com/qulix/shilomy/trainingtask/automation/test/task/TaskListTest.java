package com.qulix.shilomy.trainingtask.automation.test.task;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;

public class TaskListTest {
    /**
     * Url главной страницы
     */
    private static final String MAIN_PAGE_URL = "http://localhost:8080/Trainingtask/";

    /**
     * Url страницы добавления задачи
     */
    private static final String ADD_TASK_FORM_URL = "http://localhost:8080/Trainingtask/addTask";

    /**
     * Url страницы изменения задачи
     */
    private static final String EDIT_TASK_FORM_URL = "http://localhost:8080/Trainingtask/updateTask";

    private static WebDriver driver;

    /**
     * Объект главной страницы
     */
    private MainPage mainPage;

    @BeforeAll
    public static void init() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void setUp() {
        driver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
    }

    @Test
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void toAddTaskForm() {
        mainPage
            .clickTasksButton()
            .clickAddButton();

        assertTrue(driver.getCurrentUrl().contains(ADD_TASK_FORM_URL));
    }

    @Test
    public void toEditTaskForm() {
        mainPage
            .clickTasksButton()
            .clickEditButton();

        assertEquals(driver.getCurrentUrl(), EDIT_TASK_FORM_URL);
    }

    @Test
    public void deleteTask() {
        int lastId = mainPage
            .clickTasksButton()
            .getLastId();

        int newLastId = mainPage
            .clickTasksButton()
            .clickDeleteButton()
            .getFirstId();

        assertTrue(lastId != newLastId);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
