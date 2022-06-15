package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage_PF {
	@FindBy(xpath = "//button[text() = \" Cloud drive \"]")
	WebElement btn_cloudDrive;

	@FindBy(className = "megaList-content")
	WebElement page;

	@FindBy(xpath = "//span[text()='New text file']")
	WebElement btn_newTextFile;

	@FindBy(name = "dialog-new-file")
	WebElement fileName;

	@FindBy(className = "create-file")
	WebElement btn_create;

	@FindBy(xpath = "//span[text()='a.txt']")
	WebElement createdFile;

	@FindBy(xpath = "//a[contains(@class, 'remove-item')]/span")
	WebElement btn_remove;

	@FindBy(xpath = "//span[text()='Yes']")
	WebElement btn_yes;

	WebDriver driver;

	public MainPage_PF(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void checkCloudDriveIsDisplayed() {
		btn_cloudDrive.isDisplayed();
	}

	public void rightClickOnPage() {
		Actions actions = new Actions(driver);
		WebElement elementLocator = page;
		actions.contextClick(elementLocator).perform();
	}

	public void clickNewTextFile() {
		btn_newTextFile.click();
	}

	public void enterFileName() {
		fileName.sendKeys("a");
	}

	public void clickCreate() {
		btn_create.click();
	}

	public void checkTestFileIsDisplayed() {
		createdFile.isDisplayed();
	}

	public void rightClickOnFile() {
		Actions actions = new Actions(driver);
		WebElement elementLocator = createdFile;
		actions.contextClick(elementLocator).perform();
	}

	public void clickRemove() {
		btn_remove.click();
	}

	public void clickYes() {
		btn_yes.click();
	}
}
