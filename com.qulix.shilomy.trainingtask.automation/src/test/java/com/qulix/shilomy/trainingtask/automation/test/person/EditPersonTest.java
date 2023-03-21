package com.qulix.shilomy.trainingtask.automation.test.person;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

public class EditPersonTest {

    /**
     * Входные данные
     */
    private static final String SURNAME = "Иванов123";
    private static final String NAME = "Иван321";
    private static final String PATRONYMIC = "Иванович456";
    private static final String POSITION = "Специалист654";

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
                .clickPersonsButton()
                .clickEditButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void editPersonCancel() {
        Person lastPerson = mainPage.clickPersonsButton().getLastPerson();

        Person newLastPerson =
            mainPage
                .clickPersonsButton()
                .clickEditButton()
                .clickCancelButton()
                .getLastPerson();

        assertEquals(lastPerson, newLastPerson);
    }

    @Test
    public void idInputDisabled() {
        assertFalse(
            mainPage
                .clickPersonsButton()
                .clickEditButton()
                .isIdInputEnabled()
        );
    }

    @Test
    public void editAllowed() {
        Person person = mainPage.clickPersonsButton().getLastPerson();

        mainPage
            .clickPersonsButton()
            .clickEditButton()
            .enterPerson(
                new Person(
                    SURNAME,
                    NAME,
                    PATRONYMIC,
                    POSITION
                )
            )
            .clickSaveButton();

        Person updatedPerson = mainPage.clickPersonsButton().getLastPerson();

        assertNotEquals(person, updatedPerson);
    }

    @Test
    public void emptyValidation() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickEditButton()
                .clearInputs()
                .clickSaveButton()
                .allLengthLabelsDisplayed()
        );
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
