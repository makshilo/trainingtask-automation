package com.qulix.shilomy.trainingtask.automation.page.person;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.model.Person;

/**
 * Объектная модель страницы списка персон
 */
public class PersonListPage {

    /**
     * Корневой url приложения
     */
    private static final String ROOT_URL_PROPERTY = "rootUrl";

    /**
     * Путь списка персон
     */
    private static final String PATH = "/employees";

    /**
     * Url списка персон
     */
    public static final String URL = System.getenv(ROOT_URL_PROPERTY) + PATH;

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

    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private static final int FOURTH_INDEX = 4;
    private static final int FIFTH_INDEX = 5;

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
     * Получение персоны по индексу
     *
     * @param index индекс
     * @return объект Person
     */
    public Person getPersonByIndex(int index) {
        return new Person(
            Long.parseLong(getTableCell(index, FIRST_INDEX).getText()),
            getTableCell(index, SECOND_INDEX).getText(),
            getTableCell(index, THIRD_INDEX).getText(),
            getTableCell(index, FOURTH_INDEX).getText(),
            getTableCell(index, FIFTH_INDEX).getText()
        );
    }

    /**
     * Получение персоны по идентификатору
     *
     * @param id идентификатор
     * @return персона
     */
    public Optional<Person> getPersonById(Long id) {
        return IntStream.rangeClosed(FIRST_INDEX, tableRows.size())
            .mapToObj(this::getPersonByIndex)
            .filter(person -> person.getId().equals(id))
            .findFirst();
    }

    /**
     * Получение первого идентификатора персоны
     *
     * @return идентификатор
     */
    public Long getFirstId() {
        return getPersonByIndex(FIRST_INDEX).getId();
    }

    /**
     * Получение последней строки таблицы
     *
     * @return текст последней строки таблицы
     */
    public Person getLastPerson() {
        return getPersonByIndex(tableRows.size());
    }
}
