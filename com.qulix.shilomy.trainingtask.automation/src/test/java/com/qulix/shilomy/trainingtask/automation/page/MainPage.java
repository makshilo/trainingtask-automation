package com.qulix.shilomy.trainingtask.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Объектная модель стартовой страницы приложения
 */
public class MainPage {
    private final WebDriver driver;

    /**
     * Ссылка на страницу списка персон
     */
    @FindBy(xpath = ElementPath.PERSONS_LINK)
    private WebElement employeesLink;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     * @param driver веб-драйвер
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getEmployeesLink() {
        return employeesLink;
    }

    /**
     * Клик по ссылке на список персон
     * @return объект EmployeeListPage
     */
    public PersonListPage clickPersonsLink() {
        employeesLink.click();
        return new PersonListPage(driver);
    }
}
