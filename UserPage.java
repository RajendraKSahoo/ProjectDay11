package com.ibm.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.ibm.utilities.PropertiesFileHandler;

public class UserPage 
{
	WebDriver driver;
	WebDriverWait wait;
	
	public UserPage(WebDriver driver,WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver,this);
	}


	public void launchUserPage () throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String userurl = data.get("userurl");
								
		//WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(userurl);
		
	}
	
	public void verifytab() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String userurl = data.get("userurl");
				
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(userurl);
		
		Properties p = new Properties();
		p.load(new FileInputStream("./TestData/magentodata.properties"));
		
		String title = driver.getTitle();
		Assert.assertEquals("AtoZ Grocery | Wholesaler in Bangalore", title);
		
		String pagesource = driver.getPageSource();
		if(pagesource.contains(p.getProperty("tabname")))
		{
			System.out.println("The presence of tab name is confirmed on User page!");
			Assert.assertTrue(true);
		}
		else {
			System.out.println("The tab name doesn't exist on User page");
			Assert.fail();
		}
		
				
	}
	
	//WebElement for link SignUp
		@FindBy(xpath="//a[contains(text(),'SignUp')]")
		WebElement signupEle;
	//WebElement for field Full Name
		@FindBy(xpath="//input[@id='name']")
		WebElement nameEle;
	//WebElement for field Phone Number
		@FindBy(xpath="//input[@id='pnum']")
		WebElement pnumEle;
	//WebElement for field Password
		@FindBy(xpath="//input[@id='password']")
		WebElement pwdEle;
	//WebElement for field Confirm Password
		@FindBy(xpath="//input[@id='cpassword']")
		WebElement cpwdEle;
	//WebElement for checkbox Term & Conditions
		@FindBy(xpath="//input[@id='tccheckbox']")
		WebElement tccEle;
	//WebElement for button SignUp
		@FindBy(xpath="//button[@id='mem_signup']")
		WebElement bsignupEle;
	//WebElement for link My Account
		@FindBy(xpath="(//i[@class='flaticon-user-outline'])[1]")
		WebElement myacctEle;
	//WebElement for link Logout
		@FindBy(xpath="//a[contains(text(),'Log Out')]")
		WebElement logoutEle;
		
	//WebElement for link Login
		@FindBy(xpath="//a[contains(text(),'Login')]")
		WebElement loginlinkEle;
	//WebElement for field Phone Number
		@FindBy(xpath="//input[@id='pnum2']")
		WebElement mobileEle;
	//WebElement for field Password
		@FindBy(xpath="//input[@id='pword2']")
		WebElement passwordEle;
	//WebElement for button Login
		@FindBy(xpath="//button[@id='mem_login']")
		WebElement loginbuttonEle;
		
	//WebElement for field 1st Search
		@FindBy(xpath="//input[@id='search-box']")
		WebElement searchEle;
		
	//WebElement for icon Add to Cart
		@FindBy(xpath="//a[contains(text(),'Add To Cart')]")
		WebElement addcartEle;
	//WebElement for icon Cart
		@FindBy(xpath="//p[@id='bigCart']")
		WebElement cartEle;
	//WebElement for icon Checkout
		@FindBy(xpath="//a[contains(text(),'Check Out')]")
		WebElement checkoutEle;
	//WebElement for icon Continue to Payment
		/*@FindBy(xpath="(//a[@class='btn orange'])[1]")
		WebElement ctopaymentEle;*/
	//WebElement for icon Confirm Orders
		@FindBy(xpath="//a[@id='confirm-order-id']")
		WebElement corderEle;
	//WebElement for icon My Order
		@FindBy(xpath="(//a[contains(text(),'My Orders')])[2]")
		WebElement myorderEle;
	//WebElement for Customer name on My Order
		@FindBy(xpath="//*[@class='table table-bordered table-hover']/tbody/tr/td[2]")
		WebElement custnameEle;
		
		public void clickOnLinkSignUp()
		{
			signupEle.click();
		}
		public void enterFullName(String fullname)
		{
			nameEle.sendKeys(fullname);
		}
		public void enterPhoneNo(String pno)
		{
			pnumEle.sendKeys(pno);
		}
		public void enterPassword(String cpassword)
		{
			pwdEle.sendKeys(cpassword);
		}
		public void enterConfirmPassword(String confpassword)
		{
			cpwdEle.sendKeys(confpassword);
		}
		public void clickOnTermCondition()
		{
			tccEle.click();
		}
		public void clickOnButtonSignUp()
		{
			bsignupEle.click();
		}
		public void clickOnAlertBox()
		{
			Alert alertBox = driver.switchTo().alert();
			String alertmsg = alertBox.getText();
			System.out.println(alertmsg);
			alertBox.accept();
		}
		public void clickOnLogout()
		{
			myacctEle.click();
			logoutEle.click();
		}
		
		public void clickOnLinkLogin()
		{
			loginlinkEle.click();
		}
		public void enterMobileNo(String cpno)
		{
			mobileEle.sendKeys(cpno);
		}
		public void enterUserPassword(String cpassword)
		{
			passwordEle.sendKeys(cpassword);
		}
		public void clickOnButtonLogin()
		{
			loginbuttonEle.click();
		}
		public void performSearch() throws InterruptedException
		{
			//driver.findElement(By.xpath("(//*[@placeholder='Search for products...'])[1]")).click();
			searchEle.click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//*[@placeholder='Search for products...'])[2]")).sendKeys("rice");
			Thread.sleep(3000);
			List<WebElement> searchProductLinks= driver.findElements(By.xpath("//*[@id='searchproducts-div']/a"));
			int linkCount=searchProductLinks.size();
			System.out.println(linkCount);
			Reporter.log("Links available"+ linkCount);
			if(linkCount != 0)
			{
				System.out.println("Product Available");
				Reporter.log("Product Available");
			}

			WebElement orangeRiceEle=null;
			for(WebElement link : searchProductLinks)
			{
				System.out.println(link.getText());
				if(link.getText().toLowerCase().contains("orange"))
				{
					orangeRiceEle=link;
				}
			}
			Thread.sleep(5000);
			orangeRiceEle.click();
		}
		public void clickOnAddToCart()
		{
			addcartEle.click();
		}
		public void clickOnCart()
		{
			cartEle.click();
		}
		public void clickOnCheckout()
		{
			checkoutEle.click();
		}
		public void clickOnContinuePayment()
		{
			driver.findElement(By.xpath("//input[@id='pincode']")).click();
			driver.findElement(By.xpath("(//a[@class='btn orange'])[1]")).click();
		}
		public void clickOnConfirmOrder()
		{
			driver.findElement(By.xpath("//input[@id='tc']")).click();
			corderEle.click();
		}
		public void clickOnMyOrder()
		{
			myorderEle.click();
		}
		public void validationOnOrder() throws FileNotFoundException, IOException
		{
			Properties p = new Properties();
			p.load(new FileInputStream("./TestData/magentodata.properties"));
			
			String customername = custnameEle.getText();
			if(customername.equalsIgnoreCase(p.getProperty("fullname"))) {
				System.out.println("The placed order is available on the list!");
				Assert.assertTrue(true);
			}
			else {
				System.out.println("The placed order is not available on the list!");
				Assert.fail();
			}
		}
		
}
