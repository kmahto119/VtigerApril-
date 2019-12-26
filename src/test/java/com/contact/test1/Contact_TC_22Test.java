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
public class Contact_TC_22Test extends BaseClass {
	@Test
	public void OpenContactAndDeleteThatContact() throws Throwable, Throwable
	
	{
		Home hp=PageFactory.initElements(driver, Home.class);
		CreateContact cp=PageFactory.initElements(driver, CreateContact.class);
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		
		/*3	Navigate to "Contacts" .*/
		
		hp.getcontactLnk().click();
		wbcu.waitForPageToLoad();
		String actContactPage=driver.getTitle();
		String expContactPage="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(actContactPage, expContactPage);/*hard assert validation of contact page*/
		Reporter.log("Contact Page displayed",true);
		
		/*4	Open contact by Name*/
		/*add 1st contact*/
		
		hp.getaddContactBtnImg().click();
		wbcu.waitForPageToLoad4();
		wbcu.selectByTextValue(cp.getNameTitleOpt(),flib.getExcelData("Sheet1", 4, 6) );
		wbcu.waitForElementPresent(cp.getCustomerFirstNameEdt());
		cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 4, 7));
		cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 4, 8));
		cp.getSaveContactBtn().click();
		wbcu.waitForPageToLoad();
		
		hp.getaddContactBtnImg().click();
		wbcu.waitForPageToLoad();
		

		hp.getcontactLnk().click();
		int count=0;
		List<WebElement> lst=cp.getfirstNameContactList();
		
		int noOfElement=lst.size();
		for (int i = noOfElement+1; i > 2; i--) {
			
		//}
		
		//for (WebElement firstNamewebElement : lst) {
			//String fname=driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[3]/a")).getText();
			WebElement firstNamewebElement=driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[3]/a"));
			String fname=firstNamewebElement.getText();
			count++;
			System.out.println(count+"=="+fname);
			String expFirstName=flib.getExcelData("Sheet1", 4, 7);
			if (expFirstName.equals(fname)){
				
				firstNamewebElement.click();
				
				String actDisp=cp.getcontactInfoDisp().getText();
				String expDisp=flib.getExcelData("Sheet1", 4, 7)+" - Contact Information";	
				boolean result=actDisp.contains(expDisp);
				Assert.assertEquals(result, true);
				Reporter.log("Contact Information Page displayed",true);
			
				/* step 5: Delete the Contact */
				cp.getcontactInfoDelete().click();
				Alert alt=driver.switchTo().alert();
				String actAltMsg=alt.getText();
				String expAltMsg="Are you sure you want to delete this record?";
				Assert.assertEquals(actAltMsg, expAltMsg);
				Reporter.log("Alert Window should pop-up with below details: Are you sure you want to delete this record?",true);
				
				
				/*6	Click on "OK"*/
				 alt.accept();
			     wbcu.waitForPageToLoad();
			     String actContactPageDisp1=driver.getTitle();
				 String expContactPageDisp1="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";		
				 Assert.assertEquals(actContactPageDisp1, expContactPageDisp1);
				 Reporter.log("8.Contact information Page Should be displayed",true);
				 hp.getcontactLnk();
				
				 noOfElement--;
			}
			
		}
		
	/*	7	Search for that Contact and Verify*/
		wbcu.waitForPageToLoad();
		cp.getsearchFor().sendKeys(flib.getExcelData("Sheet1", 4, 7));
		wbcu.selectByTextValue(cp.getsearchfieldTxtBox(), cp.getsearchfieldTxtBoxFirstName().getText());
		cp.getsearchNowBtnClick().click();
		
		String expDispNoContactFound="No Contact Found !";
		String actDispNoContactFound=cp.getnoContactFoundDisp().getText();
		Assert.assertEquals(actDispNoContactFound, expDispNoContactFound);
		Reporter.log("Contacts should not be present",true);
	
	}

}
