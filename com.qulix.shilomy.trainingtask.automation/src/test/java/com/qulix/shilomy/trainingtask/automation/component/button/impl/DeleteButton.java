package com.qulix.shilomy.trainingtask.automation.component.button.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qulix.shilomy.trainingtask.automation.component.button.BaseButton;

/**
 * Кнопка удалить
 */
public class DeleteButton extends BaseButton {

    /**
     * Путь кнопки удалить
     */
    private static final String DELETE_BUTTON_PATH = "//tr[%d]//button[contains(text(), 'Удалить')]";

    /**
     * Конструктор с инициализацией веб-драйвера и индексом кнопки
     *
     * @param index индекс
     * @param driver веб-драйвер
     */
    public DeleteButton(int index, WebDriver driver) {
        super(driver);
        button = driver.findElement(By.xpath(String.format(DELETE_BUTTON_PATH, index)));
    }
}
