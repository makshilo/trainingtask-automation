package com.qulix.shilomy.trainingtask.automation.test;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;


public class PersonTest {
    /**
     * Путь к файлу тестовых данных
     */
    private static final String TEST_DATA_PROPERTIES_PATH = "src/test/resources/testData.properties";

    /**
     * Url главной страницы
     */
    public static final String MAIN_PAGE_URL = "https://cs-training-task.qulix.com/trainingtask1/";

    public static final String SURNAME = "surname";
    public static final String NAME = "name";
    public static final String PATRONYMIC = "patronymic";
    public static final String POSITION = "position";
    public static final String INCORRECT_POSITION = "incorrectPosition";

    private static WebDriver driver;

    /**
     * Объект главной страницы
     */
    private MainPage mainPage;

    /**
     * Объект тестовых данных
     */
    private static final Properties testData = new Properties();

    @BeforeAll
    public static void init() throws IOException {
        driver = new ChromeDriver();
        testData.load(new FileInputStream(TEST_DATA_PROPERTIES_PATH));
    }

    @BeforeEach
    public void setUp() {
        driver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
    }

    /**
     * Отображения элементов формы
     * <p>
     * Предусловие:<br>
     * - Выполнен переход к форме добавления персон через список персон
     * <p>
     * Ожидаемый результат:<br>
     *      Отображение элементов формы:
     * <ul>
     *      <li>заголовок с текстом “Форма добавления персон”</li>
     *      <li>поле с лейблом “Фамилия”, плейсхолдером “Введите фамилию”</li>
     *      <li>поле с лейблом “Имя”, плейсхолдером “Введите имя”</li>
     *      <li>поле с лейблом “Отчество”, плейсхолдером “Введите отчество”</li>
     *      <li>поле с лейблом “Должность”, плейсхолдером “Введите должность”</li>
     *      <li>кнопка с текстом “Сохранить"</li>
     *      <li>кнопка с текстом "Отмена"</li>
     * </ul>
     */
    @Test
    public void testElementsDisplayed() {
        assertTrue(mainPage.clickPersonsLink()
                .clickAddButton()
                .isFormElementsDisplayed());
    }

    /**
     * Сохранение персоны при заполнении всех обязательных полей допустимыми значениями
     * <p>
     * Предусловие:<br>
     * - Выполнен переход к форме добавления персон через список персон
     * <p>
     * Шаги:
     * <ol>
     *     <li>Ввести допустимые значения в поле "Фамилия"</li>
     *     <li>Ввести допустимые значения в поле "Имя"</li>
     *     <li>Ввести допустимые значения в поле "Отчество"</li>
     *     <li>Ввести допустимые значения в поле "Должность</li>
     *     <li>Нажать кнопку "Сохранить"</li>
     * </ol>
     *
     * Входные данные
     * <ol>
     *     <li>Surname</li>
     *     <li>Name</li>
     *     <li>Patronymic</li>
     *     <li>Position</li>
     * </ol>
     *
     * Ожидаемый результат:<br>
     * - Переход на форму со списком персон, персона сохранена
     */
    @Test
    public void testAddPerson() {
        int maxId = mainPage.clickPersonsLink().getLastId();

        mainPage.clickPersonsLink()
                .clickAddButton()
                .enterSurname(testData.getProperty(SURNAME))
                .enterName(testData.getProperty(NAME))
                .enterPatronymic(testData.getProperty(PATRONYMIC))
                .enterPosition(testData.getProperty(POSITION))
                .clickSaveButton();

        driver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
        int newMaxId = mainPage.clickPersonsLink().getLastId();

        assertTrue(maxId != newMaxId);
    }

