package sample.basePageTest;

import sample.testUtils.ExtentTestManager;

public class CommonTestFunction {
	
	public static void getExtendReport(String browser) {
		ExtentTestManager.startTest("Test: "+new Object(){}.getClass().getEnclosingMethod().getName(),"Browser: "+browser);
	}
}
