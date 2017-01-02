package test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.CommonFunctions;


public class Gurukul extends CommonFunctions{
	//public WebDriver driver = new ChromeDriver();
	
  @Parameters({ "browser" })
  @BeforeTest
  public void beforeTest(String browser) throws InterruptedException, MalformedURLException {
	  openBrowser(browser);
	  
  }
 
  @Test
  public void test_registerNewUser() throws InterruptedException{
	  System.out.println("The test_registerNewUser is running");
	  clickRegisterNewAccount();
	  WebElement error = driver.findElement(By.cssSelector(".alert.alert-danger.ng-scope[ translate=\"register.messages.error.fail\"]"));
	  String errormsg = "Registration failed! Please try again later.";
	  Assert.assertTrue(errormsg.equalsIgnoreCase(error.getAttribute("innerText")),"The error message is not displayed");
	  System.out.println("The error message displayed is "+error.getAttribute("innerText"));
	  System.out.println("The test_registerNewUser is ended");
  }
  
  @Test(priority= 1)
  public void test_login() throws MalformedURLException, InterruptedException {
	  System.out.println("The test_login is running");
	  login();
	  WebElement welcomeTitle =driver.findElement(By.cssSelector(" .ng-scope[translate=\"main.title\"]"));
	  String val = welcomeTitle.getAttribute("innerText");
	  Assert.assertEquals(val, "Welcome to Gurukula!");  
	  System.out.println("The test_login is ended");
  }
  
  
  @Test(priority=2)
  public void test_validateMenuBarItems() throws InterruptedException, MalformedURLException {
	  //validate 3 buttons (Home, Entities, Account)
	  System.out.println("The test_validateMenuBarItems is running");
	  WebElement accountButton =driver.findElement(By.cssSelector(".dropdown-toggle span[translate=\"global.menu.account.main\"]"));
	  WebElement homeButton = driver.findElement(By.cssSelector(".ng-scope [translate=\"global.menu.home\"]"));
	  WebElement entitiesButton =driver.findElement(By.cssSelector(".dropdown-toggle span[translate=\"global.menu.entities.main\"]"));
	  Assert.assertTrue(homeButton.isDisplayed(),"The Home button is not shown"); 
	  Assert.assertTrue(entitiesButton.isDisplayed(),"The Entities button is not shown");
	  Assert.assertTrue(accountButton.isDisplayed(),"The Account button is not shown");
	  System.out.println("The test_validateMenuBarItems is running");
  }
  
  @Test(priority=3)
  public void test_validateEntitiesDropdownItems() throws InterruptedException  {
	  System.out.println("The test_validateEntitiesDropdownItems is running");
	  clickEntitiesButton();
	  WebElement branch = driver.findElement(By.cssSelector(".open .dropdown-menu li [translate=\"global.menu.entities.branch\"]"));
	  WebElement staff = driver.findElement(By.cssSelector(".open .dropdown-menu li [translate=\"global.menu.entities.staff\"]"));
	  String ExpectedStr[] = {"  Branch","  Staff"};
	  Assert.assertTrue(ExpectedStr[0].contains(branch.getAttribute("innerText")));
	  System.out.println("The 1st Item is "+branch.getAttribute("innerText"));
	  Assert.assertTrue(ExpectedStr[1].contains(staff.getAttribute("innerText")));
	  System.out.println("The 2nd Item is "+staff.getAttribute("innerText"));
	  System.out.println("The test_validateEntitiesDropdownItems is running");
  }
  
