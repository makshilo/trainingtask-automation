package com.qulix.shilomy.trainingtask.automation.component.button;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qulix.shilomy.trainingtask.automation.page.BasePage;

/**
 * Базовая кнопка
 */
public abstract class BaseButton {

    /**
     * Элемент кнопки
     */
    protected WebElement button;

    /**
     * Веб-драйвер
     */
    protected WebDriver driver;

    /**
     * Конструктор с инициализацией веб-драйвера
     *
     * @param driver веб-драйвер
     */
    public BaseButton(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод нажатия по кнопке
     *
     * @param nextPage следующая страница
     * @param <T> класс расширяющий базовую страницу
     * @return объект следующей страницы
     */
    public <T extends BasePage> T click(T nextPage) {
        button.click();
        return nextPage;
    }

    /**
     * Проверка отображения кнопки
     *
     * @return true если кнопка отображена, иначе false
     */
    public boolean isDisplayed() {
        return button.isDisplayed();
    }
}
