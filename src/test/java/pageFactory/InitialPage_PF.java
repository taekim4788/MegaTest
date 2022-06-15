package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InitialPage_PF {
	@FindBy(className="top-login-button")
	WebElement btn_login;
	
	WebDriver driver;
	
	public InitialPage_PF(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickLogin() {
		btn_login.click();	
	}
}
