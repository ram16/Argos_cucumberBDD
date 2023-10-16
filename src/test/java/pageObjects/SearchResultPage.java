package pageObjects;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {

	WebDriver driver;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifySearchResult(String itemsDisplayed) {

		WebElement result = driver.findElement(By.cssSelector(".styles__SearchTerm-sc-1haccah-1.eslAyR"));

		if (result.getText().contains(itemsDisplayed)) {

			return true;
		}
		return false;

	}

	public ProductItemPage clickOnSelectedProduct(String targetItemTitle) {

		List<WebElement> Products = driver
				.findElements(By.xpath("//div[@class=\"ProductCardstyles__Title-h52kot-12 PQnCV\"]"));

		for (WebElement item : Products) {

			WebDriverWait itemTitle = new WebDriverWait(driver, Duration.ofSeconds(15));

			WebElement itemText = itemTitle.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[contains(@class, 'ProductCardstyles__Title-h52kot-12 PQnCV') and text() ='"
							+ targetItemTitle + "']")));
			itemText.click();

			return new ProductItemPage(driver);

		}
		return null;
	}

}