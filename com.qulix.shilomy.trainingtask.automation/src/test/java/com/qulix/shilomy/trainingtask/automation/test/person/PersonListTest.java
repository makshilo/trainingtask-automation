package com.qulix.shilomy.trainingtask.automation.test.person;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.person.AddPersonPage;
import com.qulix.shilomy.trainingtask.automation.page.person.EditPersonPage;
import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тесты формы списка персон
 */
public class PersonListTest {

    private static WebDriver driver;

    /**
     * Объект главной страницы
     */
    private MainPage mainPage;

    /**
     * Перед всеми тестами
     */
    @BeforeAll
    public static void init() {
        driver = DriverManager.getInstance().getDriver();
    }

    /**
     * Перед каждым тестом
     */
    @BeforeEach
    public void setUp() {
        driver.get(MainPage.URL);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Отображение элементов формы \"Список персон\"")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .header
                .clickPersonsButton()
                .isPageElementsDisplayed()
        );
    }

    @Test
    @DisplayName("Переход на форму добавления персоны")
    public void toAddPersonForm() {
        mainPage
            .header
            .clickPersonsButton()
            .addButton.click(AddPersonPage.class);

        assertEquals(AddPersonPage.URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на форму редактирования персоны")
    public void toEditPersonForm() {
        mainPage
            .header
            .clickPersonsButton()
            .editButton.click(EditPersonPage.class);

        assertEquals(EditPersonPage.URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Удаление персоны")
    public void deletePerson() {
        Long firstId = mainPage
            .header
            .clickPersonsButton()
            .getPersonByIndex(1)
            .getId();

        Long newFirstId = mainPage
            .header
            .clickPersonsButton()
            .deleteButton.click(PersonListPage.class)
            .getPersonByIndex(1)
            .getId();

        assertNotSame(firstId, newFirstId);
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
