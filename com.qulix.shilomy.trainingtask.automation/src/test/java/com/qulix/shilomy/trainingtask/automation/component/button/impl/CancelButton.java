package com.qulix.shilomy.trainingtask.automation.component.button.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qulix.shilomy.trainingtask.automation.component.button.BaseButton;

/**
 * Кнопка отмена
 */
public class CancelButton extends BaseButton {

    /**
     * Элемент кнопки отмена
     */
    @FindBy(xpath = "//button[contains(text(), 'Отмена')]")
    private WebElement cancelButton;

    /**
     * Конструктор с инициализацией веб-драйвера
     *
     * @param driver веб-драйвер
     */
    public CancelButton(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        button = cancelButton;
    }
}
