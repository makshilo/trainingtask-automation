package com.qulix.shilomy.trainingtask.automation.component.button.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qulix.shilomy.trainingtask.automation.component.button.BaseButton;

/**
 * Кнопка изменить
 */
public class EditButton extends BaseButton {

    /**
     * Путь кнопки изменения
     */
    private static final String EDIT_BUTTON_PATH = "//tr[%d]//button[contains(text(), 'Изменить')]";

    /**
     * Конструктор с инициализацией веб-драйвера и индексом кнопки
     *
     * @param index индекс
     * @param driver веб-драйвер
     */
    public EditButton(int index, WebDriver driver) {
        super(driver);
        button = driver.findElement(By.xpath(String.format(EDIT_BUTTON_PATH, index)));
    }
}
