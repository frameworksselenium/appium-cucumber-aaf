package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class LocalMobileBrowserDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static LocalMobileBrowserDriverFactory instance = new LocalMobileBrowserDriverFactory();

    private LocalMobileBrowserDriverFactory() {
    }

    public static LocalMobileBrowserDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String browser, String RemoteURL) {
        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");
        WebDriver driver = null;
        URL url = null;
        MutableCapabilities caps = null;
        try {
            switch (Mobile_Application_Type) {

                case "Local_Mobile_Android_Web_Emulator":
                    caps = new MutableCapabilities();
                    HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                    browserstackOptions.put("browserName", "chrome");
                    browserstackOptions.put("deviceName", "Samsung Galaxy S23 Ultra");
                    browserstackOptions.put("realMobile", "true");
                    browserstackOptions.put("osVersion", "13.0");
                    caps.setCapability("bstack:options", browserstackOptions);

                    try {
                        url = new URL(RemoteURL);
                        driver = new RemoteWebDriver(url, caps);
                        driver.manage().window().maximize();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "Local_Mobile_IOS_Web_Simulator":
                    caps = new MutableCapabilities();
                    caps.setCapability("browserName", "safari");
                    caps.setCapability("deviceName", "iPhone 15 Pro Max");
                    caps.setCapability("platformVersion", "17.0");
                    caps.setCapability("automationName","XCUITest");
                    caps.setCapability("platformName","IOS");
                    try {
                        url = new URL(RemoteURL);
                        driver = new RemoteWebDriver(url, caps);
                        driver.manage().window().maximize();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return driver;
    }
}
