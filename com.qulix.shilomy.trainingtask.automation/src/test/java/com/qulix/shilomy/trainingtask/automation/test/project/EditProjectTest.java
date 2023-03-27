package com.qulix.shilomy.trainingtask.automation.test.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.model.Project;
import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.project.EditProjectPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тест формы редактирования проекта
 */
public class EditProjectTest {

    /**
     * Верные входные данные
     */
    private static final String NAME = "Проект123";
    private static final String SHORTNAME = "П123";
    private static final String DESCRIPTION = "Проект номер 123";

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
    @DisplayName("Отображение элементов формы редактирования проекта")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .editButton.click(EditProjectPage.class)
                .elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Отмена редактирования проекта")
    public void editProjectCancel() {
        assertEquals(
            getLastProject(),
            enterProject(new Project(NAME, SHORTNAME, DESCRIPTION))
                .cancelButton.click(ProjectListPage.class)
                .getLastProject()
        );
    }

    @Test
    @DisplayName("Недоступность для редактирования поля \"Идентификатор\"")
    public void idInputDisabled() {
        assertFalse(
            mainPage
                .header
                .clickProjectsButton()
                .editButton.click(EditProjectPage.class)
                .isIdInputEnabled()
        );
    }

    @Test
    @DisplayName("Возможность редактировать поля \"Название\", \"Сокращенное название\", \"Описание\"")
    public void editAllowed() {
        //Получение последнего проекта
        Project project = new Project(NAME, SHORTNAME, DESCRIPTION);

        Project updatedProject = enterProject(project)
            .saveButton.click(ProjectListPage.class)
            .getLastProject();

        //Запись идентификатора
        project.setId(updatedProject.getId());

        assertEquals(updatedProject, project);
    }

    @Test
    @DisplayName("Отсутвие возможности сохранения при удалении данных из полей \"Название\", \"Сокращенное название\"")
    public void emptyValidation() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .editButton.click(EditProjectPage.class)
                .clearInputs()
                .saveButton.click(EditProjectPage.class)
                .minLengthLabelsDisplayed()
        );
    }

    /**
     * Получение последнего проекта в списке
     *
     * @return проект
     */
    private Project getLastProject() {
        Project project = mainPage.header.clickProjectsButton().getLastProject();
        mainPage.get();
        return project;
    }

    /**
     * Ввод проекта
     *
     * @param project проект
     * @return страница изменения проекта
     */
    private EditProjectPage enterProject(Project project) {
        return mainPage.header.clickProjectsButton()
            .addButton.click(EditProjectPage.class)
            .enterProject(project);
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}