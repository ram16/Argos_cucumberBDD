package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id='consent_prompt_submit']")
	WebElement elementToClick;

	@FindBy(id = "searchTerm")
	WebElement searchBox;

	@FindBy(xpath = "//span[@class='_1gqeQ']")
	WebElement searchButton;

	public String searchForProduct(String product) {

		searchBox.sendKeys(product);
		searchButton.click();

		return product;

	}

	public void acceptAllButton() {
		elementToClick.click();

	}

}
