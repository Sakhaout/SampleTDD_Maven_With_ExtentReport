package web.staurant.store.webElements;

import org.openqa.selenium.By;

public class HomePageProductSearch_WebElements {
	private By searchBox = null;
	private By searchButton = null;
	private By productList = null;
	private By addToCart = null;
	private By numberItemCount = null;
	private By emprtCart = null;
	private By cart = null;
	
	public HomePageProductSearch_WebElements() {
		this.searchBox = By.xpath("//input[@id='searchval']");
		this.searchButton = By.xpath("//button[@class='btn btn-info banner-search-btn']");
		this.productList = By.xpath("//div[@class='ag-item gtm-product']");
		this.addToCart = By.xpath("//input[@id='buyButton']");
		this.numberItemCount = By.xpath("//span[@id='cartItemCountSpan']");
		this.emprtCart = By.xpath("//div[@class='cartItem ag-item gtm-product-auto']//span[contains(text(),'×')]");
		this.cart = By.xpath("//span[@class='menu-btn-text'][contains(text(),'Cart')]");
	}
	
	public By getSearchBoxElement() {
		return searchBox;
	}
	
	public By getSearchButtonElement() {
		return searchButton;
	}
	
	public By getProductListElement() {
		return productList;
	}
	
	public By getAddToCartElement() {
		return addToCart;
	}
	
	public By getNumberOfItemInCartElement() {
		return numberItemCount;
	}
	
	public By getEmptyCartElement() {
		return emprtCart;
	}
	
	public By getCartElement() {
		return cart;
	}

}
