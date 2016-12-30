package common;

import java.net.MalformedURLException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;


public class CommonFunctions {
	//public WebDriver driver = new ChromeDriver();
	 protected DesiredCapabilities capability;
	 public RemoteWebDriver driver;
	
	 public void openBrowser(String browserName) throws InterruptedException, MalformedURLException{
		 
		 try {
				if (browserName.equalsIgnoreCase("firefox")) {
					capability = DesiredCapabilities.firefox();
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
				    driver.manage().deleteAllCookies();
				    driver.get("http://127.0.0.1:8080");
				    driver.manage().window().maximize();
				    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				} else if (browserName.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver","C:\\Windows\\chromedriver.exe");
					//driver = new ChromeDriver();
					capability = DesiredCapabilities.chrome();
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
				    driver.manage().deleteAllCookies();
				    driver.get("http://127.0.0.1:8080");
				    driver.manage().window().maximize();
				    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				} else if (browserName.equalsIgnoreCase("MicrosoftEdge")) {
					//System.setProperty("webdriver.ie.driver","C:\\Windows\\IEDriverServer.exe");
					System.setProperty("webdriver.edge.driver","C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe");
					//driver = new InternetExplorerDriver();
					capability = DesiredCapabilities.edge();
					Map<String, Object> edgeOptions = new HashMap<String, Object>();
					capability.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
				    driver.manage().deleteAllCookies();
				    driver.get("http://127.0.0.1:8080");
				    driver.manage().window().maximize();
				    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				}
			
			} catch (WebDriverException e) {
				System.out.println(e.getMessage());
			}
		}
//		 /
		  

	
	public void login() throws InterruptedException, MalformedURLException{
		  driver.findElement(By.linkText("login")).click();
		  driver.findElement(By.cssSelector("#username")).sendKeys("admin");
		  driver.findElement(By.cssSelector("#password")).sendKeys("admin");
		  driver.findElement(By.cssSelector(".btn.btn-primary.ng-scope")).click();
		  Thread.sleep(10000);
	}
	
	public void clickRegisterNewAccount(){
		driver.findElement(By.linkText("Register a new account")).click();
		driver.findElement(By.cssSelector(".form-control[name=\"login\"]")).sendKeys("ravindraten");
		driver.findElement(By.cssSelector(".form-control[name=\"email\"]")).sendKeys("ravindraten@gmail.com");
		driver.findElement(By.cssSelector(".form-control[name=\"password\"]")).sendKeys("DialMe@10");
		driver.findElement(By.cssSelector(".form-control[name=\"confirmPassword\"]")).sendKeys("DialMe@10");
		driver.findElement(By.cssSelector(".btn.btn-primary.ng-scope")).click();
	}
	
	public void clickEntitiesButton(){
		WebElement entitiesButton =driver.findElement(By.cssSelector(".dropdown-toggle span[translate=\"global.menu.entities.main\"]"));
		  entitiesButton.click();
	}
	
	public void clickAccountsButton(){
	WebElement accountButton =driver.findElement(By.cssSelector(".dropdown-toggle span[translate=\"global.menu.account.main\"]"));
	  accountButton.click();
	}
	
	public void clickCreateBranch(){
		WebElement create = driver.findElement(By.cssSelector(".btn.btn-primary [translate=\"gurukulaApp.branch.home.createLabel\"]"));
		create.click();
	}
	
	public void clickCreateStaff(){
		WebElement create = driver.findElement(By.cssSelector(".btn.btn-primary [translate=\"gurukulaApp.staff.home.createLabel\"]"));
		create.click();
	}
	
	public void clickBranchLink(){
		WebElement branch = driver.findElement(By.cssSelector(".open .dropdown-menu li [translate=\"global.menu.entities.branch\"]"));
		branch.click();
	}
	
	public void clickStaffLink(){
		WebElement branch = driver.findElement(By.cssSelector(".open .dropdown-menu li [translate=\"global.menu.entities.staff\"]"));
		branch.click();
	}
	public void clickSearch(String searchVal){
		WebElement searchBox = driver.findElement(By.cssSelector("#searchQuery"));
		searchBox.clear();
		searchBox.sendKeys(searchVal);
		WebElement searchButton = driver.findElement(By.cssSelector(".btn.btn-info .glyphicon-search"));
		searchButton.click();
	}
	
	public void deleteBranchOrStaff(){
		WebElement delete = driver.findElement(By.cssSelector(".btn.btn-danger.btn-sm"));
		delete.click();
	}
	
	public void deleteItem(){
		WebElement deleteButton = driver.findElement(By.cssSelector(".modal-dialog .btn.btn-danger"));
		deleteButton.click();
	}
	public void logout(){
		driver.findElement(By.cssSelector(".hipster.img-responsive.img-rounded")).click();
		WebElement logout =driver.findElement(By.cssSelector(".ng-scope [translate=\"global.menu.account.logout\"]"));
		clickAccountsButton();
		logout.click();
	}
}