  @Test(priority=4)
  public void test_validateAccountDropdownItems() throws InterruptedException {
	  System.out.println("The test_validateAccountDropdownItems is running");
	  clickAccountsButton();
	  WebElement settings = driver.findElement(By.cssSelector(".ng-scope [translate=\"global.menu.account.settings\"]"));
	  WebElement password = driver.findElement(By.cssSelector(".ng-scope [translate=\"global.menu.account.password\"]"));
	  WebElement sessions = driver.findElement(By.cssSelector(".ng-scope [translate=\"global.menu.account.sessions\"]"));
	  WebElement logout = driver.findElement(By.cssSelector(".ng-scope [translate=\"global.menu.account.logout\"]"));
	  String ExpectedStr[] = {"  Settings", "  Password", "  Sessions", "  Log out"};
	  
	  Assert.assertTrue(ExpectedStr[0].contains(settings.getAttribute("innerText")));
	  System.out.println("The 1st Item under Account is "+settings.getAttribute("innerText"));
	  Assert.assertTrue(ExpectedStr[1].contains(password.getAttribute("innerText")));
	  System.out.println("The 2nd Item under Account is "+password.getAttribute("innerText"));
	  Assert.assertTrue(ExpectedStr[2].contains(sessions.getAttribute("innerText")));
	  System.out.println("The 3rd Item under Account is "+sessions.getAttribute("innerText"));
	  Assert.assertTrue(ExpectedStr[3].contains(logout.getAttribute("innerText")));
	  System.out.println("The 4th Item under Account is "+logout.getAttribute("innerText"));  
	  System.out.println("The test_validateAccountDropdownItems is ended");
  }
  
  
  @Test(dataProvider="Branch",priority=5)
  public void test_createBranches(String BranchName,String BranchCode) throws InterruptedException{
	  System.out.println("The test_createBranches is running");
	  clickEntitiesButton();
	  clickBranchLink();
	  clickCreateBranch();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='saveBranchModal'] //input[@name='name']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='saveBranchModal'] //input[@name='name']")).sendKeys(BranchName);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='saveBranchModal'] //input[@name='code']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='saveBranchModal'] //input[@name='code']")).sendKeys(BranchCode);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='saveBranchModal']//button [@type='submit']")).click();
	  Thread.sleep(1000);
	  
	  Assert.assertTrue((driver.findElement(By.cssSelector("tbody")).getAttribute("innerText")).contains(BranchName),"The Branch is not created");
	  System.out.println("The test_createBranches is ended");

  }
  
  @DataProvider(name="Branch") 
  public Object[][] getDataFromDataprovider(){

      return new Object[][] {

              { "BrancOne", "BB1" },
              { "BranchTwo", "BB2" },
              { "BranchThree", "BB3" },
              { "BranchFour", "BB4" }

          };

  }
  

