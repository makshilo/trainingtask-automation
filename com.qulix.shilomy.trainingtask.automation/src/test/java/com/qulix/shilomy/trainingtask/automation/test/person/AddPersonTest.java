package com.qulix.shilomy.trainingtask.automation.test.person;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;


public class AddPersonTest {
    /**
     * Url главной страницы
     */
    private static final String MAIN_PAGE_URL = "http://localhost:8080/Trainingtask/";

    /**
     * Верные входные данные
     */
    private static final String SURNAME = "Иванов";
    private static final String NAME = "Иван";
    private static final String PATRONYMIC = "Иванович";
    private static final String POSITION = "Специалист";

    /**
     * Входные данные со спец. символами
     */
    private static final String SPECIAL_SURNAME = "Иванов-Петров";
    private static final String SPECIAL_NAME = "Иван-2";
    private static final String SPECIAL_PATRONYMIC = "Иванович-1";
    private static final String SPECIAL_POSITION = "Специалист-12.3/5";

    /**
     * Минимальные по длине верные входные данные
     */
    private static final String MIN_SURNAME = "Ив";
    private static final String MIN_NAME = "Ив";
    private static final String MIN_PATRONYMIC = "Ив";
    private static final String MIN_POSITION = "Сп";

    /**
     * Максимальные по длине верные входные данные
     */
    private static final String MAX_SURNAME = "вовноИаоИввнИИнонвноавааИИоИвн";
    private static final String MAX_NAME = "вовноИаоИввнИИнонвноавааИИоИвн";
    private static final String MAX_PATRONYMIC = "вовноИаоИввнИИнонвноавааИИоИвн";
    private static final String MAX_POSITION = "вовноИаоИввнИИнонвноавааИИоИвн";

    /**
     * Входные данные с недопустимыми спец. символами
     */
    private static final String INVALID_SPECIAL_NAME = "Ива@#$%^";
    private static final String INVALID_SPECIAL_SURNAME = "!ИВан@#$%^";
    private static final String INVALID_SPECIAL_PATRONYMIC = "Иванович!@#$%^";
    private static final String INVALID_SPECIAL_POSITION = "Спец!@#$%^";

    /**
     * Входные данные с длиной меньше допустимой
     */
    private static final String BELOW_MIN_SURNAME = "И";
    private static final String BELOW_MIN_NAME = "П";
    private static final String BELOW_MIN_PATRONYMIC = "О";
    private static final String BELOW_MIN_POSITION = "Д";

    /**
     * Входные данные с длиной больше допустимой
     */
    private static final String OVER_MAX_SURNAME = "вовноИаоИввнИИнонвноавааИИоИвнап";
    private static final String OVER_MAX_NAME = "вовноИаоИввнИИнонвноавааИИоИвнап";
    private static final String OVER_MAX_PATRONYMIC = "вовноИаоИввнИИнонвноавааИИоИвнапап";
    private static final String OVER_MAX_POSITION = "вовноИаоИввнИИнонвноавааИИоИвнапап";

    private static final String SPACE_SIGN = " ";

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
                .clickPersonsButton()
                .clickAddButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void addPersonCancel() {
        String lastRow = mainPage.clickPersonsButton().getLastRow();

        String newLastRow = mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterSurname(SURNAME)
            .enterName(NAME)
            .enterPatronymic(PATRONYMIC)
            .enterPosition(POSITION)
            .clickCancelButton()
            .getLastRow();

        assertEquals(lastRow, newLastRow);
    }

    @Test
    public void idFormation() {
        int maxId = mainPage.clickPersonsButton().getLastId();

        mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterSurname(SURNAME)
            .enterName(NAME)
            .enterPatronymic(PATRONYMIC)
            .enterPosition(POSITION)
            .clickSaveButton();

        driver.get(MAIN_PAGE_URL);
        int newMaxId = mainPage.clickPersonsButton().getLastId();

        assertEquals(newMaxId, maxId + 1);
    }

