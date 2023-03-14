package com.qulix.shilomy.trainingtask.automation.test.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

public class AddProjectTest {

    /**
     * Верные входные данные
     */
    private static final String NAME = "Проект1";
    private static final String UNIQUE_NAME = "УникальныйПроект";
    private static final String SHORTNAME = "П1";
    private static final String DESCRIPTION = "Проект номер 1";

    /**
     * Входные данные c цифрами и допустимыми спец. символами
     */
    private static final String DIGIT_SPECIAL_NAME = "Проект_1-1 1";
    private static final String DIGIT_SPECIAL_SHORTNAME = "Пр_1-1 1";
    private static final String DIGIT_SPECIAL_DESCRIPTION = "Проект номер_1-1!?";

    /**
     * Входные данные только из цифр
     */
    private static final String DIGIT_NAME = "01022022";
    private static final String DIGIT_SHORTNAME = "0102";
    private static final String DIGIT_DESCRIPTION = "01022022 28022022";

    /**
     * Минимальные по длине верные входные данные
     */
    private static final String MIN_NAME = "П1";
    private static final String MIN_SHORTNAME = "Пр";


    /**
     * Максимальные по длине верные входные данные
     */
    private static final String MAX_NAME = "р1рПеее11рПр1тПр1рокррткоко1тП11рр1екто11П1Поеое1р";
    private static final String MAX_SHORTNAME = "оПП1тотПк11оок1тттеотрПекеотееокПт1ет1ре";
    private static final String MAX_DESCRIPTION = "1оеПт1т1ктП1оПП1тертрПое1ПртеПтП111ртроок11трПееккооткПееПкерр11е1рПто11Пткт" +
        "ПоеПрПкорПоро111тор1отеПотоо1океПо1ек11оееоПоккторППрПкеокттП1еттПреоррПкПккПППкек11еркетроктк1ртео1еПП1еоПторекрП1" +
        "П11отккр11ПоеПте1ктктокеррее1кекерркр1ете1еертррерППоретППкрко1р";

    /**
     * Входные данные с недопустимыми спец. символами
     */
    private static final String INVALID_SPECIAL_NAME = "Проект№\"\"1";
    private static final String INVALID_SPECIAL_SHORTNAME = "Пр1\"%:?*";
    private static final String INVALID_SPECIAL_DESCRIPTION = "Проект номер 1!\"№;%:?";

    /**
     * Входные данные с длиной меньше допустимой
     */
    private static final String BELOW_MIN_NAME = "И";
    private static final String BELOW_MIN_SHORTNAME = "П";

    /**
     * Входные данные с длиной больше допустимой
     */
    private static final String OVER_MAX_NAME = "р1рПеее11рПр1тПр1рокррткоко1тП11рр1екто11П1Поеое1р999";
    private static final String OVER_MAX_SHORTNAME = "оПП1тотПк11оок1тттеотрПекеотееокПт1ет1ре999";
    private static final String OVER_MAX_DESCRIPTION = "1оеПт1т1ктП1оПП1тертрПое1ПртеПтП111ртроок11трПееккооткПееПкерр11е1р" +
        "Пто11ПтктПоеПрПкорПоро111тор1отеПотоо1океПо1ек11оееоПоккторППрПкеокттП1еттПреоррПкПккПППкек11еркетроктк1ртео1еПП1ео" +
        "ПторекрП1П11отккр11ПоеПте1ктктокеррее1кекерркр1ете1еертррерППоретППкрко1р999";

    private static final String SPACE_SIGN = " ";

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
                .clickProjectsButton()
                .clickAddButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void addProjectCancel() {
        String lastRow = mainPage.clickProjectsButton().getLastRow();

        String newLastRow = mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterName(NAME)
            .enterShortName(SHORTNAME)
            .enterDescription(DESCRIPTION)
            .clickCancelButton()
            .getLastRow();

        assertEquals(lastRow, newLastRow);
    }

    @Test
    public void idFormation() {
        int maxId = mainPage.clickProjectsButton().getFirstId();

        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterName(NAME)
            .enterShortName(SHORTNAME)
            .enterDescription(DESCRIPTION)
            .clickSaveButton();

        driver.get(MainPage.URL);
        int newMaxId = mainPage.clickProjectsButton().getFirstId();

        assertEquals(newMaxId, maxId + 1);
    }

