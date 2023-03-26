package com.qulix.shilomy.trainingtask.automation.test.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Project;
import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.project.AddProjectPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тесты формы добавления проекта
 */
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
    @DisplayName("Отображение элементов формы добавления проекта")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .header.clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Отмена создания проекта")
    public void addProjectCancel() {
        //Ролучение последнего проекта
        Project project = mainPage.header.clickProjectsButton().getLastProject();

        //Получение последнего проекта после отмены
        Project newProject = mainPage
            .header
            .clickProjectsButton()
            .addButton.click(AddProjectPage.class)
            .enterProject(
                new Project(
                    NAME,
                    SHORTNAME,
                    DESCRIPTION
                )
            )
            .cancelButton.click(ProjectListPage.class)
            .getLastProject();

        assertEquals(project, newProject);
    }

    @Test
    @DisplayName("Соответствие идентификатора порядковому номеру проекта")
    public void idFormation() {
        //Получение последнего идентификатора
        Long maxId = mainPage.header.clickProjectsButton().getLastProject().getId();

        Long newMaxId = mainPage
            .header
            .clickProjectsButton()
            .addButton.click(AddProjectPage.class)
            .enterProject(
                new Project(
                    NAME,
                    SHORTNAME,
                    DESCRIPTION
                )
            )
            .saveButton.click(ProjectListPage.class)
            .getLastProject()
            .getId();

        assertEquals(maxId + 1, newMaxId);
    }

    @Test
    @DisplayName("Сохранение проекта при заполнении полей допустимыми буквами, цифрами и спецсимволами")
    public void lettersDigitsSpecials() {
        Project project = new Project(DIGIT_SPECIAL_NAME, DIGIT_SPECIAL_SHORTNAME, DIGIT_SPECIAL_DESCRIPTION);
        Project lastProject = mainPage
            .header
            .clickProjectsButton()
            .addButton.click(AddProjectPage.class)
            .enterProject(project)
            .saveButton.click(ProjectListPage.class)
            .getLastProject();

        //Запись идентификатора
        project.setId(lastProject.getId());

        assertEquals(project, lastProject);
    }

    @Test
    @DisplayName("Сохранение проекта при заполнении полей  цифрами")
    public void digitsOnly() {
        Project project = new Project(DIGIT_NAME, DIGIT_SHORTNAME, DIGIT_DESCRIPTION);
        Project lastProject = mainPage
            .header
            .clickProjectsButton()
            .addButton.click(AddProjectPage.class)
            .enterProject(project)
            .saveButton.click(ProjectListPage.class)
            .getLastProject();

        //Запись идентификатора
        project.setId(lastProject.getId());

        assertEquals(project, lastProject);
    }

    @Test
    @DisplayName("Сохранение проекта при заполнении полей минимальным количеством символов")
    public void minLength() {
        Project project = new Project(MIN_NAME, MIN_SHORTNAME, EMPTY_STRING);
        Project lastProject = mainPage
            .header
            .clickProjectsButton()
            .addButton.click(AddProjectPage.class)
            .enterProject(project)
            .saveButton.click(ProjectListPage.class)
            .getLastProject();

        //Запись идентификатора
        project.setId(lastProject.getId());

        assertEquals(project, lastProject);
    }

    @Test
    @DisplayName("Сохранение проекта при заполнении полей максимальным количеством символов")
    public void maxLength() {
        Project project = new Project(MAX_NAME, MAX_SHORTNAME, MAX_DESCRIPTION);
        Project lastProject = mainPage
            .header
            .clickProjectsButton()
            .addButton.click(AddProjectPage.class)
            .enterProject(project)
            .saveButton.click(ProjectListPage.class)
            .getLastProject();

        //Запись идентификатора
        project.setId(lastProject.getId());

        assertEquals(project, lastProject);
    }

    @Test
    @DisplayName("Обязательность заполнения поля \"Название\"")
    public void nameObligation() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .enterProject(
                    new Project(
                        EMPTY_STRING,
                        SHORTNAME,
                        DESCRIPTION
                    )
                )
                .saveButton.click(AddProjectPage.class)
                .nameLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность заполнения поля \"Сокращенное название\"")
    public void shortNameObligation() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .enterProject(
                    new Project(
                        NAME,
                        EMPTY_STRING,
                        DESCRIPTION
                    )
                )
                .saveButton.click(AddProjectPage.class)
                .shortNameLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при попытке сохранения неуникальных данных в поле \"Название\"")
    public void nameUniqueness() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .enterProject(
                    new Project(
                        NAME,
                        SHORTNAME,
                        DESCRIPTION
                    )
                )
                .saveButton.click(AddProjectPage.class)
                .nameExistsLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при попытке сохранения неуникальных данных в поле \"Сокращённое название\"")
    public void shortNameUniqueness() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .enterProject(
                    new Project(
                        UNIQUE_NAME,
                        SHORTNAME,
                        DESCRIPTION
                    )
                )
                .saveButton.click(AddProjectPage.class)
                .shortNameExistsLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при  заполнении полей пробелами")
    public void whitespaceValidation() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .enterProject(
                    new Project(
                        SPACE_SIGN.repeat(5),
                        SPACE_SIGN.repeat(5),
                        SPACE_SIGN.repeat(10)
                    )
                )
                .saveButton.click(AddProjectPage.class)
                .allInvalidLabelsDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при  заполнении полей недопустимыми спецсимволами")
    public void specialSymbolValidation() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .enterProject(
                    new Project(
                        INVALID_SPECIAL_NAME,
                        INVALID_SPECIAL_SHORTNAME,
                        INVALID_SPECIAL_DESCRIPTION
                    )
                )
                .saveButton.click(AddProjectPage.class)
                .allInvalidLabelsDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей  количеством символов, меньше минимального")
    public void belowMinLength() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .enterProject(
                    new Project(
                        BELOW_MIN_NAME,
                        BELOW_MIN_SHORTNAME,
                        EMPTY_STRING
                    )
                )
                .saveButton.click(AddProjectPage.class)
                .minLengthLabelsDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей количеством символов, больше максимального")
    public void overMaxLength() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .addButton.click(AddProjectPage.class)
                .enterProject(
                    new Project(
                        OVER_MAX_NAME,
                        OVER_MAX_SHORTNAME,
                        OVER_MAX_DESCRIPTION
                    )
                )
                .saveButton.click(AddProjectPage.class)
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
