package web.staurant.testPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import sample.basepage.BasePage;
import sample.testUtils.ExtentTestManager;
import web.staurant.store.pages.HomePageProductSearch;

public class HomePageProductSearchTest extends BasePage{
	private HomePageProductSearch HomePage_obj;
	private Logger log = LogManager.getLogger(HomePageProductSearchTest.class.getName());


	public HomePageProductSearchTest(){
		super();
	}
	@Parameters({"browser"})
	@BeforeClass
	public void setUp(String browser){
		initialition(browser);
		HomePage_obj = new HomePageProductSearch();
	}
	
	@Test(priority = 1)
	@Parameters({"browser"})
	public void countWordInProductList(String browser) {
		ExtentTestManager.startTest("Test: "+new Object(){}.getClass().getEnclosingMethod().getName(),"Browser: "+browser);
		boolean wordCount = HomePage_obj.countWordInProduct("stainless work table", "Table");
		Assert.assertEquals(wordCount, true);
	}

	//@Test(priority = 2)
	//@Parameters({"browser"})
	public void addToCartLastItems(String browser) {
		ExtentTestManager.startTest("Test: "+new Object(){}.getClass().getEnclosingMethod().getName(),"Browser: "+browser);
		try {
			boolean isIncart = HomePage_obj.addToCartLastItems();
			Assert.assertEquals(isIncart, true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	//@Test(priority = 3)
	//@Parameters({"browser"})
	public void isCartempty(String browser) {
		ExtentTestManager.startTest("Test: "+new Object(){}.getClass().getEnclosingMethod().getName(),"Browser: "+browser);
		try {
			boolean iscartempty = HomePage_obj.emptyCart();
			Assert.assertEquals(iscartempty, true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	private void tearDown() {
		driver.close();
	}

}