    @Test
    public void lettersDigitsSpecials() {
        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterName(DIGIT_SPECIAL_NAME)
            .enterShortName(DIGIT_SPECIAL_SHORTNAME)
            .enterDescription(DIGIT_SPECIAL_DESCRIPTION)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickProjectsButton().getLastRow();
        String project = String.join(SPACE_SIGN, DIGIT_SPECIAL_NAME, DIGIT_SPECIAL_SHORTNAME, DIGIT_SPECIAL_DESCRIPTION);

        assertTrue(lastRow.contains(project));
    }

    @Test
    public void digitsOnly() {
        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterName(DIGIT_NAME)
            .enterShortName(DIGIT_SHORTNAME)
            .enterDescription(DIGIT_DESCRIPTION)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickProjectsButton().getLastRow();
        String project = String.join(SPACE_SIGN, DIGIT_NAME, DIGIT_SHORTNAME, DIGIT_DESCRIPTION);

        assertTrue(lastRow.contains(project));
    }

    @Test
    public void minLength() {
        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterName(MIN_NAME)
            .enterShortName(MIN_SHORTNAME)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickProjectsButton().getLastRow();
        String project = String.join(SPACE_SIGN, MIN_NAME, MIN_SHORTNAME);

        assertTrue(lastRow.contains(project));
    }

    @Test
    public void maxLength() {
        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterName(MAX_NAME)
            .enterShortName(MAX_SHORTNAME)
            .enterDescription(MAX_DESCRIPTION)
            .clickSaveButton();

        driver.get(MainPage.URL);

        String lastRow = mainPage.clickProjectsButton().getLastRow();
        String project = String.join(SPACE_SIGN, MAX_NAME, MAX_SHORTNAME, MAX_DESCRIPTION);

        assertTrue(lastRow.contains(project));
    }

    @Test
    public void nameObligation() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickAddButton()
                .enterShortName(SHORTNAME)
                .enterDescription(DESCRIPTION)
                .clickSaveButton()
                .nameLengthLabelDisplayed()
        );
    }

    @Test
    public void shortNameObligation() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickAddButton()
                .enterName(NAME)
                .enterDescription(DESCRIPTION)
                .clickSaveButton()
                .shortNameLengthLabelDisplayed()
        );
    }

    @Test
    public void nameUniqueness() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickAddButton()
                .enterName(NAME)
                .enterShortName(SHORTNAME)
                .enterDescription(DESCRIPTION)
                .clickSaveButton()
                .nameExistsLabelDisplayed()
        );
    }

    @Test
    public void shortNameUniqueness() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickAddButton()
                .enterName(UNIQUE_NAME)
                .enterShortName(SHORTNAME)
                .enterDescription(DESCRIPTION)
                .clickSaveButton()
                .shortNameExistsLabelDisplayed()
        );
    }

    @Test
    public void whitespaceValidation() {
        assertTrue(
            mainPage.
                clickProjectsButton()
                .clickAddButton()
                .enterName(SPACE_SIGN.repeat(5))
                .enterShortName(SPACE_SIGN.repeat(5))
                .enterDescription(SPACE_SIGN.repeat(10))
                .clickSaveButton()
                .allInvalidLabelsDisplayed()
        );
    }

    @Test
    public void specialSymbolValidation() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickAddButton()
                .enterName(INVALID_SPECIAL_NAME)
                .enterShortName(INVALID_SPECIAL_SHORTNAME)
                .enterDescription(INVALID_SPECIAL_DESCRIPTION)
                .clickSaveButton()
                .allInvalidLabelsDisplayed()
        );
    }

    @Test
    public void belowMinLength() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickAddButton()
                .enterName(BELOW_MIN_NAME)
                .enterShortName(BELOW_MIN_SHORTNAME)
                .clickSaveButton()
                .minLengthLabelsDisplayed()
        );
    }

    @Test
    public void overMaxLength() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickAddButton()
                .enterName(OVER_MAX_NAME)
                .enterShortName(OVER_MAX_SHORTNAME)
                .enterDescription(OVER_MAX_DESCRIPTION)
                .clickSaveButton()
                .allLengthLabelsDisplayed()
        );
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
