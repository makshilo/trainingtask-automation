package com.qulix.shilomy.trainingtask.automation.test.person;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.person.AddPersonPage;
import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;


/**
 * Тесты формы добавления персон
 */
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
    @DisplayName("Отображение элементов формы добавления персоны")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .header
                .clickPersonsButton()
                .addButton.click(AddPersonPage.class)
                .elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Отмена добавления персоны")
    public void addPersonCancel() {
        assertEquals(
            getLastPerson(),
            enterPerson(new Person(SURNAME, NAME, PATRONYMIC, POSITION))
                .cancelButton.click(PersonListPage.class)
                .getLastPerson()
        );
    }

    @Test
    @DisplayName("Соответствие идентификатора порядковому номеру персоны")
    public void idFormation() {
        assertEquals(
            getLastPerson().getId() + 1,
            enterPerson(
                new Person(SURNAME, NAME, PATRONYMIC, POSITION))
                .saveButton.click(PersonListPage.class)
                .getLastPerson()
                .getId()
        );
    }

    @Test
    @DisplayName("Сохранение персоны с допустимыми буквами и спецсимволами")
    public void lettersSpecials() {
        Person person = new Person(SPECIAL_SURNAME, SPECIAL_NAME, SPECIAL_PATRONYMIC, SPECIAL_POSITION);

        Person lastPerson = enterPerson(person)
            .saveButton.click(PersonListPage.class)
            .getLastPerson();

        //Запись идентификатора
        person.setId(lastPerson.getId());

        assertEquals(lastPerson, person);
    }

    @Test
    @DisplayName("Сохранение персоны c минимальным количеством символов")
    public void minLength() {
        Person person = new Person(MIN_SURNAME, MIN_NAME, MIN_PATRONYMIC, MIN_POSITION);

        Person lastPerson = enterPerson(person)
            .saveButton.click(PersonListPage.class)
            .getLastPerson();

        //Запись идентификатора
        person.setId(lastPerson.getId());

        assertEquals(lastPerson, person);
    }

    @Test
    @DisplayName("Сохранение персоны c максимальным количеством символов")
    public void maxLength() {
        Person person = new Person(MAX_SURNAME, MAX_NAME, MAX_PATRONYMIC, MAX_POSITION);

        Person lastPerson = enterPerson(person)
            .saveButton.click(PersonListPage.class)
            .getLastPerson();

        //Запись идентификатора
        person.setId(lastPerson.getId());

        assertEquals(lastPerson, person);
    }

    @Test
    @DisplayName("Обязательность заполнения поля \"Фамилия\"")
    public void surnameObligation() {
        assertTrue(
            enterPerson(
                new Person(EMPTY_STRING, NAME, PATRONYMIC, POSITION))
                .saveButton.click(AddPersonPage.class)
                .surnameLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность заполнения поля \"Имя\"")
    public void nameObligation() {
        assertTrue(
            enterPerson(new Person(SURNAME, EMPTY_STRING, PATRONYMIC, POSITION))
                .saveButton.click(AddPersonPage.class)
                .nameLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность заполнения поля \"Отчество\"")
    public void patronymicObligation() {
        assertTrue(
            enterPerson(new Person(SURNAME, NAME, EMPTY_STRING, POSITION))
                .saveButton.click(AddPersonPage.class)
                .patronymicLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность заполнения поля \"Должность\"")
    public void positionObligation() {
        assertTrue(
            enterPerson(new Person(SURNAME, NAME, PATRONYMIC, EMPTY_STRING))
                .saveButton.click(AddPersonPage.class)
                .positionLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей пробелами")
    public void whitespaceValidation() {
        String whitespaceString = SPACE_SIGN.repeat(3);

        assertTrue(
            enterPerson(new Person(whitespaceString, whitespaceString, whitespaceString, whitespaceString))
                .saveButton.click(AddPersonPage.class)
                .allInvalidLabelsDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения заполнении полей недопустимыми спецсимволами")
    public void specialSymbolValidation() {
        assertTrue(
            enterPerson(new Person(INVALID_SPECIAL_SURNAME, INVALID_SPECIAL_NAME, INVALID_SPECIAL_PATRONYMIC, INVALID_SPECIAL_POSITION))
                .saveButton.click(AddPersonPage.class)
                .allInvalidLabelsDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей количеством символов, меньше минимального")
    public void belowMinLength() {
        assertTrue(
            enterPerson(new Person(BELOW_MIN_SURNAME, BELOW_MIN_NAME, BELOW_MIN_PATRONYMIC, BELOW_MIN_POSITION))
                .saveButton.click(AddPersonPage.class)
                .allLengthLabelsDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей количеством символов, больше максимального")
    public void overMaxLength() {
        assertTrue(
            enterPerson(new Person(OVER_MAX_SURNAME, OVER_MAX_NAME, OVER_MAX_PATRONYMIC, OVER_MAX_POSITION))
                .saveButton.click(AddPersonPage.class)
                .allLengthLabelsDisplayed()
        );
    }

    /**
     * Получение последней персоны в списке
     *
     * @return персона
     */
    private Person getLastPerson() {
        Person lastPerson = mainPage.header.clickPersonsButton().getLastPerson();
        mainPage.get();
        return lastPerson;
    }

    /**
     * Ввод персоны
     *
     * @param person персона
     * @return страница добавления персоны
     */
    private AddPersonPage enterPerson(Person person) {
        return mainPage.header.clickPersonsButton().addButton.click(AddPersonPage.class).enterPerson(person);
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

