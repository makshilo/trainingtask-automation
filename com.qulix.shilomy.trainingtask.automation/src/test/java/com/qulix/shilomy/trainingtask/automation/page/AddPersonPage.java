package com.qulix.shilomy.trainingtask.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Объектная модель страницы добавления персоны
 */
public class AddPersonPage {

    private final WebDriver driver;

    /**
     * Заголовочный лейбл
     */
    @FindBy(xpath = ElementPath.TITLE_LABEL)
    private WebElement titleLabel;

    /**
     * Поле ввода фамилии
     */
    @FindBy(xpath = ElementPath.SURNAME_INPUT)
    private WebElement surnameInput;

    /**
     * Поле ввода имени
     */
    @FindBy(xpath = ElementPath.NAME_INPUT)
    private WebElement nameInput;

    /**
     * Поле ввода отчества
     */
    @FindBy(xpath = ElementPath.PATRONYMIC_INPUT)
    private WebElement patronymicInput;

    /**
     * Поле ввода должности
     */
    @FindBy(xpath = ElementPath.POSITION_INPUT)
    private WebElement positionInput;

    /**
     * Лейбл ошибки должности
     */
    @FindBy(xpath = ElementPath.POSITION_ERROR_LABEL)
    private WebElement positionErrorLabel;

    /**
     * Кнопка сохранения персоны
     */
    @FindBy(xpath = ElementPath.SAVE_PERSON_BUTTON)
    private WebElement saveButton;

    /**
     * Кнопка отмены сохранения персоны
     */
    @FindBy(xpath = ElementPath.CANCEL_PERSON_BUTTON)
    private WebElement cancelButton;

    /**
     * Список элементов формы
     */
    private final List<WebElement> formElements;

    /**
     * Конструктор, инициализирующий веб-драйвер, элементы страницы и список элементов формы
     * @param driver веб-драйвер
     */
    public AddPersonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        formElements = new ArrayList<>();
        formElements.add(titleLabel);
        formElements.add(surnameInput);
        formElements.add(nameInput);
        formElements.add(patronymicInput);
        formElements.add(positionInput);
        formElements.add(saveButton);
        formElements.add(cancelButton);
    }

    public WebElement getTitleLabel() {
        return titleLabel;
    }

    public WebElement getSurnameInput() {
        return surnameInput;
    }

    public WebElement getNameInput() {
        return nameInput;
    }

    public WebElement getPatronymicInput() {
        return patronymicInput;
    }

    public WebElement getPositionInput() {
        return positionInput;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getPositionErrorLabel() {
        return positionErrorLabel;
    }

    /**
     * Ввод фамилии
     * @param surname фамилия
     * @return текущее состояние страницы
     */
    public AddPersonPage enterSurname(String surname) {
        surnameInput.clear();
        surnameInput.sendKeys(surname);
        return this;
    }

    /**
     * Ввод имени
     * @param name имя
     * @return текущее состояние страницы
     */
    public AddPersonPage enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    /**
     * Ввод отчества
     * @param patronymic отчество
     * @return текущее состояние страницы
     */
    public AddPersonPage enterPatronymic(String patronymic) {
        patronymicInput.clear();
        patronymicInput.sendKeys(patronymic);
        return this;
    }

    /**
     * Ввод должности
     * @param position должность
     * @return текущее состояние страницы
     */
    public AddPersonPage enterPosition(String position) {
        positionInput.clear();
        positionInput.sendKeys(position);
        return this;
    }

    /**
     * Клик по кнопке сохранения персоны
     * @return текущее состояние страницы
     */
    public AddPersonPage clickSaveButton() {
        saveButton.click();
        return this;
    }

    /**
     * Клик по кнопке отмены
     * @return страница списка персон
     */
    public PersonListPage clickCancelButton() {
        cancelButton.click();
        return new PersonListPage(driver);
    }

    /**
     * Проверка отображения элементов формы
     * @return если все элементы отображены true, иначе false
     */
    public boolean isFormElementsDisplayed() {
        return formElements.stream().allMatch(WebElement::isDisplayed);
    }

    /**
     * Проверка отображения лейбла ошибки должности
     * @return если лейбл отображён true, иначе false
     */
    public boolean isPositionErrorLabelDisplayed() {
        return positionErrorLabel.isDisplayed();
    }
}
