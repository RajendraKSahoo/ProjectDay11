package com.ibm.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.ibm.pages.AdminPage;
import com.ibm.pages.BannerPage;
import com.ibm.pages.CouponPage;
import com.ibm.pages.CustomerPage;
import com.ibm.pages.DB;
import com.ibm.pages.LoginPage;
import com.ibm.pages.ProductPage;
import com.ibm.pages.ReturnPage;
import com.ibm.pages.ShippingPage;
import com.ibm.pages.TabPage;
import com.ibm.pages.UserPage;
import com.ibm.utilities.DBUtil;
import com.ibm.utilities.PropertiesFileHandler;


public class BaseTest {
	
//	@Test
	public void Day1Testcase() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
		String expectedTitle = data.get("expectedtitle");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		AdminPage apage=new AdminPage(driver, wait);
		String actualTitle=apage.getCurrentTitle();
				
		if(actualTitle.equals(expectedTitle))
		{
			System.out.println("Test Passed");
			propFileHandler.setKeyAndValue(file, "TestResult", "Test Passed");
		}
		else
		{
			System.out.println("Test failed");
			propFileHandler.setKeyAndValue(file, "TestResult", "Test Failed");
		}
		
		BannerPage bpage=new BannerPage(driver, wait);
		bpage.addBanner();
		
		apage.clickOnLogOut();

		driver.close();
	
	}
	
//	@Test
	public void Day2Testcase() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
		String couponname = data.get("couponname");
		String code = data.get("code");
		String discount = data.get("discount");
		String amount = data.get("amount");
		String startdate = data.get("startdate");
		String enddate = data.get("enddate");
		String usespercoupon = data.get("usespercoupon");
		String usespercustomer = data.get("usespercustomer");
		//String expectedcouponmessage = data.get("expectedcouponmessage");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		AdminPage apage=new AdminPage(driver, wait);
		CouponPage coupon = new CouponPage(driver, wait);
		coupon.clickOnLinkMarketing();
		coupon.clickOnLinkCoupons();
		coupon.clickOnIconAddNew();
		coupon.enterCode(code);
		coupon.clickOnType();
		coupon.enterDiscount(discount);
		coupon.enterAmount(amount);
		coupon.enterStartDate(startdate);
		coupon.enterEndDate(enddate);
		coupon.enterUsesPerCoupon(usespercoupon);
		coupon.enterUsesPerCustomer(usespercustomer);
		coupon.clickOnStatus();
		coupon.enterCouponName(couponname);
		coupon.clickOnIconSave();
		Thread.sleep(2000);
		coupon.validationOnRecord();
		
		apage.clickOnLogOut();

		driver.close();
		
	}
	
//	@Test
	public void Day3Testcase() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
		String productname = data.get("productname");
		String metatitle = data.get("metatitle");
		String model = data.get("model");
		String gst = data.get("gst");
		String price = data.get("price");
		String specialdiscount = data.get("specialdiscount");
		//String discountprice = data.get("discountprice");
		String imagepath = data.get("imagepath");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		AdminPage apage=new AdminPage(driver, wait);
		ProductPage pp = new ProductPage(driver, wait);
		pp.clickLinkCatalog();
		pp.clickOnLinkProducts();
		pp.clickOnIconAddNew();
		pp.enterMetaTitle(metatitle);
		pp.enterProductName(productname);
		pp.clickOnTabData();
		pp.enterModel(model);
		pp.enterGST(gst);
		pp.enterPrice(price);
		pp.enterSplDiscount(specialdiscount);
		//pp.enterDiscountPrice(discountprice);
		pp.clickOnTabLink();
		pp.selectFromTabs();
		pp.selectCategories();
		pp.clickOnTabImage();
		pp.enterImage(imagepath);
		pp.clickOnIconSave();
		Thread.sleep(2000);
		pp.validationOnRecord();
		
		apage.clickOnLogOut();

		driver.close();
		
	}
	
//	@Test
	public void Day4Testcase() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
		String returnactionname = data.get("returnactionname");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		AdminPage apage=new AdminPage(driver, wait);
		ReturnPage rp = new ReturnPage(driver, wait);
		rp.clickOnLinkSystem();
		rp.clickOnLinkReturns();
		Thread.sleep(2000);
		rp.clickOnLinkRetActions();
		rp.clickOnAction();
		rp.clickOnEdit();
		rp.enterReturnActionName(returnactionname);
		rp.clickOnIconSave();
		rp.validationOnRecord();
		
		apage.clickOnLogOut();

		driver.close();
		
	}
	
//	@Test
	public void Day5Testcase() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
		String expectedpincodemessage = data.get("expectedpincodemessage");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		AdminPage apage=new AdminPage(driver, wait);
		ShippingPage sp = new ShippingPage(driver, wait);
		sp.clickOnLinkSystem();
		sp.clickOnLinkShipping();
		sp.clickOnLinkShippingPincode();
		sp.clickOnAction();
		sp.clickOnEdit();
		sp.clearPincode();
		sp.clickOnIconSave();
		sp.getHeaderMessage();
		String actualpincodemessage = sp.validationOnMisingField();
		Assert.assertEquals(expectedpincodemessage, actualpincodemessage);
		
		apage.clickOnLogOut();

		driver.close();
	}
	
