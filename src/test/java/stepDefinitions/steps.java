package stepDefinitions;

import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import pageObjects.ProductItemPage;
import pageObjects.SearchResultPage;

public class steps {

	WebDriver driver;
	HomePage homepage;
	ProductItemPage productitempage;
	SearchResultPage searchresultpage;
	Logger logger;// for logging
	ResourceBundle rb; // for reading properties file
	String br;

	@Before
	public void setup() {
		logger = LogManager.getLogger(this.getClass()); // for logging

		rb = ResourceBundle.getBundle("config");
		br = rb.getString("browser");
	}

	@After
	public void tearDown() {
		try {
			if (driver != null) {
				driver.quit();
				logger.info("Browser closed successfully.");
			}
		} catch (Exception e) {
			logger.error("Error occurred while closing the browser: " + e.getMessage());
		}
	}

	@Given("User Launch browser")
	public void user_launch_browser() {

		if (br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (br.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("opens URL {string}")
	public void opens_url(String url) {
		driver.get(url);
		logger.info("Home Page Displayed");
		driver.manage().window().maximize();
	}

	@When("the user searches for a product")
	public void the_user_searches_for_a_product() {
		homepage = new HomePage(driver);
		homepage.acceptAllButton();
		homepage.searchForProduct("tree");

	}

	@Then("the search result page should return the item")
	public void the_search_result_page_should_return_the_item() {
		searchresultpage = new SearchResultPage(driver);
		logger.info("Search Result Page Displayed ");
		searchresultpage.verifySearchResult("tree");
		logger.info("Search Result Page Displayed with expected products ");
		searchresultpage.clickOnSelectedProduct("Argos Home 3ft Christmas Tree");

	}

	@When("the user adds the product to the basket")
	public void the_user_adds_the_product_to_the_basket() {
		productitempage = new ProductItemPage(driver);
		productitempage.verifyProductNameInTrolley("Argos Home 3ft Christmas Tree");
		logger.info("Product is availble in search results page");
		productitempage.addToTrolley();
		productitempage.goToTrolley();
		logger.info("Product added to trolley");
		productitempage.clickOnBasket();
	}

	@Then("the product should be in the basket")
	public void the_product_should_be_in_the_basket() {
		productitempage.verifyProductNameInTrolley("Argos Home 3ft Christmas Tree");
		logger.info("Trolley contains selected product ");
		productitempage.verifyBasketQuantity(1);
		logger.info("Products quantity in basket verified");
	}

	@When("the user increases the quantity of the product by {int}")
	public void the_user_increases_the_quantity_of_the_product_by(Integer int1) {
		productitempage.increaseQuantity();
		logger.info("Increase the product quantity");

	}

	@Then("the products should reflect the increased quantity")
	public void the_products_should_reflect_the_increased_quantity() {
		productitempage.verifyBasketQuantity(2);
		logger.info("Product quantity also increased in basket after increment");

	}

}
