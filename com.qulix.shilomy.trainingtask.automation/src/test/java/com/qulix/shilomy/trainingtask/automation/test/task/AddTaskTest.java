package com.qulix.shilomy.trainingtask.automation.test.task;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;

public class AddTaskTest {

    /**
     * Верные входные данные
     */
    private static final String NAME = "Задача1";
    private static final String WORK = "5";
    private static final String START_DATE = "11.11.2018";
    private static final String END_DATE = "02.12.2019";
    private static final String MID_START_DATE = "10.01.2000";
    private static final String MID_END_DATE = "02.11.2006";
    private static final String STATUS_NOT_STARTED = "Не начата";
    private static final String STATUS_IN_PROCESS = "В процессе";
    private static final String STATUS_COMPLETED = "Завершена";
    private static final String STATUS_POSTPONED = "Отложена";
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;

    /**
     * Входные данные c цифрами и допустимыми спец. символами
     */
    private static final String DIGIT_SPECIAL_NAME = "Задача_1-1";

    /**
     * Входные данные только из цифр
     */
    private static final String DIGIT_NAME = "12345";


    /**
     * Минимальные по длине верные входные данные
     */
    private static final String MIN_NAME = "За";
    private static final String MIN_DATE = "01.01.1990";


    /**
     * Максимальные по длине верные входные данные
     */
    private static final String MAX_NAME = "а ааа а1 ачЗч аааа дд1аачадаа1а адда1За дЗЗ11аачд 11ЗдчЗа1аччч ч ддЗдаа1ач  ачач" +
        "ЗчааччЗдаЗ1а дЗда1а Здчааадчааааа1ааадчаааааачдЗ  ачаЗаачачд1аЗ1чдч1аа11а1Зч  1З 1а ЗачдЗЗ1адЗачдчад д а 11а1ЗЗ" +
        "ааач1ччЗЗадда ч11Заача1  Зч а ч1ч аадддчаааа дч чач1аача 1а1аад1";
    private static final String MAX_WORK = "123456789";
    private static final String MAX_DATE = "31.12.2099";

    /**
     * Входные данные с недопустимыми спец. символами
     */
    private static final String INVALID_SPECIAL_NAME = "Задача1\"№;%";
    private static final String INVALID_SPECIAL_WORK = "5ап!\"";

    /**
     * Входные данные с длиной меньше допустимой
     */
    private static final String BELOW_MIN_NAME = "Т";
    private static final String BELOW_MIN_START_DATE = "01.01.1970";
    private static final String BELOW_MIN_END_DATE = "30.04.1985";

    /**
     * Входные данные с длиной больше допустимой
     */
    private static final String OVER_MAX_NAME = "ЗдааЗччаЗаааЗа1чдадаааадаЗаЗаЗЗЗдчааадаа1З1чда ЗччЗ а1аЗч1адддЗЗЗчаЗа" +
        "1аачач ч дча11адаЗачччд1чачдч а1Зчаа З1 д1а Зч 1З1 а ааЗЗ1ЗаадЗачдч ааддачад 1аа1ач1ааадаЗ аааа1ча1 аЗа11аа чЗЗачд" +
        "ЗЗЗ адЗа 1д а11чЗЗ чадчаччч1ачаааача ач ЗаЗаадЗчЗ чааадад ааа 1 1адчда аЗча";
    private static final String OVER_MAX_WORK = "5123456789789";
    private static final String OVER_MAX_START_DATE = "01.01.2100";
    private static final String OVER_MAX_END_DATE = "30.04.2199";

