package com.open.hotel.mobilepages;

import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import com.open.hotel.uiUtils.UIUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MobileLogin extends UIUtils {

	org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariables().getVar("testCaseID").toString());
	WebDriver driver = null;
	/*@AndroidFindBy(accessibility = "test-Username")
	@iOSXCUITFindBy(accessibility = "test-Username")
	public WebElement UserName;

	@AndroidFindBy(accessibility = "test-Password")
	@iOSXCUITFindBy(accessibility = "test-Password")
	public WebElement Password;

	@AndroidFindBy(accessibility = "test-LOGIN")
	@iOSXCUITFindBy(accessibility = "test-LOGIN")
	public WebElement Login;*/


	String pageName = "Login Page";
	@FindBy(how = How.XPATH, using = "//XCUIElementTypeTextField[@name='test-Username'] | //XCUIElementTypeTextField[@name='test-Username']")
	WebElement UserName;
	@FindBy(how =How.XPATH, using = "//XCUIElementTypeSecureTextField[@name='test-Password'] | //XCUIElementTypeSecureTextField[@name='test-Password']")
	WebElement Password;
	@FindBy(how =How.XPATH, using = "//XCUIElementTypeOther[@name='test-LOGIN'] | //XCUIElementTypeOther[@name='test-LOGIN']")
	WebElement Login;

	public MobileLogin(){
		this.driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
		PageFactory.initElements(this.driver, this);
	}

	public void login() throws Exception {
		clickElement(UserName, "User Name", pageName);
		type(UserName, "standard_user", "User Name", pageName);
		clickElement(Password, "Password", pageName);
		type(Password, "secret_sauce", "Password", pageName);
		clickElement(Login, "Login", pageName);
	}

}