//	@Test
	public void Day6Testcase() throws IOException, InterruptedException
	{
		
		/*String file="./TestData/magentodata.properties";
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);*/
		
		/*String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
		String tabname = data.get("tabname");
		String sortorder = data.get("sortorder");*/
		
		//Location of Excel file
		FileInputStream excelfile = new FileInputStream("./TestData/TestData.xlsx");
		//Open Excel file
		XSSFWorkbook book = new XSSFWorkbook(excelfile);
		//1st sheet on workbook - LoginData
		XSSFSheet logindata = book.getSheet("LoginData");
		//Row
		XSSFRow loginrow = logindata.getRow(1);
		//Cell
		XSSFCell ul = loginrow.getCell(0);
		XSSFCell em = loginrow.getCell(1);
		XSSFCell pw = loginrow.getCell(2);
								
		String url = ul.getStringCellValue();
		String email = em.getStringCellValue();
		String password = pw.getStringCellValue();
		
		//2nd sheet on workbook - Tabs
		XSSFSheet tabs = book.getSheet("Tabs");
		//Row
		XSSFRow row = tabs.getRow(1);
		//Cell
		XSSFCell tn = row.getCell(0);
		XSSFCell so = row.getCell(1);
									
		String tabname = tn.getStringCellValue();
		String sortorder = so.getStringCellValue();
						
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		AdminPage apage=new AdminPage(driver, wait);
		TabPage tp = new TabPage(driver, wait);
		tp.clickOnLinkCatalog();
		tp.clickOnLinkTabs();
		tp.clickOnIconAddNew();
		tp.enterTabName(tabname);
		tp.enterSortOrder(sortorder);
		tp.selectStatus();
		tp.clickOnIconSave();
		Thread.sleep(2000);
		tp.validationOnRecord();
		
		UserPage up = new UserPage (driver, wait);
		up.verifytab();
		
		book.close();
		
		apage.clickOnLogOut();

		driver.close();
		System.out.println("End of program!");
		
	}
	
//	@Test
	public void Day7Testcase() throws IOException, InterruptedException
	{
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String userurl = data.get("userurl");
		String fullname = data.get("fullname");
		String pno = data.get("pno");
		String cpassword = data.get("cpassword");
		String confpassword = data.get("confpassword");
		String cpno = data.get("cpno");
		String cemail = data.get("cemail");
		String caddress = data.get("caddress");
		String ccity = data.get("ccity");
		String cpincode = data.get("cpincode");
		String IPaddress = data.get("IPaddress");
				
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(userurl);
		
		AdminPage apage=new AdminPage(driver, wait);
		UserPage up = new UserPage(driver, wait);
		up.clickOnLinkSignUp();
		up.enterFullName(fullname);
		up.enterPhoneNo(pno);
		up.enterPassword(cpassword);
		up.enterConfirmPassword(confpassword);
		up.clickOnTermCondition();
		up.clickOnButtonSignUp();
		Thread.sleep(2000);
		up.clickOnAlertBox();
		up.clickOnLogout();
								
		CustomerPage custp = new CustomerPage(driver, wait);
		apage.launchAdminPage();
		custp.clickOnLinkCustomer();
		Thread.sleep(2000);
		custp.clickOnAction();
		custp.enterPhoneNumber(cpno);
		custp.enterEmail(cemail);
		custp.enterAddress(caddress);
		custp.enterCity(ccity);
		custp.enterPincode(cpincode);
		custp.enterIPAddress(IPaddress);
		custp.clickOnTopArrow();
		Thread.sleep(2000);
		custp.clickOnIconSave();
		custp.validationOnRecord();
		apage.clickOnLogOut();
		
		up.launchUserPage();
		up.clickOnLinkLogin();
		up.enterMobileNo(cpno);
		up.enterUserPassword(cpassword);
		up.clickOnButtonLogin();
		up.clickOnLogout();
		
	}
	
//	@Test
	public void Day8Testcase() throws IOException, InterruptedException
	{
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String userurl = data.get("userurl");
		String cpno = data.get("cpno");
		String cpassword = data.get("cpassword");
						
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(userurl);
		
		UserPage up = new UserPage(driver, wait);
		up.clickOnLinkLogin();
		up.enterMobileNo(cpno);
		up.enterUserPassword(cpassword);
		up.clickOnButtonLogin();
		Thread.sleep(3000);
		up.performSearch();
		up.clickOnAddToCart();
		up.clickOnCart();
		Thread.sleep(2000);
		up.clickOnCheckout();
		up.clickOnContinuePayment();
		Thread.sleep(2000);
		up.clickOnConfirmOrder();
		Thread.sleep(2000);
		up.clickOnMyOrder();
		up.validationOnOrder();
		
	}
	
