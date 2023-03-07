package com.qulix.shilomy.trainingtask.automation.test.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;

public class EditProjectTest {

    /**
     * Url главной страницы
     */
    private static final String MAIN_PAGE_URL = "http://localhost:8080/Trainingtask/";

    /**
     * Верные входные данные
     */
    private static final String NAME = "Проект123";
    private static final String SHORTNAME = "П123";
    private static final String DESCRIPTION = "Проект номер 123";

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
                .clickProjectsButton()
                .clickEditButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void editProjectCancel() {
        String lastRow = mainPage.clickProjectsButton().getLastRow();

        String newLastRow = mainPage
            .clickProjectsButton()
            .clickEditButton()
            .enterName(NAME)
            .enterShortName(SHORTNAME)
            .enterDescription(DESCRIPTION)
            .clickCancelButton()
            .getLastRow();

        assertEquals(lastRow, newLastRow);
    }

    @Test
    public void idInputDisabled() {
        assertFalse(
            mainPage
                .clickProjectsButton()
                .clickEditButton()
                .isIdInputEnabled()
        );
    }

    @Test
    public void editAllowed() {
        String project = mainPage.clickProjectsButton().getLastRow();

        mainPage
            .clickProjectsButton()
            .clickEditButton()
            .enterName(NAME)
            .enterShortName(SHORTNAME)
            .enterDescription(DESCRIPTION)
            .clickSaveButton();

        String updatedProject = mainPage.clickProjectsButton().getLastRow();

        assertNotEquals(project, updatedProject);
    }

    @Test
    public void emptyValidation() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickEditButton()
                .clearInputs()
                .clickSaveButton()
                .minLengthLabelsDisplayed()
        );
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}