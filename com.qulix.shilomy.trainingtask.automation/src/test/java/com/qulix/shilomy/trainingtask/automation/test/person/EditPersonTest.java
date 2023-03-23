package com.qulix.shilomy.trainingtask.automation.test.person;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.person.EditPersonPage;
import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тесты формы редактирования персон
 */
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
    @DisplayName("Отображение элементов формы редактирования персоны")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .header
                .clickPersonsButton()
                .clickEditButton()
                .elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Отмена редактирования персоны")
    public void editPersonCancel() {
        //Получение последней персоны
        Person lastPerson = mainPage.header.clickPersonsButton().getLastPerson();

        //Получение последней персоны после отмены
        Person newLastPerson =
            mainPage
                .header
                .clickPersonsButton()
                .clickEditButton()
                .cancelButton.click(new PersonListPage(driver))
                .getLastPerson();

        assertEquals(lastPerson, newLastPerson);
    }

    @Test
    @DisplayName("Недоступность для редактирования поля \"Идентификатор\"")
    public void idInputDisabled() {
        assertFalse(
            mainPage
                .header
                .clickPersonsButton()
                .clickEditButton()
                .isIdInputEnabled()
        );
    }

    @Test
    @DisplayName("Возможность редактировать поля \"Фамилия\", \"Имя\", \"Отчество\", \"Должность\"")
    public void editAllowed() {
        //Получение последней персоны
        Person person = mainPage.header.clickPersonsButton().getLastPerson();

        Person updatedPerson = mainPage
            .header
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
            .saveButton.click(new PersonListPage(driver))
            .getLastPerson();

        assertNotEquals(person, updatedPerson);
    }

    @Test
    @DisplayName("Отсутствие возможности сохранения, при удалении данных из полей \"Фамилия\", \"Имя\", \"Отчество\", \"Должность\"")
    public void emptyValidation() {
        assertTrue(
            mainPage
                .header
                .clickPersonsButton()
                .clickEditButton()
                .clearInputs()
                .saveButton.click(new EditPersonPage(driver))
                .allLengthLabelsDisplayed()
        );
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
