package com.qulix.shilomy.trainingtask.automation.test.person;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;


public class AddPersonTest {

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
    private static final String EMPTY_STRING = "";

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
                .clickAddButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void addPersonCancel() {
        Person lastPerson = mainPage.clickPersonsButton().getLastPerson();

        Person newLastPerson = mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterPerson(
                new Person(
                    SURNAME,
                    NAME,
                    PATRONYMIC,
                    POSITION
                )
            )
            .clickCancelButton()
            .getLastPerson();

        assertEquals(lastPerson, newLastPerson);
    }

    @Test
    public void idFormation() {
        Long maxId = mainPage.clickPersonsButton().getLastPerson().getId();

        mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterPerson(new Person(SURNAME, NAME, PATRONYMIC, POSITION))
            .clickSaveButton();

        driver.get(MainPage.URL);
        Long newMaxId = mainPage.clickPersonsButton().getLastPerson().getId();

        assertEquals(newMaxId, maxId + 1);
    }

    @Test
    public void lettersSpecials() {
        Person person = new Person(SPECIAL_SURNAME, SPECIAL_NAME, SPECIAL_PATRONYMIC, SPECIAL_POSITION);

        mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterPerson(person)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Person lastPerson = mainPage.clickPersonsButton().getLastPerson();
        person.setId(lastPerson.getId());

        assertEquals(lastPerson, person);
    }

    @Test
    public void minLength() {
        Person person = new Person(MIN_SURNAME, MIN_NAME, MIN_PATRONYMIC, MIN_POSITION);

        mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterPerson(person)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Person lastPerson = mainPage.clickPersonsButton().getLastPerson();
        person.setId(lastPerson.getId());

        assertEquals(lastPerson, person);
    }

    @Test
    public void maxLength() {
        Person person = new Person(MAX_SURNAME, MAX_NAME, MAX_PATRONYMIC, MAX_POSITION);

        mainPage
            .clickPersonsButton()
            .clickAddButton()
            .enterPerson(person)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Person lastPerson = mainPage.clickPersonsButton().getLastPerson();
        person.setId(lastPerson.getId());
        assertEquals(lastPerson, person);
    }

    @Test
    public void surnameObligation() {
        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterPerson(
                    new Person(
                        EMPTY_STRING,
                        NAME,
                        PATRONYMIC,
                        POSITION
                    )
                )
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
                .enterPerson(
                    new Person(
                        SURNAME,
                        EMPTY_STRING,
                        PATRONYMIC,
                        POSITION
                    )
                )
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
                .enterPerson(
                    new Person(
                        SURNAME,
                        NAME,
                        EMPTY_STRING,
                        POSITION
                    )
                )
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
                .enterPerson(
                    new Person(
                        SURNAME,
                        NAME,
                        PATRONYMIC,
                        EMPTY_STRING
                    )
                )
                .clickSaveButton()
                .positionLengthLabelDisplayed()
        );
    }

    @Test
    public void whitespaceValidation() {
        String whitespaceString = SPACE_SIGN.repeat(3);

        assertTrue(
            mainPage
                .clickPersonsButton()
                .clickAddButton()
                .enterPerson(
                    new Person(
                        whitespaceString,
                        whitespaceString,
                        whitespaceString,
                        whitespaceString
                    )
                )
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
                .enterPerson(
                    new Person(
                        INVALID_SPECIAL_SURNAME,
                        INVALID_SPECIAL_NAME,
                        INVALID_SPECIAL_PATRONYMIC,
                        INVALID_SPECIAL_POSITION
                    )
                )
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
                .enterPerson(
                    new Person(
                        BELOW_MIN_SURNAME,
                        BELOW_MIN_NAME,
                        BELOW_MIN_PATRONYMIC,
                        BELOW_MIN_POSITION
                    )
                )
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
                .enterPerson(
                    new Person(
                        OVER_MAX_SURNAME,
                        OVER_MAX_NAME,
                        OVER_MAX_PATRONYMIC,
                        OVER_MAX_POSITION
                    )
                )
                .clickSaveButton()
                .allLengthLabelsDisplayed()
        );
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

