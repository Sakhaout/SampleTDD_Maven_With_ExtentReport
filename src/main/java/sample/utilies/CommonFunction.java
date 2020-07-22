package sample.utilies;

import java.util.concurrent.TimeUnit;

import sample.basepage.BasePage;

public class CommonFunction extends BasePage {
	public static void implicitlyWait(int timeInSecond) {
		driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
	}
	
	public static void pageLoadTimeout(int timeInSecond) {
		driver.manage().timeouts().pageLoadTimeout(timeInSecond, TimeUnit.SECONDS);
	}

}
