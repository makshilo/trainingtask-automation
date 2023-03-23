package com.qulix.shilomy.trainingtask.automation.page;

import org.openqa.selenium.WebDriver;

import com.qulix.shilomy.trainingtask.automation.component.Header;

/**
 * Базовая страница
 */
public abstract class BasePage {

    /**
     * Переменная окружения корневого url приложения
     */
    private static final String ROOT_URL_PROPERTY = "rootUrl";

    /**
     * Корневой url приложения
     */
    protected static final String ROOT_URL = System.getenv(ROOT_URL_PROPERTY);

    /**
     * Заголовочное меню
     */
    public Header header;

    /**
     * Веб-драйвер
     */
    protected final WebDriver driver;

    /**
     * Конструктор инициализирующий веб-драйвер и заголовочное меню
     *
     * @param driver веб-драйвер
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        header = new Header(driver);
    }
}
