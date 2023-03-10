package com.qulix.shilomy.trainingtask.automation.page;

import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;
import com.qulix.shilomy.trainingtask.automation.page.task.TaskListPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Объектная модель стартовой страницы приложения
 */
public class MainPage {

    /**
     * Url главной страницы
     */
    public static final String URL = "http://localhost:8080/Trainingtask/";

    /**
     * Ссылка на страницу списка проектов
     */
    @FindBy(xpath = "//a[@href='/Trainingtask/projects']")
    private WebElement projectsButton;

    /**
     * Ссылка на страницу списка задач
     */
    @FindBy(xpath = "//a[@href='/Trainingtask/tasks']")
    private WebElement tasksButton;

    /**
     * Ссылка на страницу списка персон
     */
    @FindBy(xpath = "//a[@href='/Trainingtask/employees']")
    private WebElement personsButton;

    private final WebDriver driver;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Клик по ссылке на список проектов
     *
     * @return объект EmployeeListPage
     */
    public ProjectListPage clickProjectsButton() {
        projectsButton.click();
        return new ProjectListPage(driver);
    }

    /**
     * Клик по ссылке на список задач
     *
     * @return объект EmployeeListPage
     */
    public TaskListPage clickTasksButton() {
        tasksButton.click();
        return new TaskListPage(driver);
    }

    /**
     * Клик по ссылке на список персон
     *
     * @return объект EmployeeListPage
     */
    public PersonListPage clickPersonsButton() {
        personsButton.click();
        return new PersonListPage(driver);
    }


    public boolean elementsDisplayed() {
        return projectsButton.isDisplayed()
            && tasksButton.isDisplayed()
            && personsButton.isDisplayed();
    }
}
