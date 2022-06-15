package StepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.MainPage_PF;
import pageFactory.BinPage_PF;
import pageFactory.FilePage_PF;
import pageFactory.InitialPage_PF;
import pageFactory.LoginPage_PF;

public class MegaTestSteps {
	WebDriver driver = null;
	String projectPath;
	
	@Before
	public void browserSetup() {
		projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		driver.manage().window().maximize();
	}

	@After
	public void teardown() {
		System.out.println("Driver tear down");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.close();
		driver.quit();
	}

	InitialPage_PF initial;
	LoginPage_PF login;
	MainPage_PF main;
	FilePage_PF file;
	BinPage_PF bin;
	
	Properties pro;

	// Background - login to Mega
	@Given("user is on MegaWebsite")
	public void user_is_on_mega_website() throws IOException {
		File src = new File(projectPath + "/src/test/resources/config_dir/config.property");
		FileInputStream fis = new FileInputStream(src);
		pro = new Properties();
		pro.load(fis);
		driver.navigate().to(pro.getProperty("WEB_URL"));
	}

	@And("enters login page")
	public void enters_login_page() {
		initial = new InitialPage_PF(driver);

		initial.clickLogin();
	}

	@When("user enters username and password")
	public void user_enters_username_and_password() {
		login = new LoginPage_PF(driver);

		login.enterUsername(pro.getProperty("USERNAME"));
		login.enterPassword(pro.getProperty("PASSWORD"));
	}

	@And("click login button")
	public void clicks_on_login_button() {
		login.clickLogin();
	}

	@Then("user is logged into Mega")
	public void user_is_logged_into_mega() {
		main = new MainPage_PF(driver);

		main.checkCloudDriveIsDisplayed();
	}

	// Scenario - upload test file
	@When("user right clicks on page")
	public void user_right_clicks_on_page() {
		main.rightClickOnPage();
	}

	@And("click New text file button")
	public void click_new_text_file_button() {
		main.clickNewTextFile();
	}

	@And("enter text file name")
	public void enter_text_file_name() {
		main.enterFileName();
	}

	@And("click Create")
	public void click_create() {
		main.clickCreate();
	}

	@And("Enter file content")
	public void enter_file_content() throws InterruptedException {
		file = new FilePage_PF(driver);
		file.enterFileContent();
	}

	@And("click Save")
	public void click_save() {
		file.clickSave();
	}

	@And("click close button")
	public void click_close_button() throws InterruptedException {
		Thread.sleep(5000);
		file.clickClose();
	}

	@Then("file is saved on Mega drive")
	public void file_is_saved_on_mega_drive() {
		main.checkTestFileIsDisplayed();
	}

	// Scenario - remove test file
	@When("user right clicks on test file")
	public void user_right_clicks_on_test_file() {
		main.rightClickOnFile();
	}

	@And("click Remove button")
	public void click_remove_button() {
		main.clickRemove();
	}

	@Then("file is removed on Mega drive")
	public void file_is_removed_on_mega_drive() throws InterruptedException {
		Thread.sleep(1000);
		try {
			driver.findElement(By.xpath("//span[text()='a.txt']"));
			System.out.println("Test failed: file not deleted");
		} catch (NoSuchElementException ex) {
			/* do nothing, file is deleted, assert is passed */
		}
	}

	// Scenario - restore test file
	@When("user navigates to Rubbish bin page")
	public void user_navigates_to_rubbish_bin_page() {
		bin = new BinPage_PF(driver);

		bin.clickRubbishBin();
	}

	@And("select testfile")
	public void select_testfile() throws InterruptedException {
		Thread.sleep(3000);
		bin.selectDeletedFile();
	}

	@And("click Restore button")
	public void click_restore_button() {
		bin.clickRestore();
	}

	@Then("file is restored on Cloud drive")
	public void file_is_restored_on_cloud_drive() {
		main.checkTestFileIsDisplayed();
	}
}
