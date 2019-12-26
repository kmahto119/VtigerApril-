package com.contact.test1;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vtiger.genericLib.BaseClass;
import com.vtiger.genericLib.WebdriverCommonUtility;
import com.vtiger.objectRepoLib.CreateContact;
import com.vtiger.objectRepoLib.Home;

@Listeners(com.vtiger.genericLib.ListImpClass.class)
public class Contact_TC_26Test extends BaseClass {
	@Test
	public void SelectContactAndDeleteOneContactWithFooterDelete() throws Throwable, Throwable{
		
		/*3	Navigate to "Contacts" .*/
		Home hp=PageFactory.initElements(driver, Home.class);
		CreateContact cp=PageFactory.initElements(driver, CreateContact.class);
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		
		hp.getcontactLnk().click();
		wbcu.waitForPageToLoad();
		String actcontactPageDisp=driver.getTitle();
		String expContactPageDisp="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(actcontactPageDisp, expContactPageDisp);
		Reporter.log("Contact Page Should be displayed",true);
		
		
		/*4	Create Contact by clicking '+' icon*/
		hp.getaddContactBtnImg().click();
		wbcu.waitForPageToLoad4();
		String actContactInfoDisp=cp.getaddContactInfoPageDisp().getText();
		String expContactInfoDisp="Contact Information";
		Assert.assertEquals(actContactInfoDisp, expContactInfoDisp);
		Reporter.log("Contact information Page should be displayed",true);
		
		
		/*5	Enter the Valid Data and click on Save*/
		wbcu.selectByTextValue(cp.getNameTitleOpt(), flib.getExcelData("Sheet1", 17, 6));
		cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 17, 7));
		cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 17, 8));
		cp.getSaveContactBtn().click();
		wbcu.waitForPageToLoad();
		String actContactDisp=cp.getcontactInfoDisp().getText();
		String expContactDisp=cp.getcontactInfoFirstName().getText()+" - Contact Information";
		boolean result=actContactDisp.contains(expContactDisp);
		Assert.assertEquals(result, true);
		Reporter.log("Contact should be created Successfully",true);
		
		
		/*6	Select created Contact From Checkbox*/
		hp.getcontactLnk().click();
		wbcu.waitForPageToLoad4();
		cp.getsearchFor().sendKeys(flib.getExcelData("Sheet1", 17, 7));
		wbcu.selectByTextValue(cp.getsearchfieldTxtBox(), cp.getsearchfieldTxtBoxFirstName().getText());
		cp.getsearchNowBtnClick().click();
		wbcu.waitForPageToLoad();
		
	
		String expContactNameDelete=flib.getExcelData("Sheet1", 17, 7);
		List<WebElement> lst=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[3]"));
		int noOfCol=lst.size();
		for (int i = 3; i < noOfCol+1; i++) {
			String actContactNameDelete=driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[3]")).getText();
			if(actContactNameDelete.equals(expContactNameDelete))
			{
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[1]")).click();
				
				wbcu.waitForPageToLoad();
				
				/*	7	Delete the Contact with "Footer "delete" button */	
				
				cp.getfooterDeleteBtnEdt().click();
				//wbcu.waitForElementPresent(cp.getfooterDeleteBtnEdt());
				Alert alt=driver.switchTo().alert();
				String actAltMsg=alt.getText();
				String expAltMsg="Are you sure you want to delete the selected 1 records?";
				Assert.assertEquals(actAltMsg, expAltMsg);
				Reporter.log("Alert Window should pop-up with below details: Are you sure you want to delete the selected 1 records?",true);
				
				/*8	Click on "OK"*/
				alt.accept();
				 wbcu.waitForPageToLoad();
				
				
				break;
			}
			
		}
		
	     String actContactPageDisp1=driver.getTitle();
		 String expContactPageDisp1="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";		
		 Assert.assertEquals(actContactPageDisp1, expContactPageDisp1);
		 Reporter.log("8.Contact information Page Should be displayed=Pass",true);
			//s.assertAll();
		//Thread.sleep(8000);
		 wbcu.waitForPageToLoad4();
		
		

		
	}
	


}
