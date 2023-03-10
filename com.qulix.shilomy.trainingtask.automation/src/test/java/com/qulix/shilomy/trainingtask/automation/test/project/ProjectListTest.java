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
import com.qulix.shilomy.trainingtask.automation.page.project.AddProjectPage;
import com.qulix.shilomy.trainingtask.automation.page.project.EditProjectPage;

public class ProjectListTest {

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
        driver.get(MainPage.URL);
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

        assertTrue(driver.getCurrentUrl().contains(AddProjectPage.URL));
    }

    @Test
    public void toEditProjectForm() {
        mainPage
            .clickProjectsButton()
            .clickEditButton();

        assertTrue(driver.getCurrentUrl().contains(EditProjectPage.URL));
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
