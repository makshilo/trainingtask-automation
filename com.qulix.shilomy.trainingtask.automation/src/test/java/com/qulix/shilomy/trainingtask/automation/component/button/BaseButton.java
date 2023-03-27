package com.qulix.shilomy.trainingtask.automation.component.button;

import java.lang.reflect.InvocationTargetException;

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
     * @param nextPage класс следующей страницы
     * @param <T> класс расширяющий базовую страницу
     * @return объект следующей страницы
     */
    public <T extends BasePage<T>> T click(Class<T> nextPage) {
        button.click();
        try {
            return nextPage.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getCause().getMessage());
        }
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
