package Ajeet.CodeRevieww;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PagesforAutomationAssignment {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.happyfox.com");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("username", "password");

		HomePage homePage = new HomePage(driver);
		homePage.verifyHomePage();

		driver.quit();
	}
	//instead of writing whole code like this it can be divided  into methods
	//BasePage
	static class BasePage {
		protected WebDriver driver;

		public BasePage(WebDriver driver) {
			this.driver = driver;
		}
	}
	//LoginPage
	static class LoginPage extends BasePage {
		public LoginPage(WebDriver driver) {
			super(driver);
		}

		public void login(String username, String password) {

			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("loginButton")).click();
		}

		public void forgotPassword() {
			driver.findElement(By.linkText("Forgot password?")).click();
		}
	}
	//HomePage
	static class HomePage extends BasePage {
		public HomePage(WebDriver driver) {
			super(driver);
		}

		public void verifyHomePage() {
			if (!driver.getCurrentUrl().equals("https://www.happyfox.com/home")) {
				throw new IllegalStateException("Not on the home page");
			}
		}

		public void navigateToProfile() {
			driver.findElement(By.id("profileLink")).click();
		}
		//TablePage
		public class TablePage extends BasePage {

			private By rowLocator = By.xpath("//table[@id='dataTable']/tbody/tr");

			public TablePage(WebDriver driver) {
				super(driver);
			}

			public void retrieveRowTexts() {
				List<WebElement> rows = driver.findElements(rowLocator);

				for (int i = 0; i < rows.size(); i++) {
					WebElement row = rows.get(i);
					String rowText = row.getText();
					System.out.println("Row " + i + " Text: " + rowText);
				}
			}




		}

	}
