package com.qulix.shilomy.trainingtask.automation.page.project;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.model.Project;

/**
 * Объектная модель страницы списка проектов
 */
public class ProjectListPage {

    /**
     * Корневой url приложения
     */
    private static final String ROOT_URL_PROPERTY = "rootUrl";

    /**
     * Путь списка проектов
     */
    private static final String PATH = "/projects";

    /**
     * Url списка проектов
     */
    public static final String URL = System.getenv(ROOT_URL_PROPERTY) + PATH;

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

    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private static final int FOURTH_INDEX = 4;

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
     * Получение проекта по индексу
     *
     * @param index индекс
     * @return проект
     */
    public Project getProjectByIndex(int index) {
        return new Project(
            Long.parseLong(getTableCell(index, FIRST_INDEX).getText()),
            getTableCell(index, SECOND_INDEX).getText(),
            getTableCell(index, THIRD_INDEX).getText(),
            getTableCell(index, FOURTH_INDEX).getText()
        );
    }

    /**
     * Получение проекта по идентификатору
     *
     * @param id идентификатор
     * @return проект
     */
    public Optional<Project> getProjectById(Long id) {
        return IntStream.rangeClosed(FIRST_INDEX, tableRows.size())
            .mapToObj(this::getProjectByIndex)
            .filter(project -> project.getId().equals(id))
            .findFirst();
    }

    /**
     * Получение последней строки таблицы
     *
     * @return текст последней строки таблицы
     */
    public Project getLastProject() {
        return getProjectByIndex(tableRows.size());
    }
}