    @Test
    public void lettersSpecials() {
        mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterSurname(SPECIAL_SURNAME)
            .enterName(SPECIAL_NAME)
            .enterPatronymic(SPECIAL_PATRONYMIC)
            .enterPosition(SPECIAL_POSITION)
            .clickSaveButton();

        driver.get(MAIN_PAGE_URL);

        String lastRow = mainPage.clickPersonsButton().getLastRow();
        String person = String.join(SPACE_SIGN, SPECIAL_SURNAME, SPECIAL_NAME, SPECIAL_PATRONYMIC, SPECIAL_POSITION);

        assertTrue(lastRow.contains(person));
    }

    @Test
    public void minLength() {
        mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterSurname(MIN_SURNAME)
            .enterName(MIN_NAME)
            .enterPatronymic(MIN_PATRONYMIC)
            .enterPosition(MIN_POSITION)
            .clickSaveButton();

        driver.get(MAIN_PAGE_URL);

        String lastRow = mainPage.clickPersonsButton().getLastRow();
        String person = String.join(SPACE_SIGN, MIN_SURNAME, MIN_NAME, MIN_PATRONYMIC, MIN_POSITION);

        assertTrue(lastRow.contains(person));
    }

    @Test
    public void maxLength() {
        mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterSurname(MAX_SURNAME)
            .enterName(MAX_NAME)
            .enterPatronymic(MAX_PATRONYMIC)
            .enterPosition(MAX_POSITION)
            .clickSaveButton();

        driver.get(MAIN_PAGE_URL);

        String lastRow = mainPage.clickPersonsButton().getLastRow();
        String person = String.join(SPACE_SIGN, MAX_SURNAME, MAX_NAME, MAX_PATRONYMIC, MAX_POSITION);

        assertTrue(lastRow.contains(person));
    }

    @Test
    public void surnameObligation() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterName(NAME)
                .enterPatronymic(PATRONYMIC)
                .enterPosition(POSITION)
                .clickSaveButton()
                .surnameLengthLabelDisplayed()
        );
    }

    @Test
    public void nameObligation() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterSurname(SURNAME)
                .enterPatronymic(PATRONYMIC)
                .enterPosition(POSITION)
                .clickSaveButton()
                .nameLengthLabelDisplayed()
        );
    }

    @Test
    public void patronymicObligation() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterSurname(SURNAME)
                .enterName(NAME)
                .enterPosition(POSITION)
                .clickSaveButton()
                .patronymicLengthLabelDisplayed()
        );
    }

    @Test
    public void positionObligation() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterSurname(SURNAME)
                .enterName(NAME)
                .enterPatronymic(PATRONYMIC)
                .clickSaveButton()
                .positionLengthLabelDisplayed()
        );
    }

    @Test
    public void whitespaceValidation() {
        String whitespaceString = SPACE_SIGN + SPACE_SIGN + SPACE_SIGN;

        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterSurname(whitespaceString)
                .enterName(whitespaceString)
                .enterPatronymic(whitespaceString)
                .enterPosition(whitespaceString)
                .clickSaveButton()
                .allInvalidLabelsDisplayed()
        );
    }

    @Test
    public void specialSymbolValidation() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterSurname(INVALID_SPECIAL_SURNAME)
                .enterName(INVALID_SPECIAL_NAME)
                .enterPatronymic(INVALID_SPECIAL_PATRONYMIC)
                .enterPosition(INVALID_SPECIAL_POSITION)
                .clickSaveButton()
                .allInvalidLabelsDisplayed()
        );
    }

    @Test
    public void belowMinLength() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterSurname(BELOW_MIN_SURNAME)
                .enterName(BELOW_MIN_NAME)
                .enterPatronymic(BELOW_MIN_PATRONYMIC)
                .enterPosition(BELOW_MIN_POSITION)
                .clickSaveButton()
                .allLengthLabelsDisplayed()
        );
    }

    @Test
    public void overMaxLength() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterSurname(OVER_MAX_SURNAME)
                .enterName(OVER_MAX_NAME)
                .enterPatronymic(OVER_MAX_PATRONYMIC)
                .enterPosition(OVER_MAX_POSITION)
                .clickSaveButton()
                .allLengthLabelsDisplayed()
        );
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

