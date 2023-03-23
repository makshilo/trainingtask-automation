package com.qulix.shilomy.trainingtask.automation.test.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.project.AddProjectPage;
import com.qulix.shilomy.trainingtask.automation.page.project.EditProjectPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тесты формы списка проектов
 */
public class ProjectListTest {

    public static final int FIRST_INDEX = 1;
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
    @DisplayName("Отображение элементов формы \"Список проектов\"")
    public void elementsDisplayed() {
        assertTrue(
            mainPage
                .header
                .clickProjectsButton()
                .elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Переход на форму добавления проекта")
    public void toAddProjectForm() {
        mainPage
            .header
            .clickProjectsButton()
            .clickAddButton();

        assertTrue(driver.getCurrentUrl().contains(AddProjectPage.URL));
    }

    @Test
    @DisplayName("Переход на форму редактирования проекта")
    public void toEditProjectForm() {
        mainPage
            .header
            .clickProjectsButton()
            .clickEditButton();

        assertTrue(driver.getCurrentUrl().contains(EditProjectPage.URL));
    }

    @Test
    @DisplayName("Удаление проекта")
    public void deleteProject() {
        Long lastId = mainPage
            .header
            .clickProjectsButton()
            .getProjectByIndex(FIRST_INDEX)
            .getId();

        Long newLastId = mainPage
            .header
            .clickProjectsButton()
            .clickDeleteButton()
            .getProjectByIndex(FIRST_INDEX)
            .getId();

        assertNotSame(lastId, newLastId);
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
