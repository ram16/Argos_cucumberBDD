package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductItemPage {

	WebDriver driver;

	public ProductItemPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyProductNameInTrolley(String actualTitle) {

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		WebElement titleElement = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(("(//span[contains(text(), '" + actualTitle + "')])[2]"))));

		if (titleElement.getText().contains(actualTitle)) {
			return true;
		} else {
			return false;

		}
	}

	public void clickOnBasket() {

		WebDriverWait basket = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement countBasket_Element = basket.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//a[@href='/basket?clickOrigin=header:trolley:trolley']//*[name()='svg']")));

		countBasket_Element.click();

	}

	public void increaseQuantity() {

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//select[@class='ProductCardstyles__QuantitySelect-sc-1g8w3q7-21 fpgWSD']")));

		wait1.until(ExpectedConditions.elementToBeClickable(dropdown));
		Select drpSelect = new Select(dropdown);
		drpSelect.selectByValue("2");

	}

	public boolean verifyBasketQuantity(int noOfItemsDisplayed) {

		WebDriverWait basketCount = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement itemCountElement = basketCount
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='LQbCV']")));

		String countItemText = itemCountElement.getText();

		int itemCount = Integer.parseInt(countItemText);

		if (itemCount == noOfItemsDisplayed) {

			return true;
		}
		return false;

	}

	public void addToTrolley() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement elementToClick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='xs-8--none']")));
		elementToClick.click();
	}

	public void goToTrolley() {

		WebDriverWait addToTrolley = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement goToTrolley = addToTrolley
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Go to trolley']")));
		goToTrolley.click();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}