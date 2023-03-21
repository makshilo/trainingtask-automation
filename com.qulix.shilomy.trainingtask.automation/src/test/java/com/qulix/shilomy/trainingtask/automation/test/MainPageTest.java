package com.qulix.shilomy.trainingtask.automation.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.page.MainPage;
import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;
import com.qulix.shilomy.trainingtask.automation.page.task.TaskListPage;
import com.qulix.shilomy.trainingtask.automation.utils.DriverManager;

/**
 * Тесты главного меню
 */
public class MainPageTest {

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
    @DisplayName("Переход к главному меню")
    public void toMainMenu() {
        assertEquals(MainPage.URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Отображение элементов в главном меню")
    public void elementsDisplayed() {
        assertTrue(
            mainPage.elementsDisplayed()
        );
    }

    @Test
    @DisplayName("Переход на форму \"Список проектов\"")
    public void toProjectList() {
        mainPage.clickProjectsButton();
        assertEquals(ProjectListPage.URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на форму \"Список задач\"")
    public void toTaskList() {
        mainPage.clickTasksButton();
        assertEquals(TaskListPage.URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на форму \"Список персон\"")
    public void toPersonList() {
        mainPage.clickPersonsButton();
        assertEquals(PersonListPage.URL, driver.getCurrentUrl());
    }

    /**
     * После всех тестов
     */
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
