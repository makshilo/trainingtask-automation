package com.qulix.shilomy.trainingtask.automation.test.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Project;
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
                .clickProjectsButton()
                .clickAddButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void addProjectCancel() {
        Project project = mainPage.clickProjectsButton().getLastProject();

        Project newProject = mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterProject(
                new Project(
                    NAME,
                    SHORTNAME,
                    DESCRIPTION
                )
            )
            .clickCancelButton()
            .getLastProject();

        assertEquals(project, newProject);
    }

    @Test
    public void idFormation() {
        Long maxId = mainPage.clickProjectsButton().getLastProject().getId();

        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterProject(
                new Project(
                    NAME,
                    SHORTNAME,
                    DESCRIPTION
                )
            )
            .clickSaveButton();

        driver.get(MainPage.URL);
        Long newMaxId = mainPage.clickProjectsButton().getLastProject().getId();

        assertEquals(maxId + 1, newMaxId);
    }

    @Test
    public void lettersDigitsSpecials() {
        Project project = new Project(DIGIT_SPECIAL_NAME, DIGIT_SPECIAL_SHORTNAME, DIGIT_SPECIAL_DESCRIPTION);

        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterProject(project)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Project lastProject = mainPage.clickProjectsButton().getLastProject();
        project.setId(lastProject.getId());

        assertEquals(project, lastProject);
    }

    @Test
    public void digitsOnly() {
        Project project = new Project(DIGIT_NAME, DIGIT_SHORTNAME, DIGIT_DESCRIPTION);

        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterProject(project)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Project lastProject = mainPage.clickProjectsButton().getLastProject();
        project.setId(lastProject.getId());

        assertEquals(project, lastProject);
    }

    @Test
    public void minLength() {
        Project project = new Project(MIN_NAME, MIN_SHORTNAME, EMPTY_STRING);

        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterProject(project)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Project lastProject = mainPage.clickProjectsButton().getLastProject();
        project.setId(lastProject.getId());

        assertEquals(project, lastProject);
    }

    @Test
    public void maxLength() {
        Project project = new Project(MAX_NAME, MAX_SHORTNAME, MAX_DESCRIPTION);

        mainPage
            .clickProjectsButton()
            .clickAddButton()
            .enterProject(project)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Project lastProject = mainPage.clickProjectsButton().getLastProject();
        project.setId(lastProject.getId());

        assertEquals(project, lastProject);
    }

    @Test
    public void nameObligation() {
        assertTrue(
            mainPage
                .clickProjectsButton()
                .clickAddButton()
                .enterProject(
                    new Project(
                        EMPTY_STRING,
                        SHORTNAME,
                        DESCRIPTION
                    )
                )
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
                .enterProject(
                    new Project(
                        NAME,
                        EMPTY_STRING,
                        DESCRIPTION
                    )
                )
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
                .enterProject(
                    new Project(
                        NAME,
                        SHORTNAME,
                        DESCRIPTION
                    )
                )
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
                .enterProject(
                    new Project(
                        UNIQUE_NAME,
                        SHORTNAME,
                        DESCRIPTION
                    )
                )
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
                .enterProject(
                    new Project(
                        SPACE_SIGN.repeat(5),
                        SPACE_SIGN.repeat(5),
                        SPACE_SIGN.repeat(10)
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
                .clickProjectsButton()
                .clickAddButton()
                .enterProject(
                    new Project(
                        INVALID_SPECIAL_NAME,
                        INVALID_SPECIAL_SHORTNAME,
                        INVALID_SPECIAL_DESCRIPTION
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
                .clickProjectsButton()
                .clickAddButton()
                .enterProject(
                    new Project(
                        BELOW_MIN_NAME,
                        BELOW_MIN_SHORTNAME,
                        EMPTY_STRING
                    )
                )
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
                .enterProject(
                    new Project(
                        OVER_MAX_NAME,
                        OVER_MAX_SHORTNAME,
                        OVER_MAX_DESCRIPTION
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
