package stepDefinition;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;


//@RunWith(Cucumber.class)
public class StepDefinition {
	//Defining global variables those can be accessible to all methods  
	WebDriver driver;
	WebElement Teddy_Bear;
	String Teddy_Bear_name=" ";
	
	@Given("^User select the \"([^\"]*)\" Browser$")
	public void user_select_the_Browser(String browserName) throws Throwable {
		
	    // Script checks the browsers and according to that it will select the browser driver
		if (browserName.equalsIgnoreCase("chrome")) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\Eclipse\\Eclipse_workspace\\Automation_Amazon_Shopping\\Driver\\chromedriver.exe");
		} else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Softwares\\Eclipse\\Eclipse_workspace\\Automation_Amazon_Shopping\\Driver\\chromedriver.exe");
		}else if(browserName.equalsIgnoreCase("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver", "D:\\Softwares\\Eclipse\\Eclipse_workspace\\Automation_Amazon_Shopping\\Driver\\chromedriver.exe");
		}
		
		driver = new ChromeDriver();
		//Maximize the browser window
		driver.manage().window().maximize();
	    
	}

	@Given("^User navigate to \"([^\"]*)\" URL$")
	public void user_navigate_to_URL(String url) throws Throwable {
	    // Opening the amazon.com
		driver.get(url);
	    
	}

	@When("^User logins into amazon application with username \"([^\"]*)\" and \"([^\"]*)\" password$")
	public void user_logins_into_amazon_application_with_username_and_password(String user, String pwd) throws Throwable {
	    // Script puts the userid and password, then clicks on the  
		driver.findElement(By.xpath("xpath for user field")).sendKeys(user);
		driver.findElement(By.xpath("xpath for password field")).sendKeys(pwd);
		driver.findElement(By.xpath("xpath for sign-in button")).submit();

	}

	
	@Then("^Users name \"([^\"]*)\" is displayed in the account panel$")
	public void users_name_is_displayed_in_the_account_panel(String userName) throws Throwable {
	    
		// Checking the user's name on the top right corner
		if (userName.equalsIgnoreCase(driver.findElement(By.xpath("xpath of the account name panel")).getText())) {
			System.out.println("User logged in successfully.");
		}else {
			System.out.println("User is not logged in.");
		}
		
	}
	

	@When("^User search the item \"([^\"]*)\" in the search area$")
	public void user_search_the_item_in_the_search_area(String searchItem) throws Throwable {
	    
		// searching the item
		driver.findElement(By.xpath("xpath of the search text area")).sendKeys(searchItem);
		driver.findElement(By.xpath("xpath of the search/magnifying glass button")).click();
		
	}
	
	@Then("^All \"([^\"]*)\" item is displayed in the search result$")
	public void all_item_is_displayed_in_the_search_result(String searchItem) throws Throwable {
	    // Checking expected items are successfully displayed in the search result
		String item1= driver.findElement(By.xpath("xpath of any item")).getText();
		//checking whether the item's name text contains expected item's name
		if (item1.toLowerCase().contains(searchItem.toLowerCase())) {  
			  System.out.println(searchItem+" are found successfully.");
		} else {
			System.out.println(searchItem+" are not found.");
		}
	}
	
	
	@When("^User sorts the result by selecting \"([^\"]*)\"$")
	public void user_sorts_the_result_by_selecting(String selectDropDown) throws Throwable {
	    //Creating an object of the Select class
		Select obj = new Select(driver.findElement(By.xpath("xpath of drpdown field")));
		//Selecting the expected value from dropdown list 
		obj.selectByVisibleText(selectDropDown);
	}
	
	@When("^User selects the Age range \"([^\"]*)\" old$")
	public void user_selects_the_Age_range_old(String ageRange) throws Throwable {
	    // Selecting the proper age range
		String web_ageRange = driver.findElement(By.xpath("xpath of the desired age range")).getText();
		if (web_ageRange.equals(ageRange)) {
			driver.findElement(By.xpath("xpath of the desired age range")).click();
		}
		
	}
	
	@Then("^All desired items are displayed in the search result$")
	public void all_desired_items_are_displayed_in_the_search_result() throws Throwable {
	    // Checking whether the age text contains 5 or 6
		String text_age= driver.findElement(By.xpath("xpath of age text")).getText();
		if ((text_age.toLowerCase().contains("5".toLowerCase())) || (text_age.toLowerCase().contains("6".toLowerCase()))) {
			  System.out.println("Search results with proper age range found successfully.");
		} else {
			System.out.println("Search results with proper age range is not found.");
		}
		
		
		WebElement rating = driver.findElement(By.xpath("xpath of the star rating"));
		//Hovering to the rating text using the object of Actions class
		Actions act = new Actions(driver);
		act.moveToElement(rating).build().perform();
		String text_rating= driver.findElement(By.xpath("xpath of the rating text")).getText();
		
		//checking whether the rating contains 4 or 5. Considering this is good rating
		if ((text_rating.toLowerCase().contains("4".toLowerCase())) || (text_rating.toLowerCase().contains("5".toLowerCase()))) {
			System.out.println("Search results with proper customer review found successfully.");
		} else {
			System.out.println("Search results with proper customer review is not found.");
		}
		
		
	}
	
	@When("^User clicks and navigate to the number (\\d+) item$")
	public void user_clicks_and_navigate_to_the_number_item(int index) throws Throwable {
	    // script navigates to the first item from the search result
	    Teddy_Bear = driver.findElement(By.xpath("xpath of the item"+index));
	    //storing the name of the item in the Teddy_Bear_name variable
	    Teddy_Bear_name = Teddy_Bear_name+Teddy_Bear.getText();
	    Teddy_Bear.click();
	}
	
	
	@Then("^User adds the item to the cart$")
	public void user_adds_the_item_to_the_cart() throws Throwable {
	    //script click on the Add To Cart button
		driver.findElement(By.xpath("xpath of addtocart button")).click();
	}
	
	@Then("^User navigate back to the search page$")
	public void user_navigate_back_to_the_search_page() throws Throwable {
	    // script navigates back to the search result page
	    driver.navigate().back();
	}
	
	@When("^User navigates to the cart$")
	public void user_navigates_to_the_cart() throws Throwable {
	    // script navigates to the cart page 
		driver.findElement(By.xpath("xpath of the cart button")).click();
	}
	
	@Then("^User validates the number (\\d+) item in the cart$")
	public void user_validates_the_number_item_in_the_cart(int cartIndex) throws Throwable {
	    // Script stores the name of the items in the variable Cart_Item_Name 
		WebElement Cart_Item = driver.findElement(By.xpath("xpath_cartItem"+cartIndex));
		String Cart_Item_name = Cart_Item.getText();
		
		//script check Cart_Item_name is present in already stored global variable Teddy_Bear_name
		if (Teddy_Bear_name.toLowerCase().contains(Cart_Item_name.toLowerCase())) {
			  System.out.println(cartIndex+" item is found in the cart successfully.");
		} else {
			  System.out.println(cartIndex+" item is not found in the cart.");
		}
		
	}


	
}
