package com.qulix.shilomy.trainingtask.automation.test.project;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;

public class ProjectListTest {
    /**
     * Url главной страницы
     */
    private static final String MAIN_PAGE_URL = "http://localhost:8080/Trainingtask/";

    /**
     * Url страницы добавления проекта
     */
    private static final String ADD_PROJECT_FORM_URL = "http://localhost:8080/Trainingtask/addProject";

    /**
     * Url страницы изменения проекта
     */
    private static final String EDIT_PROJECT_FORM_URL = "http://localhost:8080/Trainingtask/updateProject";

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
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void toAddProjectForm() {
        mainPage
            .clickProjectsButton()
            .clickAddButton();

        assertTrue(driver.getCurrentUrl().contains(ADD_PROJECT_FORM_URL));
    }

    @Test
    public void toEditProjectForm() {
        mainPage
            .clickProjectsButton()
            .clickEditButton();

        assertTrue(driver.getCurrentUrl().contains(EDIT_PROJECT_FORM_URL));
    }

    @Test
    public void deleteProject() {
        int lastId = mainPage
            .clickProjectsButton()
            .getFirstId();

        int newLastId = mainPage
            .clickProjectsButton()
            .clickDeleteButton()
            .getFirstId();

        assertTrue(lastId != newLastId);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
