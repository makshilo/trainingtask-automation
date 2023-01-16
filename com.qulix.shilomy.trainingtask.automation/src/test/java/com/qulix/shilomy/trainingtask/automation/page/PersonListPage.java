package com.qulix.shilomy.trainingtask.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Объектная модель страницы списка персон
 */
public class PersonListPage {

    private final WebDriver driver;

    /**
     * Кнопка добавления персоны
     */
    @FindBy(xpath = ElementPath.ADD_PERSON_BUTTON)
    private WebElement addButton;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     * @param driver веб-драйвер
     */
    public PersonListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getAddButton() {
        return addButton;
    }

    /**
     * Клик по кнопке добавления персоны
     * @return объект AddEmployeePage
     */
    public AddPersonPage clickAddButton() {
        addButton.click();
        return new AddPersonPage(driver);
    }

    /**
     * Получение последнего идентификатора персоны
     * @return идентификатор
     */
    public int getLastId() {
        WebElement lastId = driver.findElement(By.xpath(ElementPath.LAST_PERSON_ID));

        return Integer.parseInt(lastId.getText());
    }
}
