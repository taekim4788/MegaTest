package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class FilePage_PF {
	@FindBy(className = "CodeMirror")
	WebElement codeMirror;

//	@FindBy(className = "CodeMirror-line")
//	WebElement codeMirrorLine;
//	
//	@FindBy(css = "textarea")
//	WebElement textArea;

	@FindBy(xpath = "//button[@title='Save changes']")
	WebElement btn_save;

	@FindBy(xpath = "//button[@class='close-btn']")
	WebElement btn_close;

	WebDriver driver;

	public FilePage_PF(WebDriver driver) {
		this.driver = driver;
		
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
		PageFactory.initElements(factory, this);
	}

	public void enterFileContent() {
		WebElement codeLine = codeMirror.findElements(By.className("CodeMirror-line")).get(0);
		codeLine.click();
		WebElement txtbx = codeMirror.findElement(By.cssSelector("textarea"));
		txtbx.sendKeys("megatesting");
	}

	public void clickSave() {
		btn_save.click();
	}

	public void clickClose() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", btn_close);
	}
}
