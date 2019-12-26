package com.vtiger.genericLib;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * 
 * @author kamlesh
 *
 */

public class ListImpClass implements ITestListener  {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		Date date=new Date();
		String currentDate=date.toString().replace(":", "_").replace(" ", "_");
		
		String failedTestName=result.getMethod().getMethodName();
		System.out.println("--------failed----"+failedTestName);
		EventFiringWebDriver eDriver=new EventFiringWebDriver(BaseClass.driver);
		File srcFile=eDriver.getScreenshotAs(OutputType.FILE);
		File destFile=new File("./screenshot/"+failedTestName+"_"+currentDate+".png");
		try{
			FileUtils.copyFile(srcFile, destFile);
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * used to take screenshot whenever est getting failed
	 * 
	 */

	

}
