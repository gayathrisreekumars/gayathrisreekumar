package in.amazon;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonTest {

	@Test
	public void loginTest() {

		// Create Driver

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver(); // Creating driver variable

		// Maximize Browser Window

		driver.manage().window().maximize();
		sleep(1000);

		// Open Amazon Login Page

		String url = "https://www.amazon.in/";
		driver.get(url);
		sleep(2000);

		// Click on Sign In Button
		WebElement signIn = driver.findElement(By.xpath("//a[@id='nav-link-accountList']/span"));
		signIn.click();
		sleep(1000);

//		Enter Email

		WebElement email = driver.findElement(By.id("ap_email"));
		email.sendKeys("testamazon509@gmail.com");
		sleep(1000);

//		Click on Continue Button

		WebElement continueButton = driver.findElement(By.id("continue"));
		continueButton.click();
		sleep(1000);

//		Enter Password

		WebElement password = driver.findElement(By.id("ap_password"));
		password.sendKeys("amazon@123");

//		Click on Sign-In button

		WebElement signInButton = driver.findElement(By.id("signInSubmit"));
		signInButton.click();
		sleep(1000);

//		Verify User Name

		WebElement userNameMessage = driver.findElement(By.xpath("/html//span[@id='nav-link-accountList-nav-line-1']"));
		String expectedUserName = "Hello, Test";
		String actualUserName = userNameMessage.getText();
		Assert.assertEquals(expectedUserName, actualUserName, "User Name is differnt from expected");

		// Search for an Item, say pampers

		WebElement searchTextbox = driver.findElement(By.id("twotabsearchtextbox"));
		searchTextbox.sendKeys("pampers");

		// Click on Search Button

		WebElement searchicon = driver.findElement(By.id("nav-search-submit-button"));
		searchicon.click();
		sleep(1000);

		// Click on an Item

		WebElement item = driver.findElement(By.linkText(
				"Pampers All round Protection Pants, Medium size baby diapers (M) 200 Count, Lotion with Aloe Vera"));
		item.click();
		sleep(2000);

		// As the item opens in new tab, for selenium to locate objects in new tab

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);

		// To Add More Quantity

		WebElement quantity = driver.findElement(By.id("quantity"));

		Select objSelect = new Select(quantity);
		objSelect.selectByValue("2");

		// Click on Add to cart button

		WebElement addToCartButton = driver.findElement(By.cssSelector("input#add-to-cart-button"));
		addToCartButton.click();
		sleep(1000);

		// Click on Cart icon

		WebElement cartIcon = driver.findElement(By.xpath("/html//span[@id='nav-cart-count']"));
		cartIcon.click();
		sleep(1000);

		// Click on Proceed to buy Button

		WebElement proceedToBuyButton = driver.findElement(By.name("proceedToRetailCheckout"));
		proceedToBuyButton.click();
		sleep(1000);

		// Click on Deliver To This Address Button

		WebElement deliverAdressButton = driver.findElement(
				By.cssSelector("[id='address-book-entry-0'] div:nth-of-type(2) .a-declarative.a-button-text"));
		deliverAdressButton.click();
		sleep(1000);

		// Close Browser
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
