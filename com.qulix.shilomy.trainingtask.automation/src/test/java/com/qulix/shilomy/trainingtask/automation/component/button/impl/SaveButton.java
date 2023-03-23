package com.qulix.shilomy.trainingtask.automation.component.button.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.component.button.BaseButton;

/**
 * Кнопка сохранить
 */
public class SaveButton extends BaseButton {

    /**
     * Элемент кнопки сохранения
     */
    @FindBy(xpath = "//input[@value='Сохранить']")
    private WebElement saveButton;

    /**
     * Конструктор с инициализацией веб-драйвера
     *
     * @param driver веб-драйвер
     */
    public SaveButton(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        button = saveButton;
    }
}