    /**
     * Дата окончания раньше даты начала
     */
    private static final String COLLISION_START_DATE = "11.11.2020";
    private static final String COLLISION_END_DATE = "02.12.2019";

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
        driver.get(MainPage.URL);
        mainPage = new MainPage(driver);
    }

    @Test
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void addTaskCancel() {
        String lastRow = mainPage.clickTasksButton().getLastRow();

        String newLastRow = mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickCancelButton()
            .getLastRow();

        assertEquals(lastRow, newLastRow);
    }

    @Test
    public void addTask() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(NAME)
                && lastRow.contains(START_DATE)
                && lastRow.contains(END_DATE)
                && lastRow.contains(STATUS_IN_PROCESS)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void idFormation() {
        int maxId = mainPage.clickTasksButton().getLastId();

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);
        int newMaxId = mainPage.clickTasksButton().getLastId();

        assertEquals(newMaxId, maxId + 1);
    }

    @Test
    public void digitsName() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(DIGIT_NAME)
            .enterWork(WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(DIGIT_NAME)
                && lastRow.contains(START_DATE)
                && lastRow.contains(END_DATE)
                && lastRow.contains(STATUS_IN_PROCESS)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void lettersDigitsSpecialsName() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(DIGIT_SPECIAL_NAME)
            .enterWork(WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(DIGIT_SPECIAL_NAME)
                && lastRow.contains(START_DATE)
                && lastRow.contains(END_DATE)
                && lastRow.contains(STATUS_IN_PROCESS)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void minLength() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(MIN_NAME)
            .enterWork(WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(MIN_NAME)
                && lastRow.contains(START_DATE)
                && lastRow.contains(END_DATE)
                && lastRow.contains(STATUS_IN_PROCESS)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void maxLength() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(MAX_NAME)
            .enterWork(MAX_WORK)
            .enterStartDate(START_DATE)
            .enterEndDate(END_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(MAX_NAME)
                && lastRow.contains(START_DATE)
                && lastRow.contains(END_DATE)
                && lastRow.contains(STATUS_IN_PROCESS)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void minDate() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(MIN_NAME)
            .enterWork(WORK)
            .enterStartDate(MIN_DATE)
            .enterEndDate(MIN_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(MIN_NAME)
                && lastRow.contains(MIN_DATE)
                && lastRow.contains(STATUS_IN_PROCESS)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void maxDate() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(MIN_NAME)
            .enterWork(WORK)
            .enterStartDate(MAX_DATE)
            .enterEndDate(MAX_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(MIN_NAME)
                && lastRow.contains(MAX_DATE)
                && lastRow.contains(STATUS_IN_PROCESS)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void statusNotStarted() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(MID_START_DATE)
            .enterEndDate(MID_END_DATE)
            .selectStatusByText(STATUS_NOT_STARTED)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(NAME)
                && lastRow.contains(MID_START_DATE)
                && lastRow.contains(MID_END_DATE)
                && lastRow.contains(STATUS_NOT_STARTED)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void statusInProcess() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(MID_START_DATE)
            .enterEndDate(MID_END_DATE)
            .selectStatusByText(STATUS_IN_PROCESS)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(NAME)
                && lastRow.contains(MID_START_DATE)
                && lastRow.contains(MID_END_DATE)
                && lastRow.contains(STATUS_IN_PROCESS)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void statusCompleted() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(MID_START_DATE)
            .enterEndDate(MID_END_DATE)
            .selectStatusByText(STATUS_COMPLETED)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(NAME)
                && lastRow.contains(MID_START_DATE)
                && lastRow.contains(MID_END_DATE)
                && lastRow.contains(STATUS_COMPLETED)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void statusPostponed() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(MID_START_DATE)
            .enterEndDate(MID_END_DATE)
            .selectStatusByText(STATUS_POSTPONED)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(FIRST_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(NAME)
                && lastRow.contains(MID_START_DATE)
                && lastRow.contains(MID_END_DATE)
                && lastRow.contains(STATUS_POSTPONED)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void multipleExecutors() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(MID_START_DATE)
            .enterEndDate(MID_END_DATE)
            .selectStatusByText(STATUS_POSTPONED)
            .selectExecutorByIndex(FIRST_INDEX)
            .selectExecutorByIndex(SECOND_INDEX)
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getRowNameByIndex(SECOND_INDEX);
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(NAME)
                && lastRow.contains(MID_START_DATE)
                && lastRow.contains(MID_END_DATE)
                && lastRow.contains(STATUS_POSTPONED)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void allExecutors() {
        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterName(NAME)
            .enterWork(WORK)
            .enterStartDate(MID_START_DATE)
            .enterEndDate(MID_END_DATE)
            .selectStatusByText(STATUS_POSTPONED)
            .selectAllExecutors()
            .selectProjectByIndex(FIRST_INDEX)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickTasksButton().getLastRow();
        String executor = mainPage.clickPersonsButton().getAllNames();
        String project = mainPage.clickProjectsButton().getShortNameByIndex(FIRST_INDEX);

        assertTrue(
            lastRow.contains(NAME)
                && lastRow.contains(MID_START_DATE)
                && lastRow.contains(MID_END_DATE)
                && lastRow.contains(STATUS_POSTPONED)
                && lastRow.contains(executor)
                && lastRow.contains(project)
        );
    }

    @Test
    public void specialSymbolValidation() {
        assertTrue(
            mainPage.
                clickTasksButton()
                .clickAddButton()
                .enterName(INVALID_SPECIAL_NAME)
                .enterWork(INVALID_SPECIAL_WORK)
                .enterStartDate(MID_START_DATE)
                .enterEndDate(MID_END_DATE)
                .selectStatusByText(STATUS_POSTPONED)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .nameWorkInvalidLabelDisplayed()
        );
    }

    @Test
    public void whitespaceValidation() {
        assertTrue(
            mainPage.
                clickTasksButton()
                .clickAddButton()
                .enterName(SPACE_SIGN.repeat(4))
                .enterWork(SPACE_SIGN.repeat(2))
                .enterStartDate(MID_START_DATE)
                .enterEndDate(MID_END_DATE)
                .selectStatusByText(STATUS_POSTPONED)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .nameWorkInvalidLabelDisplayed()
        );
    }

    @Test
    public void belowMinLength() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(BELOW_MIN_NAME)
                .enterWork(WORK)
                .enterStartDate(MID_START_DATE)
                .enterEndDate(MID_END_DATE)
                .selectStatusByText(STATUS_POSTPONED)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .nameLengthLabelDisplayed()
        );
    }

    @Test
    public void overMaxLength() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(OVER_MAX_NAME)
                .enterWork(OVER_MAX_WORK)
                .enterStartDate(MID_START_DATE)
                .enterEndDate(MID_END_DATE)
                .selectStatusByText(STATUS_POSTPONED)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .nameWorkLengthLabelDisplayed()
        );
    }

    @Test
    public void dateCollision() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterWork(WORK)
                .enterStartDate(COLLISION_START_DATE)
                .enterEndDate(COLLISION_END_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .dateCollisionLabelDisplayed()
        );
    }

    @Test
    public void belowMinDate() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterWork(WORK)
                .enterStartDate(BELOW_MIN_START_DATE)
                .enterEndDate(BELOW_MIN_END_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .allDateInvalidLabelsDisplayed()
        );
    }

    @Test
    public void overMaxDate() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterWork(WORK)
                .enterStartDate(OVER_MAX_START_DATE)
                .enterEndDate(OVER_MAX_END_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .allDateInvalidLabelsDisplayed()
        );
    }

    @Test
    public void severalStatuses() {
        assertEquals(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .selectStatusByText(STATUS_POSTPONED)
                .selectStatusByText(STATUS_COMPLETED)
                .getSelectedStatus(),
            STATUS_COMPLETED
        );
    }

    @Test
    public void severalProjects() {
        String choice = mainPage
            .clickTasksButton()
            .clickAddButton()
            .selectProjectByIndex(FIRST_INDEX)
            .selectProjectByIndex(SECOND_INDEX)
            .getSelectedProject();

        driver.get(MainPage.URL);

        String project = mainPage
            .clickTasksButton()
            .clickAddButton()
            .selectProjectByIndex(SECOND_INDEX)
            .getSelectedProject();

        assertEquals(choice, project);
    }

    @Test
    public void nameObligation() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterWork(WORK)
                .enterStartDate(START_DATE)
                .enterEndDate(END_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .nameLengthLabelDisplayed()
        );
    }

    @Test
    public void workObligation() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterStartDate(START_DATE)
                .enterEndDate(END_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .workLengthLabelDisplayed()
        );
    }

    @Test
    public void startDateObligation() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterWork(WORK)
                .enterEndDate(END_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .startDateInvalidLabelDisplayed()
        );
    }

    @Test
    public void endDateObligation() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterWork(WORK)
                .enterStartDate(START_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .endDateInvalidLabelDisplayed()
        );
    }

    @Test
    public void statusObligation() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterWork(WORK)
                .enterStartDate(START_DATE)
                .enterEndDate(END_DATE)
                .selectExecutorByIndex(FIRST_INDEX)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .statusInvalidLabelDisplayed()
        );
    }

    @Test
    public void executorObligation() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterWork(WORK)
                .enterStartDate(START_DATE)
                .enterEndDate(END_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectProjectByIndex(FIRST_INDEX)
                .clickSaveButton()
                .executorInvalidLabelDisplayed()
        );
    }

    @Test
    public void projectObligation() {
        assertTrue(
            mainPage
                .clickTasksButton()
                .clickAddButton()
                .enterName(NAME)
                .enterWork(WORK)
                .enterStartDate(START_DATE)
                .enterEndDate(END_DATE)
                .selectStatusByText(STATUS_IN_PROCESS)
                .selectExecutorByIndex(FIRST_INDEX)
                .clickSaveButton()
                .projectInvalidLabelDisplayed()
        );
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
