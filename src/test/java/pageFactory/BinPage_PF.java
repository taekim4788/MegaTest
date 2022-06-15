package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BinPage_PF {
	@FindBy(xpath = "//button[contains(@class, 'rubbish-bin')]")
	WebElement btn_rubbishBin;

	@FindBy(xpath = "//span[text()='a.txt']")
	WebElement deletedFile;

	@FindBy(className = "grid-url-arrow")
	WebElement btn_more;

	@FindBy(xpath = "//span[text()='Restore']")
	WebElement btn_restore;

	WebDriver driver;

	public BinPage_PF(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void clickRubbishBin() {
		btn_rubbishBin.click();
	}

	public void selectDeletedFile() {
		deletedFile.click();
	}

	public void clickRestore() {
		btn_more.click();
		btn_restore.click();
	}
}
