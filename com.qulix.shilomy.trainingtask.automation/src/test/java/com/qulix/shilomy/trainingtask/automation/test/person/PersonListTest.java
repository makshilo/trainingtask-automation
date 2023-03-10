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
import com.qulix.shilomy.trainingtask.automation.page.person.AddPersonPage;
import com.qulix.shilomy.trainingtask.automation.page.person.EditPersonPage;

public class PersonListTest {

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
                .clickPersonsButton()
                .isPageElementsDisplayed()
        );
    }

    @Test
    public void toAddPersonForm() {
        mainPage
            .clickPersonsButton()
            .clickAddButton();

        assertEquals(AddPersonPage.URL, driver.getCurrentUrl());
    }

    @Test
    public void toEditPersonForm() {
        mainPage
            .clickPersonsButton()
            .clickEditButton();

        assertEquals(EditPersonPage.URL, driver.getCurrentUrl());
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
