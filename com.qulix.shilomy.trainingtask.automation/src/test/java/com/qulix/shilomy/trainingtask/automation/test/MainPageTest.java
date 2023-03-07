package com.qulix.shilomy.trainingtask.automation.test;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;

public class MainPageTest {
    /**
     * Url главной страницы
     */
    private static final String MAIN_PAGE_URL = "http://localhost:8080/Trainingtask/";

    /**
     * Url списка проектов
     */
    private static final String PROJECT_LIST_URL = "http://localhost:8080/Trainingtask/projects";

    /**
     * Url списка задач
     */
    private static final String TASK_LIST_URL = "http://localhost:8080/Trainingtask/tasks";

    /**
     * Url списка персон
     */
    private static final String PERSON_LIST_URL = "http://localhost:8080/Trainingtask/employees";

    private static WebDriver driver;

    /**
     * Объект главной страницы
     */
    private MainPage mainPage;

    @BeforeAll
    public static void init() throws IOException {
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void setUp() {
        driver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
    }

    @Test
    public void toMainMenu() {
        assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
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
        assertEquals(PROJECT_LIST_URL, driver.getCurrentUrl());
    }

    @Test
    public void toTaskList() {
        mainPage.clickTasksButton();
        assertEquals(TASK_LIST_URL, driver.getCurrentUrl());
    }

    @Test
    public void toPersonList() {
        mainPage.clickPersonsButton();
        assertEquals(PERSON_LIST_URL, driver.getCurrentUrl());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
