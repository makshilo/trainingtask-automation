package com.qulix.shilomy.trainingtask.automation.page.project;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.component.button.impl.AddButton;
import com.qulix.shilomy.trainingtask.automation.component.button.impl.DeleteButton;
import com.qulix.shilomy.trainingtask.automation.component.button.impl.EditButton;
import com.qulix.shilomy.trainingtask.automation.model.Project;
import com.qulix.shilomy.trainingtask.automation.page.BasePage;

/**
 * Объектная модель страницы списка проектов
 */
public class ProjectListPage extends BasePage<ProjectListPage> {

    /**
     * Путь списка проектов
     */
    private static final String PATH = "/projects";

    /**
     * Url списка проектов
     */
    public static final String URL = ROOT_URL + PATH;

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
     * Кнопка добавления проекта
     */
    public AddButton addButton = new AddButton(driver);

    /**
     * Строка таблицы
     */
    private final List<WebElement> tableRows = driver.findElements(By.xpath("//tbody/tr"));

    /**
     * Последняя кнопка Изменить
     */
    public EditButton editButton = new EditButton(tableRows.size(), driver);

    /**
     * Первая кнопка Удалить
     */
    public DeleteButton deleteButton = new DeleteButton(FIRST_INDEX, driver);


    private static final String TABLE_CELL = "//tbody/tr[%d]/td[%d]";

    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private static final int FOURTH_INDEX = 4;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public ProjectListPage(WebDriver driver) {
        super(URL, driver);
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
