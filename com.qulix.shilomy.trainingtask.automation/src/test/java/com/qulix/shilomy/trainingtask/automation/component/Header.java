package com.qulix.shilomy.trainingtask.automation.component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.page.person.PersonListPage;
import com.qulix.shilomy.trainingtask.automation.page.project.ProjectListPage;
import com.qulix.shilomy.trainingtask.automation.page.task.TaskListPage;

/**
 * Заголовочное меню
 */
public class Header {

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

    /**
     * Веб-драйвер
     */
    private final WebDriver driver;

    /**
     * Конструктор с инициализацией веб-драйвера
     *
     * @param driver веб-драйвер
     */
    public Header(WebDriver driver) {
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

    /**
     * Проверка отображения элементов формы
     *
     * @return true если все элементы отображены, иначе false
     */
    public boolean elementsDisplayed() {
        return projectsButton.isDisplayed()
            && tasksButton.isDisplayed()
            && personsButton.isDisplayed();
    }
}
