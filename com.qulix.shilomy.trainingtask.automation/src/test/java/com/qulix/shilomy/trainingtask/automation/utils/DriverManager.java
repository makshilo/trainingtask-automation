package com.qulix.shilomy.trainingtask.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Класс управляющий получением соответствующего веб драйвера
 */
public class DriverManager {

    /**
     * Тип браузера
     */
    private static final String BROWSER_TYPE = "browserType";

    /**
     * Доступные веб драйверы
     */
    private static final String CHROME = "chrome";
    private static final String EDGE = "edge";
    private static final String FIREFOX = "firefox";
    private static final String IE = "ie";
    private static final String SAFARI = "safari";

    /**
     * Сообщение об ошибке выбора драйвера
     */
    private static final String INVALID_DRIVER_NAME = "Неверное имя драйвера";

    /**
     * Объект класса
     */
    private static DriverManager instance;

    private DriverManager() {
    }

    /**
     * Получение объекта класса
     *
     * @return объект DriverManager
     */
    public static synchronized DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    /**
     * Получение веб драйвера по имени из переменной среды
     *
     * @return объект WebDriver
     */
    public WebDriver getDriver() {
        switch (System.getenv(BROWSER_TYPE)) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
            case EDGE:
                return new EdgeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            case IE:
                return new InternetExplorerDriver();
            case SAFARI:
                return new SafariDriver();
            default:
                throw new WebDriverException(INVALID_DRIVER_NAME);
        }
    }
}
