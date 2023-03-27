package com.qulix.shilomy.trainingtask.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qulix.shilomy.trainingtask.automation.component.Header;

/**
 * Базовая страница
 */
public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {

    /**
     * Переменная окружения корневого url приложения
     */
    private static final String ROOT_URL_PROPERTY = "rootUrl";

    /**
     * Корневой url приложения
     */
    protected static final String ROOT_URL = System.getenv(ROOT_URL_PROPERTY);

    private final String url;

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
    public BasePage(String url, WebDriver driver) {
        this.url = url;
        this.driver = driver;
        header = new Header(driver);
    }

    /**
     * Загрузить страницу
     */
    @Override
    protected void load() {
        driver.get(url);
    }

    /**
     * Проверка загрузки страницы
     *
     * @throws Error ошибка загрузки
     */
    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains(url));
    }
}