@Test(dataProvider="Staff",priority=6)
	public void test_createStaff(String StaffName ,String BranchName) throws InterruptedException {
	System.out.println("The test_createStaff is running");
	  clickEntitiesButton();
	  clickStaffLink();
	  clickCreateStaff();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath(".//*[@id='saveStaffModal'] //input[@name='name']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath(".//*[@id='saveStaffModal'] //input[@name='name']")).sendKeys(StaffName);
	  Thread.sleep(1000);
	  WebElement mySelectElement = driver.findElement(By.xpath(".//*[@id='saveStaffModal'] //select")); //[@name='related_branch']
	  
	  mySelectElement.click();
	  mySelectElement.sendKeys(Keys.ARROW_DOWN);
	  mySelectElement.sendKeys(Keys.ENTER);
	  Thread.sleep(2000);
	  
	  driver.findElement(By.xpath(".//*[@id='saveStaffModal']//button [@type='submit']")).click();
	  Thread.sleep(2000);
	  
	  Assert.assertTrue((driver.findElement(By.cssSelector("tbody")).getAttribute("innerText")).contains(BranchName),"The Branch is not created");
	  System.out.println("The test_createStaff is ended");
}

@DataProvider(name="Staff") 
public Object[][] getDataFromDataprovider1(){

    return new Object[][] {

            { "StaffOne", "BranchOne" }  
        };

}

@Test(priority=7)
public void test_searchStaffAndDelete() throws InterruptedException{
	System.out.println("The test_searchStaffAndDelete is running");
	  clickEntitiesButton();
	  clickStaffLink();
	  clickSearch("StaffOne");
	  Thread.sleep(2000);
	  Assert.assertTrue((driver.findElement(By.cssSelector("tbody")).getAttribute("innerText")).contains("BranchOne"),"The Branch searched is not displayed");  
	  deleteBranchOrStaff();
	  deleteItem();
	  System.out.println("The test_searchStaffAndDelete is running");
}

@Test(dataProvider="StaffAdd",priority=8)
public void test_staffAdd(String StaffName,String BranchCode) throws InterruptedException{
	System.out.println("The test_StaffAdd is running");
	clickEntitiesButton();
	  clickStaffLink(); 
	  clickCreateStaff();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='saveStaffModal'] //input[@name='name']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='saveStaffModal'] //input[@name='name']")).sendKeys(StaffName);
	  Thread.sleep(1000);
	  WebElement mySelectElement = driver.findElement(By.xpath(".//*[@id='saveStaffModal'] //select")); //[@name='related_branch']
	  
	  mySelectElement.click();
	  mySelectElement.sendKeys(Keys.ARROW_DOWN);
	  mySelectElement.sendKeys(Keys.ENTER);
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath(".//*[@id='saveStaffModal']//button [@type='submit']")).click();
	  Thread.sleep(1000);
	  
	  System.out.println("The test_StaffAdd is ended");
}
@DataProvider(name="StaffAdd") 
public Object[][] getDataFromDataprovider2(){

    return new Object[][] {

    	{ "StaffA", "BranchOne" } ,
    	{ "StaffB", "BranchOne" } ,
    	{ "StaffC", "BranchOne" }  ,
    	{ "StaffD", "BranchOne" }  ,
    	{ "StaffE", "BranchOne" }  ,
    	{ "StaffF", "BranchOne" }  ,
    	{ "StaffG", "BranchOne" }  ,
    	{ "StaffH", "BranchOne" }  ,
    	{ "StaffI", "BranchOne" } ,
    	{ "StaffJ", "BranchOne" }  ,
    	{ "StaffK", "BranchOne" }  ,
    	{ "StaffL", "BranchOne" }  ,
    	{ "StaffM", "BranchOne" }  ,
    	{ "StaffN", "BranchOne" }  ,
    	{ "StaffM", "BranchOne" }  ,
    	{ "StaffO", "BranchOne" }  ,
    	{ "StaffP", "BranchOne" }  ,
    	{ "StaffQ", "BranchOne" }  ,
    	{ "StaffR", "BranchOne" }  ,
    	{ "StaffS", "BranchOne" }  ,
    	{ "StaffT", "BranchOne" }  ,
    	{ "StaffU", "BranchOne" }  ,
    	{ "StaffV", "BranchOne" }  ,
    	{ "StaffW", "BranchOne" }  ,
    	{ "StaffX", "BranchOne" }  ,
    	{ "StaffY", "BranchOne" }  ,
    	{ "StaffZ", "BranchOne" }  ,
    	{ "StaffAA", "BranchOne" }  ,
    	{ "StaffBB", "BranchOne" }  ,
    	{ "StaffCC", "BranchOne" }  ,
    	{ "StaffDD", "BranchOne" }  ,
    	{ "StaffEE", "BranchOne" }  ,
    	{ "StaffFF", "BranchOne" }  ,
    	{ "StaffGG", "BranchOne" }  

        };

}

@Test(priority=9)
public void test_pagination() throws InterruptedException{
	System.out.println("The test_pagination is running");
	  clickEntitiesButton();
	  clickStaffLink();
	  Thread.sleep(5000);
	  
	//Pagination First button is visible (<<)
	  String first = driver.findElement(By.cssSelector(".pager>li[ng-click=\"loadPage(links['first'])\"]")).getAttribute("innerText");
	  Assert.assertTrue(first.contains("<<"), "The first button is not displayed");
	//Pagination Next button is visible (>)
	  String next = driver.findElement(By.cssSelector(".pager>li[ng-click=\"loadPage(links['next'])\"]")).getAttribute("innerText");
	  Assert.assertTrue(next.contains(">"), "The next button is not displayed");
	  
	//Pagination last button is visible (>>)
	  String last = driver.findElement(By.cssSelector(".pager>li[ng-click=\"loadPage(links['last'])\"]")).getAttribute("innerText");
	  Assert.assertTrue(last.contains(">>"), "The last button is not displayed");
	  driver.findElement(By.cssSelector(".pager>li[ng-click=\"loadPage(links['last'])\"]")).click();
	  
	  Thread.sleep(5000);
	//Pagination Previous button is visible (<)
	  String prev = driver.findElement(By.cssSelector(".pager>li[ng-click=\"loadPage(links['prev'])\"]")).getAttribute("innerText");
	  Assert.assertTrue(prev.contains("<"), "The first button is not displayed");
	  System.out.println("The test_pagination is ended");
}

