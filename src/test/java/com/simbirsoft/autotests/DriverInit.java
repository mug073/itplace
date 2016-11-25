package com.simbirsoft.autotests;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * Created by user on 24.03.2016.
 */
public class DriverInit {
    //IP машины, на которой установлен Google Chrome и запущен Selenium Server
    //private static final String REMOTE_URL = "192.168.30.177";
    //private static final String REMOTE_URL = "localhost";
    private static final String REMOTE_URL = "192.168.60.229";
    public static WebDriver driver;
    private static final String BROWSER = "chrome";
    public static int waitTime = 60; //second
    public static int waitTimeForm = 300;

    private String yandexEmailLogin = "testautoqa@simbirsoft.com";
    private String yandexEmailPass = "6oValwPgIQ";
    private String yandexLogin;
    private String yandexPswrd;

    public String getYandexLogin() {
        this.yandexLogin = yandexEmailLogin;
        return yandexLogin;
    }

    public String getYandexPassword() {
        this.yandexPswrd = yandexEmailPass;
        return yandexPswrd;
    }

    public DriverInit() {

    }

    @BeforeClass
    public static void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "./libs/chromedriver.exe");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (BROWSER.equals("chrome")) {
            capabilities = DesiredCapabilities.chrome();
            capabilities.setJavascriptEnabled(true);
            //capabilities.setCapability("chrome.binary", "/usr/lib/chromium-browser/chromium-browser");
            //capabilities.setCapability("chrome.binary", "C:\\work4food\\corpsites-automation-tests-Daily\\libs\\chromedriver.exe");
            capabilities.setCapability("chrome.binary", "/libs/chromedriver.exe");
        } else if (BROWSER.equals("firefox")) {
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setEnableNativeEvents(true);
            firefoxProfile.setPreference("plugin.state.java", 2);
            capabilities = DesiredCapabilities.firefox();
            capabilities.setJavascriptEnabled(true);
            capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        }
        try {
            URL host = new URL("http://" + REMOTE_URL + ":4444/wd/hub");
            driver = new RemoteWebDriver(host, capabilities);
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("Ошибка связи с удаленным сервером: " + REMOTE_URL + ". Возможно такой адрес не найден.\n" + e.getMessage());
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
}
