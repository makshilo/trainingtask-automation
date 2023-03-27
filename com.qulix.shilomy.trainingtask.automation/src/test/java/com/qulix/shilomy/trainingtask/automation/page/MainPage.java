package com.qulix.shilomy.trainingtask.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Объектная модель стартовой страницы приложения
 */
public class MainPage extends BasePage<MainPage> {

    /**
     * Путь главной страницы
     */
    private static final String PATH = "/";

    /**
     * Url главной страницы
     */
    public static final String URL = ROOT_URL + PATH;

    /**
     * Конструктор, инициализирующий веб-драйвер и элементы страницы
     *
     * @param driver веб-драйвер
     */
    public MainPage(WebDriver driver) {
        super(URL, driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения элементов страницы
     *
     * @return true если все элементы отображены, иначе false
     */
    public boolean elementsDisplayed() {
        return header.elementsDisplayed();
    }
}
