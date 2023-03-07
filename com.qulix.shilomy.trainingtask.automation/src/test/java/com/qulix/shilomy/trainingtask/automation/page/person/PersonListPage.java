package com.qulix.shilomy.trainingtask.automation.page.person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Объектная модель страницы списка персон
 */
public class PersonListPage {

    /**
     * Колонка Идентификатор
     */
    @FindBy(xpath = "//th[contains(text(), 'Идентификатор')]")
    private WebElement idColumn;

    /**
     * Колонка Фамилия
     */
    @FindBy(xpath = "//th[contains(text(), 'Фамилия')]")
    private WebElement surnameColumn;

    /**
     * Колонка Имя
     */
    @FindBy(xpath = "//th[contains(text(), 'Имя')]")
    private WebElement nameColumn;

    /**
     * Колонка Отчество
     */
    @FindBy(xpath = "//th[contains(text(), 'Отчество')]")
    private WebElement patronymicColumn;

    /**
     * Колонка Должность
     */
    @FindBy(xpath = "//th[contains(text(), 'Должность')]")
    private WebElement positionColumn;

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

    /**
     * Кнопка добавления персоны
     */
    @FindBy(xpath = "//a[@href='/Trainingtask/addEmployee']")
    private WebElement addButton;

    private static final String TABLE_CELL = "//tbody/tr[%d]/td[%d]";

    private static final int INDEX_FIRST = 1;
    private static final int INDEX_SECOND = 2;
    private static final int INDEX_THIRD = 3;
    private static final int INDEX_FOURTH = 4;
    private static final String LINE_END_SIGN = "\n";
    private static final String SPACE_SIGN = " ";

    private final WebDriver driver;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public PersonListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов страницы
     *
     * @return если все элементы отображены true, иначе false
     */
    public boolean isPageElementsDisplayed() {
        return idColumn.isDisplayed()
            && surnameColumn.isDisplayed()
            && nameColumn.isDisplayed()
            && patronymicColumn.isDisplayed()
            && positionColumn.isDisplayed()
            && addButton.isDisplayed()
            && editButton.isDisplayed()
            && deleteButton.isDisplayed();
    }

    /**
     * Клик по кнопке добавления персоны
     *
     * @return объект AddEmployeePage
     */
    public AddPersonPage clickAddButton() {
        addButton.click();
        return new AddPersonPage(driver);
    }

    /**
     * Клик по кнопке Изменить
     *
     * @return объект EditPersonPage
     */
    public EditPersonPage clickEditButton() {
        editButton.click();
        return new EditPersonPage(driver);
    }

    /**
     * Клик по кнопке Удалить
     *
     * @return объект PersonListPage
     */
    public PersonListPage clickDeleteButton() {
        deleteButton.click();
        return new PersonListPage(driver);
    }

    /**
     * Получение ячейки таблицы
     * @param row строка
     * @param column столбец
     * @return элемент ячейки таблицы
     */
    public WebElement getTableCell(int row, int column) {
        return driver.findElement(By.xpath(String.format(TABLE_CELL, row, column)));
    }

    /**
     * Получение полного имени сотрудника по индексу
     * @param index индекс
     * @return полное имя
     */
    public String getRowNameByIndex(int index) {
        return String.join(SPACE_SIGN,
            getTableCell(index, INDEX_SECOND).getText(),
            getTableCell(index, INDEX_THIRD).getText(),
            getTableCell(index, INDEX_FOURTH).getText()
        );
    }

    /**
     * Получение всех полных имён в таблице
     * @return строка со всеми полными именами
     */
    public String getAllNames() {
        return IntStream.range(INDEX_FIRST, tableRows.size()).mapToObj(this::getRowNameByIndex).collect(Collectors.joining(LINE_END_SIGN));
    }

    /**
     * Получение первого идентификатора персоны
     *
     * @return идентификатор
     */
    public int getFirstId() {
        return Integer.parseInt(getTableCell(INDEX_FIRST, INDEX_FIRST).getText());
    }

    /**
     * Получение последнего идентификатора персоны
     *
     * @return идентификатор
     */
    public int getLastId() {
        return Integer.parseInt(getTableCell(tableRows.size(), INDEX_FIRST).getText());
    }

    /**
     * Получение последней строки таблицы
     * @return текст последней строки таблицы
     */
    public String getLastRow() {
        return tableRows.get(tableRows.size() - INDEX_FIRST).getText();
    }
}
