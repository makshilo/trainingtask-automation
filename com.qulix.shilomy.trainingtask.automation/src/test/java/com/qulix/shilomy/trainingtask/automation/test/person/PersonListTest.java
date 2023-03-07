package com.qulix.shilomy.trainingtask.automation.test.person;

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

public class PersonListTest {

    /**
     * Url главной страницы
     */
    private static final String MAIN_PAGE_URL = "http://localhost:8080/Trainingtask/";

    /**
     * Url страницы добавления персоны
     */
    private static final String ADD_PERSON_FORM_URL = "http://localhost:8080/Trainingtask/addEmployee";

    /**
     * Url страницы изменения персоны
     */
    private static final String EDIT_PERSON_FORM_URL = "http://localhost:8080/Trainingtask/updateEmployee";

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
                .clickPersonsButton()
                .isPageElementsDisplayed()
        );
    }

    @Test
    public void toAddPersonForm() {
        mainPage
            .clickPersonsButton()
            .clickAddButton();

        assertEquals(ADD_PERSON_FORM_URL, driver.getCurrentUrl());
    }

    @Test
    public void toEditPersonForm() {
        mainPage
            .clickPersonsButton()
            .clickEditButton();

        assertEquals(EDIT_PERSON_FORM_URL, driver.getCurrentUrl());
    }

    @Test
    public void deletePerson() {
        int lastId = mainPage
            .clickPersonsButton()
            .getFirstId();

        int newLastId = mainPage
            .clickPersonsButton()
            .clickDeleteButton()
            .getFirstId();

        assertTrue(lastId != newLastId);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
