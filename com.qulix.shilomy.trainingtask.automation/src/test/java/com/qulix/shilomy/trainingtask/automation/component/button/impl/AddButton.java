package com.qulix.shilomy.trainingtask.automation.component.button.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.component.button.BaseButton;

/**
 * Кнопка добавления
 */
public class AddButton extends BaseButton {

    /**
     * Элемент кнопки добавления
     */
    @FindBy(xpath = "//button[contains(text(), 'Добавить')]")
    private WebElement addButton;

    /**
     * Конструктор с инициализацией веб-драйвера
     *
     * @param driver веб-драйвер
     */
    public AddButton(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        button = addButton;
    }
}
