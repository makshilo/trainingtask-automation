package com.qulix.shilomy.trainingtask.automation.test.task;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import com.qulix.shilomy.trainingtask.automation.page.task.AddTaskPage;
import com.qulix.shilomy.trainingtask.automation.page.task.TaskListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тесты формы добавления задачи
 */
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
    @DisplayName("Отображение элементов формы добавления задачи")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .header
                .clickTasksButton()
                .clickAddButton()
                .elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Отмена создания задачи")
    public void addTaskCancel() {
        //Получение последней задачи
        Task lastRow = mainPage.header.clickTasksButton().getLastTask();

        //Получение задачи после отмены
        Task newLastRow = mainPage
            .header
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
            .cancelButton.click(new TaskListPage(driver))
            .getLastTask();

        assertEquals(lastRow, newLastRow);
    }

    @Test
    @DisplayName("Сохранение задачи")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Соответствие идентификатора порядковому номеру задачи")
    public void idFormation() {
        //Получение последнего идентификатора
        Long maxId = mainPage.header.clickTasksButton().getLastTask().getId();

        Long newMaxId = mainPage
            .header
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
            .saveButton.click(new TaskListPage(driver))
            .getLastTask().getId();

        assertEquals(newMaxId, maxId + 1);
    }

    @Test
    @DisplayName("Сохранение задачи при заполнении поля \"Название\" цифрами")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи при заполнении поля \"Название\" значениями с буквами, цифрами и допустимыми спецсимволами")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи при заполнении полей \"Название\" и \"Работа\" минимальным количеством символов")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи при заполнении полей \"Название\" и \"Работа\" максимальным количеством символов")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи при заполнении полей \"Дата начала\" и \"Дата окончания\" минимальными датами")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи при заполнении полей \"Дата начала\" и \"Дата окончания\" максимальными датами")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи со статусом \"Не начата\"")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи со статусом \"Завершена\"")
    public void statusCompleted() {
        Task task = new Task(
            getProject(FIRST_INDEX),
            NAME,
            WORK,
            MID_START_DATE,
            MID_END_DATE,
            List.of(getPerson(FIRST_INDEX)),
            TaskStatus.of(STATUS_COMPLETED)
        );

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи со статусом \"Отложена\"")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи с выбором нескольких исполнителей из списка")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .saveButton.click(new TaskListPage(driver)).getLastTask();

        //Запись идентификатора
        task.setId(lastTask.getId());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Сохранение задачи с выбором всех исполнителей из списка")
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

        Task lastTask = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .enterTask(task)
            .selectAllExecutors()
            .saveButton.click(new TaskListPage(driver))
            .getLastTask();

        //Запись идентификатора
        task.setExecutors(lastTask.getExecutors());

        assertEquals(task, lastTask);
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при  заполнении полей \"Название\" и \"Работа\" недопустимыми спецсимволами")
    public void specialSymbolValidation() {
        assertTrue(
            mainPage
                .header
                .clickTasksButton()
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
                .saveButton.click(new AddTaskPage(driver))
                .nameWorkInvalidLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при  заполнении полей \"Название\" и \"Работа\" пробелами")
    public void whitespaceValidation() {
        assertTrue(
            mainPage
                .header
                .clickTasksButton()
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
                .saveButton.click(new AddTaskPage(driver))
                .nameWorkInvalidLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей \"Название\" количеством символов, меньше минимального")
    public void belowMinLength() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .nameLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей \"Название\" и \"Работа\" количеством символов, больше максимального")
    public void overMaxLength() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .nameWorkLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при сохранении задачи с датой окончания раньше даты начала")
    public void dateCollision() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .dateCollisionLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей \"Дата начала\" и \"Дата окончания\" датами, раньше минимальной")
    public void belowMinDate() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .allDateInvalidLabelsDisplayed()
        );
    }

    @Test
    @DisplayName("Отображение валидационного сообщения при заполнении полей \"Дата начала\" и \"Дата окончания\" датами, позже максимальной")
    public void overMaxDate() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .allDateInvalidLabelsDisplayed()
        );
    }

    @Test
    @DisplayName("Отсутствие возможности выбрать несколько статусов")
    public void severalStatuses() {
        assertEquals(
            mainPage
                .header
                .clickTasksButton()
                .clickAddButton()
                .selectStatusByText(STATUS_POSTPONED)
                .selectStatusByText(STATUS_COMPLETED)
                .getSelectedStatus(),
            STATUS_COMPLETED
        );
    }

    @Test
    @DisplayName("Отсутствие возможности выбрать несколько проектов")
    public void severalProjects() {
        String choice = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .selectProjectByIndex(FIRST_INDEX)
            .selectProjectByIndex(SECOND_INDEX)
            .getSelectedProject();

        driver.get(MainPage.URL);

        String project = mainPage
            .header
            .clickTasksButton()
            .clickAddButton()
            .selectProjectByIndex(SECOND_INDEX)
            .getSelectedProject();

        assertEquals(choice, project);
    }

    @Test
    @DisplayName("Обязательность поля \"Название\"")
    public void nameObligation() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .nameLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность поля \"Работа\"")
    public void workObligation() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .workLengthLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность поля \"Дата начала\"")
    public void startDateObligation() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .startDateInvalidLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность поля \"Дата окончания\"")
    public void endDateObligation() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .endDateInvalidLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность поля \"Статус\"")
    public void statusObligation() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .statusInvalidLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность поля \"Исполнитель\"")
    public void executorObligation() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .executorInvalidLabelDisplayed()
        );
    }

    @Test
    @DisplayName("Обязательность поля \"Проект\"")
    public void projectObligation() {
        assertTrue(
            mainPage
                .header
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
                .saveButton.click(new AddTaskPage(driver))
                .projectInvalidLabelDisplayed()
        );
    }

    /**
     * Получение персоны по индексу
     *
     * @param index индекс
     * @return персона
     */
    private Person getPerson(int index) {
        driver.get(PersonListPage.URL);
        PersonListPage persons = new PersonListPage(driver);
        Person person = persons.getPersonByIndex(index);
        driver.navigate().back();
        return person;
    }

    /**
     * Получение проекта по индексу
     *
     * @param index индекс
     * @return проект
     */
    private Project getProject(int index) {
        driver.get(ProjectListPage.URL);
        ProjectListPage projects = new ProjectListPage(driver);
        Project project = projects.getProjectByIndex(index);
        driver.navigate().back();
        return project;
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
