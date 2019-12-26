package com.vtiger.genericLib;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverCommonUtility {
	
	public void waitForPageToLoad(){
		BaseClass.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	public void waitForPageToLoad4(){
		BaseClass.driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	public void waitForElementPresent(WebElement expWebElement){
	WebDriverWait wait=new WebDriverWait(BaseClass.driver, 30);
	wait.until(ExpectedConditions.visibilityOf(expWebElement));	
	}
	public void selectByTextValue(WebElement element,String data)
	{
	 Select sel=new Select(element);
	 sel.selectByVisibleText(data);
	}
	public void selectByIndexValue(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	
	
	
	
	

}
