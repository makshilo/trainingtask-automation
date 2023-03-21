package com.qulix.shilomy.trainingtask.automation.test.task;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.model.Project;
import com.qulix.shilomy.trainingtask.automation.model.Task;
import com.qulix.shilomy.trainingtask.automation.model.TaskStatus;
import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

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
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
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
                .clickTasksButton()
                .clickAddButton()
                .elementsDisplayed()
        );
    }

    @Test
    public void addTaskCancel() {
        Task lastRow = mainPage.clickTasksButton().getLastTask();

        Task newLastRow = mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(
                new Task(
                    getProject(FIRST_INDEX),
                    NAME,
                    WORK,
                    START_DATE,
                    END_DATE,
                    List.of(getPerson(FIRST_INDEX)),
                    TaskStatus.of(STATUS_IN_PROCESS)
                )
            )
            .clickCancelButton()
            .getLastTask();

        assertEquals(lastRow, newLastRow);
    }

    @Test
    public void addTask() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            NAME,
            WORK,
            START_DATE,
            END_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_IN_PROCESS)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void idFormation() {
        Long maxId = mainPage.clickTasksButton().getLastTask().getId();

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(
                new Task(
                    getProject(FIRST_INDEX),
                    NAME,
                    WORK,
                    START_DATE,
                    END_DATE,
                    List.of(getPerson(FIRST_INDEX)),
                    TaskStatus.of(STATUS_IN_PROCESS)
                )
            )
            .clickSaveButton();

        driver.get(MainPage.URL);
        Long newMaxId = mainPage.clickTasksButton().getLastTask().getId();

        assertEquals(newMaxId, maxId + 1);
    }

    @Test
    public void digitsName() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            DIGIT_NAME,
            WORK,
            START_DATE,
            END_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_IN_PROCESS)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(
                task
            )
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void lettersDigitsSpecialsName() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            DIGIT_SPECIAL_NAME,
            WORK,
            START_DATE,
            END_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_IN_PROCESS)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void minLength() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            MIN_NAME,
            WORK,
            START_DATE,
            END_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_IN_PROCESS)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(
                task
            )
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void maxLength() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            MAX_NAME,
            MAX_WORK,
            START_DATE,
            END_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_IN_PROCESS)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void minDate() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            MIN_NAME,
            WORK,
            MIN_DATE,
            MIN_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_IN_PROCESS)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void maxDate() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            MIN_NAME,
            WORK,
            MAX_DATE,
            MAX_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_IN_PROCESS)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void statusNotStarted() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            NAME,
            WORK,
            MID_START_DATE,
            MID_END_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_NOT_STARTED)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void statusPostponed() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            NAME,
            WORK,
            MID_START_DATE,
            MID_END_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_POSTPONED)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void multipleExecutors() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            NAME,
            WORK,
            MID_START_DATE,
            MID_END_DATE,
            List.of(getPerson(FIRST_INDEX), getPerson(SECOND_INDEX)),
            TaskStatus.of(STATUS_POSTPONED)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    public void allExecutors() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            NAME,
            WORK,
            MID_START_DATE,
            MID_END_DATE,
            Collections.emptyList(),
            TaskStatus.of(STATUS_POSTPONED)
        );

        mainPage
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .selectAllExecutors()
            .clickSaveButton();

        driver.get(MainPage.URL);

        Task lastTask = mainPage.clickTasksButton().getLastTask();
        task.setId(lastTask.getId());
        task.setExecutors(lastTask.getExecutors());

        assertEquals(task, lastTask);
    }

    @Test
    public void specialSymbolValidation() {
        assertTrue(
            mainPage.
                clickTasksButton()
                .clickAddButton()
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        INVALID_SPECIAL_NAME,
                        INVALID_SPECIAL_WORK,
                        MID_START_DATE,
                        MID_END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_POSTPONED)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        SPACE_SIGN.repeat(4),
                        SPACE_SIGN.repeat(2),
                        MID_START_DATE,
                        MID_END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_POSTPONED)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        BELOW_MIN_NAME,
                        WORK,
                        MID_START_DATE,
                        MID_END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_POSTPONED)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        OVER_MAX_NAME,
                        OVER_MAX_WORK,
                        MID_START_DATE,
                        MID_END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_POSTPONED)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        NAME,
                        WORK,
                        COLLISION_START_DATE,
                        COLLISION_END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        NAME,
                        WORK,
                        BELOW_MIN_START_DATE,
                        BELOW_MIN_END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        NAME,
                        WORK,
                        OVER_MAX_START_DATE,
                        OVER_MAX_END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        EMPTY_STRING,
                        WORK,
                        START_DATE,
                        END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        NAME,
                        EMPTY_STRING,
                        START_DATE,
                        END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        NAME,
                        WORK,
                        EMPTY_STRING,
                        END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        NAME,
                        WORK,
                        START_DATE,
                        EMPTY_STRING,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        NAME,
                        WORK,
                        START_DATE,
                        END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        null
                    )
                )
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
                .enterTask(
                    new Task(
                        getProject(FIRST_INDEX),
                        NAME,
                        WORK,
                        START_DATE,
                        END_DATE,
                        Collections.emptyList(),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
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
                .enterTask(
                    new Task(
                        null,
                        NAME,
                        WORK,
                        START_DATE,
                        END_DATE,
                        List.of(getPerson(FIRST_INDEX)),
                        TaskStatus.of(STATUS_IN_PROCESS)
                    )
                )
                .clickSaveButton()
                .projectInvalidLabelDisplayed()
        );
    }

    private Person getPerson(int index) {
        driver.get(PersonListPage.URL);
        PersonListPage persons = new PersonListPage(driver);
        Person person = persons.getPersonByIndex(index);
        driver.navigate().back();
        return person;
    }

    private Project getProject(int index) {
        driver.get(ProjectListPage.URL);
        ProjectListPage projects = new ProjectListPage(driver);
        Project project = projects.getProjectByIndex(index);
        driver.navigate().back();
        return project;
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