@Test(priority=10)
public void test_searchBranchAndDelete() throws InterruptedException{
	System.out.println("The test_searchBranchAndDelete is running");
	  clickEntitiesButton();
	  clickBranchLink();
	  Thread.sleep(2000);
	  clickSearch("BB1");
	  Thread.sleep(2000);
	  Assert.assertTrue((driver.findElement(By.cssSelector("tbody")).getAttribute("innerText")).contains("BB1"),"The Branch searched is not displayed");
	  
	  
	  deleteBranchOrStaff();
	  Thread.sleep(2000);
	  deleteItem();
	  clickSearch("BB2");
	  Thread.sleep(2000);
	  Assert.assertTrue((driver.findElement(By.cssSelector("tbody")).getAttribute("innerText")).contains("BB2"),"The Branch searched is not displayed");
	  deleteBranchOrStaff();
	  Thread.sleep(2000);
	  deleteItem();
	  
	  clickSearch("BB3");
	  Thread.sleep(2000);
	  Assert.assertTrue((driver.findElement(By.cssSelector("tbody")).getAttribute("innerText")).contains("BB3"),"The Branch searched is not displayed");
	  deleteBranchOrStaff();
	  Thread.sleep(2000);
	  deleteItem();
	  
	  clickSearch("*");
	  Thread.sleep(2000);
	  Assert.assertTrue((driver.findElement(By.cssSelector("tbody")).getAttribute("innerText")).contains("BB4"),"The Branch searched is not displayed");
	  deleteBranchOrStaff();
	  Thread.sleep(2000);
	  deleteItem(); 
	  
	  System.out.println("The test_searchBranchAndDelete is running");
	    
}

@Test(priority=11)
public void test_searchAndClickPageButton() throws InterruptedException{
	System.out.println("The test_searchBranchAndClickPageButton is running");
	clickEntitiesButton();
	  clickStaffLink();
	  clickSearch("StaffOne");
	  Thread.sleep(2000);
	  List<WebElement> TRcount = driver.findElements(By.tagName("tr"));
	  Thread.sleep(2000);
	  int val= TRcount.size();
	  System.out.println(val);
	  if(val==2)
		  Assert.assertTrue(true);			  
	  else
		  Assert.assertTrue(false,"There is more than one row dislayed for the search term");
	  
	//Pagination First button is visible (<<)
	  String first = driver.findElement(By.cssSelector(".pager>li[ng-click=\"loadPage(links['first'])\"]")).getAttribute("innerText");
	  Assert.assertFalse(first.contains("<<"), "The first button is displayed");
	//Pagination Next button is visible (>)
	  String next = driver.findElement(By.cssSelector(".pager>li[ng-click=\"loadPage(links['next'])\"]")).getAttribute("innerText");
	  Assert.assertFalse(next.contains(">"), "The next button is displayed");
	  
	//Pagination last button is visible (>>)
	  String last = driver.findElement(By.cssSelector(".pager>li[ng-click=\"loadPage(links['last'])\"]")).getAttribute("innerText");
	  Assert.assertFalse(last.contains(">>"), "The last button is displayed");
		System.out.println("The test_searchBranchAndClickPageButton is ended");
	  
	  
}

@Test(priority=12)
public void test_accountSettings() throws InterruptedException{
	clickAccountsButton();
	settings();
	Thread.sleep(3000);
	String val = driver.findElement(By.cssSelector(".form-control[ng-model=\"settingsAccount.firstName\"]")).getAttribute("value");
	Assert.assertTrue(val.contains("Administrator"), "The value doesnt match the actual value is "+val+" but expected was Administrator");
	
	String val1 = driver.findElement(By.cssSelector(".form-control[ng-model=\"settingsAccount.lastName\"]")).getAttribute("value");
	Assert.assertTrue(val1.contains("Administrator"), "The value doesnt match the actual value is "+val1+" but expected was Administrator");
	
	String val2 = driver.findElement(By.cssSelector(".form-control[ng-model=\"settingsAccount.email\"]")).getAttribute("value");
	Assert.assertTrue(val2.contains("admin@localhost"), "The value doesnt match the actual value is "+val2+" but expected was admin@localhost");
	
}


@Test(priority=13)
public void test_logout(){
	System.out.println("The test_logout is running");
	  logout();
	  Assert.assertTrue(driver.findElement(By.linkText("login")).isDisplayed(),"The login button is not shown");
	  System.out.println("The test_logout is running");
}

  @AfterTest
  public void afterMethod() {
	  driver.close();
	  driver.quit();
  }
}
