package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class LocalMobileNativeDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static LocalMobileNativeDriverFactory instance = new LocalMobileNativeDriverFactory();

    private LocalMobileNativeDriverFactory() {
    }

    public static LocalMobileNativeDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String RemoteURL) {
        String Mobile_Application_Type = Config.properties.getProperty("Mobile_Application_Type");
        AppiumDriver driver = null;
        MutableCapabilities caps = null;
        MutableCapabilities sauceOptions = null;
        URL url = null;
        try {
            switch (Mobile_Application_Type) {
                case "Local_Mobile_Android_Native_Emulator":
                    /*caps = new MutableCapabilities();
                    caps.setCapability("platformName", "Android");
                    caps.setCapability("appium:app", "storage:filename=Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");  // The filename of the mobile app
                    caps.setCapability("appium:deviceName", "Samsung Galaxy S9");
                    caps.setCapability("appium:platformVersion", "10");
                    caps.setCapability("appium:automationName", "UiAutomator2");
                    sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("build", "<your build id>");
                    sauceOptions.setCapability("name", "<your test name>");
                    caps.setCapability("sauce:options", sauceOptions);
                    driver = new AndroidDriver(new URL(RemoteURL), caps);*/
                    String filePath1 = System.getProperty("user.dir") + "/src/test/resources/apps/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";

                    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
                    uiAutomator2Options.setDeviceName("emulator-5554")
                            //.setPlatformVersion("13.0")
                            .setPlatformName("android")
                            .setApp(filePath1)
                            .setAutomationName("UiAutomator2");
                            //.setAppPackage("com.swaglabsmobileapp") //if app already installed we no need to give below capabilities
                            //.setAppActivity("com.swaglabsmobileapp.MainActivity");
                    driver = new AndroidDriver(new URL(RemoteURL), uiAutomator2Options);
                    System.out.println("AndroidDriver is set");

                    break;
                case "Local_Mobile_IOS_Native_Simulator":
                    String filePath = System.getProperty("user.dir") + "/src/test/resources/apps/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.app";

                    XCUITestOptions xcuiTestOptions = new XCUITestOptions();
                    xcuiTestOptions.setDeviceName("iPhone 15 Pro Max")
                            .setPlatformVersion("17.0")
                            .setPlatformName("iOS")
                            .setAutomationName("XCUITest")
                            //.setNoReset(true)
                            .setApp(filePath);
                            //.setBundleId("com.saucelabs.SwagLabsMobileApp"); //if u do not want ot install every time we can provide bundleId if already install app
                    driver = new IOSDriver(new URL(RemoteURL), xcuiTestOptions);
                    break;
                default:
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return driver;
    }

}
