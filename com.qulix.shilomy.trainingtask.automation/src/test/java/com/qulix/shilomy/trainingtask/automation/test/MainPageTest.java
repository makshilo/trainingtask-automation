package com.qulix.shilomy.trainingtask.automation.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;
import com.qulix.shilomy.trainingtask.automation.page.task.TaskListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

public class MainPageTest {

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
    public void toMainMenu() {
        assertEquals(MainPage.URL, driver.getCurrentUrl());
    }

    @Test
    public void elementsDisplayed() {
        assertTrue(
            mainPage.elementsDisplayed()
        );
    }

    @Test
    public void toProjectList() {
        mainPage.clickProjectsButton();
        assertEquals(ProjectListPage.URL, driver.getCurrentUrl());
    }

    @Test
    public void toTaskList() {
        mainPage.clickTasksButton();
        assertEquals(TaskListPage.URL, driver.getCurrentUrl());
    }

    @Test
    public void toPersonList() {
        mainPage.clickPersonsButton();
        assertEquals(PersonListPage.URL, driver.getCurrentUrl());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