//	@Test
	public void Day9Testcase() throws IOException, InterruptedException, SQLException
	{
		
		/*String file="./TestData/magentodata.properties";
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);*/
		
		/*String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
		String tabname = data.get("tabname");
		String sortorder = data.get("sortorder");*/
		
		//Location of Excel file
		FileInputStream excelfile = new FileInputStream("./TestData/TestData.xlsx");
		//Open Excel file
		XSSFWorkbook book = new XSSFWorkbook(excelfile);
		//1st sheet on workbook - LoginData
		XSSFSheet logindata = book.getSheet("LoginData");
		//Row
		XSSFRow loginrow = logindata.getRow(1);
		//Cell
		XSSFCell ul = loginrow.getCell(0);
		XSSFCell em = loginrow.getCell(1);
		XSSFCell pw = loginrow.getCell(2);
								
		String url = ul.getStringCellValue();
		String email = em.getStringCellValue();
		String password = pw.getStringCellValue();
		
		//2nd sheet on workbook - Tabs
		XSSFSheet tabs = book.getSheet("Tabs");
		//Row
		XSSFRow row = tabs.getRow(1);
		//Cell
		XSSFCell tn = row.getCell(0);
		XSSFCell so = row.getCell(1);
									
		String tabname = tn.getStringCellValue();
		String sortorder = so.getStringCellValue();
						
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		AdminPage apage=new AdminPage(driver, wait);
		TabPage tp = new TabPage(driver, wait);
		tp.clickOnLinkCatalog();
		tp.clickOnLinkTabs();
		tp.clickOnIconAddNew();
		tp.enterTabName(tabname);
		tp.enterSortOrder(sortorder);
		tp.selectStatus();
		tp.clickOnIconSave();
		Thread.sleep(2000);
		tp.validationOnRecord();
		
		book.close();
		
		DB dbcon = new DB();
		dbcon.dbvalidation_tab();
		
		apage.clickOnLogOut();

		driver.close();
		System.out.println("End of program!");
		
	}
	
//	@Test
	public void Day10Testcase() throws IOException, InterruptedException, SQLException
	{
		DBUtil db = new DBUtil();
		int expectedcount = db.countQuery("SELECT count(customer_id) from as_customer");
		System.out.println("The total count of records in DB for Customers: " +expectedcount);
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String userurl = data.get("userurl");
		String fullname = data.get("fullname");
		String pno = data.get("pno");
		String cpassword = data.get("cpassword");
		String confpassword = data.get("confpassword");
						
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(userurl);
		
		UserPage up = new UserPage(driver, wait);
		up.clickOnLinkSignUp();
		up.enterFullName(fullname);
		up.enterPhoneNo(pno);
		up.enterPassword(cpassword);
		up.enterConfirmPassword(confpassword);
		up.clickOnTermCondition();
		up.clickOnButtonSignUp();
		Thread.sleep(2000);
		up.clickOnAlertBox();
		up.clickOnLogout();
		
		String actualdata = db.singleDataQuery("SELECT name from as_customer where pnum=9810098100");
		//System.out.println(actualdata);
		Assert.assertEquals(actualdata, "Rajendra Sahoo");
				
		int actualcount = db.countQuery("SELECT count(customer_id) from as_customer");
		System.out.println("The total count of records in DB after creating new Customer: " +actualcount);
		Assert.assertEquals(actualcount, expectedcount+1);
	}
	
	@Test
	public void Day11Testcase() throws IOException, InterruptedException, SQLException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
		String tabname = data.get("tabname");
		String sortorder = data.get("sortorder");
								
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
		
		DBUtil db = new DBUtil();
		int expectedcount = db.countQuery("SELECT count(tab_id) from as_tabs");
		System.out.println("The total count of records in DB for Tabs before creating new record: " +expectedcount);
		
		AdminPage apage=new AdminPage(driver, wait);
		TabPage tp = new TabPage(driver, wait);
		tp.clickOnLinkCatalog();
		tp.clickOnLinkTabs();
		tp.clickOnIconAddNew();
		tp.getHeaderMessage();
		tp.clickOnIconSave();
		tp.validationOnMisingField();
		Thread.sleep(2000);
		tp.enterTabName(tabname);
		tp.enterSortOrder(sortorder);
		tp.selectStatus();
		tp.clickOnIconSave();
		Thread.sleep(2000);
		tp.validationOnSuccessMessage();
		tp.validationOnRecord();
		
		String actualname = db.singleDataQuery("SELECT name from as_tabs where sort=1");
		System.out.println("The new tab added on DB as: " +actualname);
		Assert.assertEquals(tabname, actualname);
				
		int actualcount = db.countQuery("SELECT count(tab_id) from as_tabs");
		System.out.println("The total count of records in DB after creating new tab: " +actualcount);
		Assert.assertEquals(expectedcount+1, actualcount);
		
//		apage.clickOnLogOut();
		
		UserPage up = new UserPage (driver, wait);
		up.verifytab();

//		driver.close();
		System.out.println("End of program!");
		
	}
	
	//excel data using dataprovider
	//run and check	
	//sends data in excel one by one
    /*@Test(dataProvider = "data")
    public void testcase2(String user, String pwd){
    		System.out.println(user);
    		System.out.println(pwd);
    }

    @DataProvider(name="data")
    public Object[][] data() throws IOException {
        return ExcelUtil.DataTable("./TestData/TestData.xlsx","LoginData");
    }*/


}
