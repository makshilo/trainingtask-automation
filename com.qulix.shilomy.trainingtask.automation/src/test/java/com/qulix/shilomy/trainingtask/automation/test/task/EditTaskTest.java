package com.qulix.shilomy.trainingtask.automation.test.task;

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

public class EditTaskTest {

    /**
     * Url главной страницы
     */
    private static final String MAIN_PAGE_URL = "http://localhost:8080/Trainingtask/";

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
                .clickEditButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void editTaskCancel() {
        String lastRow = mainPage.clickTasksButton().getLastRow();

        String newLastRow = mainPage
            .clickTasksButton()
            .clickEditButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_NOT_STARTED)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickCancelButton()
            .getLastRow();

        assertEquals(lastRow, newLastRow);
    }

    @Test
    public void idInputDisabled() {
        assertFalse(
            mainPage
                .clickTasksButton()
                .clickEditButton()
                .isIdInputEnabled()
        );
    }

    @Test
    public void editAllowed() {
        String task = mainPage.clickTasksButton().getLastRow();

        mainPage
            .clickTasksButton()
            .clickEditButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_NOT_STARTED)
            .selectExecutorByIndex(SECOND_INDEX)
            .selectProjectByIndex(SECOND_INDEX)
            .clickSaveButton();

        String updatedTask = mainPage.clickTasksButton().getLastRow();

        assertNotEquals(task, updatedTask);
    }

    @Test
    public void emptyValidation() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickEditButton()
                .clearInputs()
                .clickSaveButton()
                .allValidationMessagesDisplayed()
        );
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
