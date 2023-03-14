package com.qulix.shilomy.trainingtask.automation.test.task;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.task.AddTaskPage;
import com.qulix.shilomy.trainingtask.automation.page.task.EditTaskPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

public class TaskListTest {

    private static WebDriver driver;

    /**
     * Объект главной страницы
     */
    private MainPage mainPage;

    @BeforeAll
    public static void init() {
        driver = DriverManager.getInstance().getDriver();
    }

    @BeforeEach
    public void setUp() {
        driver.get(MainPage.URL);
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

        assertTrue(driver.getCurrentUrl().contains(AddTaskPage.URL));
    }

    @Test
    public void toEditTaskForm() {
        mainPage
            .clickTasksButton()
            .clickEditButton();

        assertEquals(driver.getCurrentUrl(), EditTaskPage.URL);
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
