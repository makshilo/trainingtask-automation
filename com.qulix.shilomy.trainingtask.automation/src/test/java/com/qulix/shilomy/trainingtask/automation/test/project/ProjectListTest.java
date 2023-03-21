package com.qulix.shilomy.trainingtask.automation.test.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.project.AddProjectPage;
import com.qulix.shilomy.trainingtask.automation.page.project.EditProjectPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

public class ProjectListTest {

    public static final int FIRST_INDEX = 1;
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
        Long lastId = mainPage
            .clickProjectsButton()
            .getProjectByIndex(FIRST_INDEX)
            .getId();

        Long newLastId = mainPage
            .clickProjectsButton()
            .clickDeleteButton()
            .getProjectByIndex(FIRST_INDEX)
            .getId();

        assertNotSame(lastId, newLastId);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
