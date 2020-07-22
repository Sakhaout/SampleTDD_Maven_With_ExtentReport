package web.staurant.store.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import sample.basepage.BasePage;
import web.staurant.store.webElements.HomePageProductSearch_WebElements;

public class HomePageProductSearch extends BasePage{
	private HomePageProductSearch_WebElements webElements_obj =  new HomePageProductSearch_WebElements();
	private List<WebElement> productList;
	
	public boolean countWordInProduct(String productName, String searchWord) {
		int count = 0;
		webElements_obj.getSearchBoxElement().findElement(driver).sendKeys(productName);
		webElements_obj.getSearchButtonElement().findElement(driver).click();
		productList = webElements_obj.getProductListElement().findElements(driver);
		System.out.println("Total search Product for '"+productName +"' is: "+productList.size());
		for(WebElement product:productList) {
			String str = product.getText();
			String[] splitted = str.split("[\\W]");
			for(int i =0; i < splitted.length; i++) {
				if(splitted[i].equalsIgnoreCase(searchWord)) {
					count++;
				}
			}
		}
		System.out.println("Total '"+searchWord +"' worde are in the product list is: "+count);
		if(productList.size() == count) {
			return true;
		}
		return false;


	}

	/**
	 * This method will add the last product to the Cart and Verify that product are in Shopping Cart or not. 
	 * @throws InterruptedException 
	 */
	public boolean addToCartLastItems() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		String str1 = "//div[";
		String str2 = "]//div[1]//a[2]//img[2]";
		String customizexpath = str1+productList.size()+str2;
		By item = By.xpath(customizexpath);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");		 
		while (!isElementPresent(item)){
			actions.sendKeys(Keys.PAGE_UP).perform();
			}
		item.findElement(driver).click();
		webElements_obj.getAddToCartElement().findElement(driver).click();
		int numberOfItems = Integer.parseInt(webElements_obj.getNumberOfItemInCartElement().findElement(driver).getText()); //Number of Item in Shopping Cart
		if(numberOfItems > 0) {
			return true;
		}
		return false;

	}

	/**
	 * This method will empty the Cart and verify that the Cart is empty or not.
	 * @throws InterruptedException 
	 */
	public boolean emptyCart() throws InterruptedException {
		webElements_obj.getCartElement().findElement(driver).click();
		Thread.sleep(1500);
		webElements_obj.getEmptyCartElement().findElement(driver).click();
		driver.get(driver.getCurrentUrl());
		int numberOfItems = Integer.parseInt(webElements_obj.getNumberOfItemInCartElement().findElement(driver).getText());
		if(numberOfItems == 0) {
			return true;
		}
		return false;

	}

	public boolean isElementPresent(By element){
	    return !driver.findElements(element).isEmpty();
	    		
	}


}