    /**
     * Появление валидационного сообщения при сохранении персоны
     * при заполнении поля "Должность" недопустимыми спецсимволами
     * <p>
     * Предусловие:<br>
     * - Выполнен переход к форме добавления персон через список персон
     * <p>
     * Шаги:
     * <ol>
     *     <li>Ввести допустимые значения в поле "Фамилия"</li>
     *     <li>Ввести допустимые значения в поле "Имя"</li>
     *     <li>Ввести допустимые значения в поле "Отчество"</li>
     *     <li>Ввести недопустимые значения в поле "Должность</li>
     *     <li>Нажать кнопку "Сохранить"</li>
     * </ol>
     *
     * Входные данные
     * <ol>
     *     <li>Surname</li>
     *     <li>Name</li>
     *     <li>Patronymic</li>
     *     <li>~!@#$№%^&*()+=?[]{}|/</li>
     * </ol>
     *
     * Ожидаемый результат:<br>
     * - Появление валидационного сообщения с текстом "Должность содержит недопустимый символ"
     */
    @Test
    public void testPositionValidation() {
        assertTrue(mainPage.clickPersonsLink()
                .clickAddButton()
                .enterSurname(testData.getProperty(SURNAME))
                .enterName(testData.getProperty(NAME))
                .enterPatronymic(testData.getProperty(PATRONYMIC))
                .enterPosition(testData.getProperty(INCORRECT_POSITION))
                .clickSaveButton()
                .isPositionErrorLabelDisplayed());
    }

    /**
     * Отмена сохранения персоны при заполненных полях при нажатии кнопки "Отмена"
     * <p>
     * Предусловие:<br>
     * - Выполнен переход к форме добавления персон через список персон
     * <p>
     * Шаги:
     * <ol>
     *     <li>Ввести допустимые значения в поле "Фамилия"</li>
     *     <li>Ввести допустимые значения в поле "Имя"</li>
     *     <li>Ввести допустимые значения в поле "Отчество"</li>
     *     <li>Ввести допустимые значения в поле "Должность</li>
     *     <li>Нажать кнопку "Отмена"</li>
     * </ol>
     *
     * Входные данные
     * <ol>
     *     <li>Surname</li>
     *     <li>Name</li>
     *     <li>Patronymic</li>
     *     <li>Position</li>
     * </ol>
     *
     * Ожидаемый результат:<br>
     * - Переход на форму со списком персон, персона не сохранена
     */
    @Test
    public void testCancel() {
        int maxId = mainPage.clickPersonsLink().getLastId();

        int newMaxId = mainPage.clickPersonsLink()
                .clickAddButton()
                .enterSurname(testData.getProperty(SURNAME))
                .enterName(testData.getProperty(NAME))
                .enterPatronymic(testData.getProperty(PATRONYMIC))
                .enterPosition(testData.getProperty(POSITION))
                .clickCancelButton()
                .getLastId();

        assertEquals(maxId, newMaxId);
    }

    /**
     * Автоматическое формирование идентификатора
     * <p>
     * Предусловие:<br>
     * - Зафиксирован идентификатор последней персоны в списке персон<br>
     * - Выполнен переход к форме добавления персон через список персон
     * <p>
     * Шаги:
     * <ol>
     *     <li>Ввести допустимые значения в поле "Фамилия"</li>
     *     <li>Ввести допустимые значения в поле "Имя"</li>
     *     <li>Ввести допустимые значения в поле "Отчество"</li>
     *     <li>Ввести допустимые значения в поле "Должность</li>
     *     <li>Нажать кнопку "Отмена"</li>
     * </ol>
     *
     * Входные данные
     * <ol>
     *     <li>Surname</li>
     *     <li>Name</li>
     *     <li>Patronymic</li>
     *     <li>Position</li>
     * </ol>
     *
     * Ожидаемый результат:<br>
     * - Значение в колонке "Идентификатор" на 1 больше значения, зафиксированного в предусловии
     */
    @Test
    public void testIdFormation() {
        int maxId = mainPage.clickPersonsLink().getLastId();

        mainPage.clickPersonsLink()
                .clickAddButton()
                .enterSurname(testData.getProperty(SURNAME))
                .enterName(testData.getProperty(NAME))
                .enterPatronymic(testData.getProperty(PATRONYMIC))
                .enterPosition(testData.getProperty(POSITION))
                .clickSaveButton();

        driver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
        int newMaxId = mainPage.clickPersonsLink().getLastId();

        assertEquals(newMaxId, maxId+1);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

