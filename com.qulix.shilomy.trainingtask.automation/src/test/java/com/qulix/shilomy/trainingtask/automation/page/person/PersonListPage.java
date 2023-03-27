package com.qulix.shilomy.trainingtask.automation.page.person;

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
import com.qulix.shilomy.trainingtask.automation.model.Person;
import com.qulix.shilomy.trainingtask.automation.page.BasePage;

/**
 * Объектная модель страницы списка персон
 */
public class PersonListPage extends BasePage<PersonListPage> {

    /**
     * Путь списка персон
     */
    private static final String PATH = "/employees";

    /**
     * Url списка персон
     */
    public static final String URL = ROOT_URL + PATH;

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
     * Кнопка добавления персоны
     */
    public final AddButton addButton = new AddButton(driver);

    /**
     * Список строк таблицы
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
    private static final int FIFTH_INDEX = 5;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public PersonListPage(WebDriver driver) {
        super(URL, driver);
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
     * Получение последней персоны
     *
     * @return персона
     */
    public Person getLastPerson() {
        return getPersonByIndex(tableRows.size());
    }
}
