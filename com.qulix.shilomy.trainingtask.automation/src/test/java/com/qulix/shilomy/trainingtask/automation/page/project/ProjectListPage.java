package com.qulix.shilomy.trainingtask.automation.page.project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Объектная модель страницы списка проектов
 */
public class ProjectListPage {

    /**
     * Колонка Идентификатор
     */
    @FindBy(xpath = "//th[contains(text(), 'Идентификатор')]")
    private WebElement idColumn;

    /**
     * Колонка Название
     */
    @FindBy(xpath = "//th[contains(text(), 'Название')]")
    private WebElement nameColumn;

    /**
     * Колонка Сокращённое название
     */
    @FindBy(xpath = "//th[contains(text(), 'Сокращенное название')]")
    private WebElement shortNameColumn;

    /**
     * Колонка Описание
     */
    @FindBy(xpath = "//th[contains(text(), 'Описание')]")
    private WebElement descriptionColumn;

    /**
     * Кнопка Добавить
     */
    @FindBy(xpath = "//button[contains(text(),'Добавить')]")
    private WebElement addButton;

    /**
     * Последняя кнопка Изменить
     */
    @FindBy(xpath = "//tbody/tr[last()]//button[contains(text(), 'Изменить')]")
    private WebElement editButton;

    /**
     * Первая кнопка Удалить
     */
    @FindBy(xpath = "//tbody//button[contains(text(), 'Удалить')]")
    private WebElement deleteButton;

    /**
     * Строка таблицы
     */
    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> tableRows;

    private static final String TABLE_CELL = "//tbody/tr[%d]/td[%d]";

    private static final int INDEX_FIRST = 1;
    private static final int INDEX_THIRD = 3;

    private final WebDriver driver;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public ProjectListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов страницы
     *
     * @return если все элементы отображены true, иначе false
     */
    public boolean elementsDisplayed() {
        return idColumn.isDisplayed()
            && nameColumn.isDisplayed()
            && shortNameColumn.isDisplayed()
            && descriptionColumn.isDisplayed()
            && addButton.isDisplayed()
            && editButton.isDisplayed()
            && deleteButton.isDisplayed();
    }

    /**
     * Клик по кнопке Добавить
     *
     * @return объект AddEmployeePage
     */
    public AddProjectPage clickAddButton() {
        addButton.click();
        return new AddProjectPage(driver);
    }

    /**
     * Клик по кнопке Изменить
     *
     * @return объект EditProjectPage
     */
    public EditProjectPage clickEditButton() {
        editButton.click();
        return new EditProjectPage(driver);
    }

    /**
     * Клик по кнопке Удалить
     *
     * @return объект ProjectListPage
     */
    public ProjectListPage clickDeleteButton() {
        deleteButton.click();
        return new ProjectListPage(driver);
    }

    /**
     * Получение ячейки таблицы
     *
     * @param row строка
     * @param column столбец
     * @return элемент ячейки таблицы
     */
    public WebElement getTableCell(int row, int column) {
        return driver.findElement(By.xpath(String.format(TABLE_CELL, row, column)));
    }

    /**
     * Получение сокращённого имени по индексу
     *
     * @param index индекс
     * @return сокращённое имя
     */
    public String getShortNameByIndex(int index) {
        return getTableCell(index, INDEX_THIRD).getText();
    }

    /**
     * Получение первого идентификатора проекта
     *
     * @return идентификатор
     */
    public int getFirstId() {
        return Integer.parseInt(getTableCell(INDEX_FIRST, INDEX_FIRST).getText());
    }

    /**
     * Получение последней строки таблицы
     *
     * @return текст последней строки таблицы
     */
    public String getLastRow() {
        return tableRows.get(tableRows.size() - INDEX_FIRST).getText();
    }
}
