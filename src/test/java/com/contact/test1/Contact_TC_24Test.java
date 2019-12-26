package com.contact.test1;


import java.util.List;
import java.util.concurrent.TimeUnit;

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
public class Contact_TC_24Test extends BaseClass {
	@Test
	public void SelectContactAndDeleteOneContactWithHeaderDelete() throws Throwable, Throwable{
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		Home hp=PageFactory.initElements(driver, Home.class);
		CreateContact cp=PageFactory.initElements(driver, CreateContact.class);
		//SoftAssert s=new SoftAssert();
		
		/*step 3:	Navigate to "Contacts" .*/
		hp.getcontactLnk().click();
		String actContactPageDisp=driver.getTitle();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    String expContactPageDisp="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";		
		Assert.assertEquals(actContactPageDisp, expContactPageDisp);
		Reporter.log("Contact Page Should be displayed",true);
		
		/*step 4:	Create Contact by clicking '+' icon*/
		hp.getaddContactBtnImg().click();
		wbcu.waitForPageToLoad();
		String actAddContactInfo=cp.getaddContactInfoPageDisp().getText();
		String expAddContactInfo="Contact Information";
		Assert.assertEquals(actAddContactInfo, expAddContactInfo);
		Reporter.log("Contact information Page should be displayed",true);
		
		/* step 5:	Enter the Valid Data and click on Save */
		/*First contact added */
		wbcu.selectByTextValue(cp.getNameTitleOpt(),flib.getExcelData("Sheet1", 11, 6) );
		wbcu.waitForPageToLoad();
		cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 11, 7));
		cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 11, 8));
		cp.getSaveContactBtn().click();
		wbcu.waitForPageToLoad();
		
		
		String actContactInfoDisp=cp.getcontactInfoDisp().getText();
		String expContactInfoDips=cp.getcontactInfoFirstName().getText()+" - Contact Information";
		boolean resultConInfo=actContactInfoDisp.contains(expContactInfoDips);
		Assert.assertEquals(resultConInfo, true);
		Reporter.log("Contact should be created Successfully",true);

	           /* step 6	Select multiple created Contact From Checkbox*/

		hp.getcontactLnk().click();
		

		List<WebElement> lst=cp.getfirstNameContactList();
		int noOfRow=lst.size();
		int count=0;
		for (int i = 3; i <= noOfRow+1; i++) {
				String actfirstname=driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[3]/a")).getText();
				//if(actfirstname!="")/*select all customer name*/
				boolean first=(actfirstname.equals(flib.getExcelData("Sheet1", 11, 7)));
				if(first)
				{
					driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[1]")).click();
					count++;
					//break;
			     }
		       }
			
		/*7	Delete the Contact with "Header "delete" button */
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cp.getheaderDeleteBtnEdt().click();
		
		
		
		Alert alt=driver.switchTo().alert();
		String expMsgDelete="Are you sure you want to delete the selected "+count+" records?";
		String actMsgDelete=alt.getText();
		Assert.assertEquals(expMsgDelete, actMsgDelete);
		Reporter.log("7.Alert Window should pop-up with below details: Are you sure you want to delete the selected "+count+" records?",true);
		
		/*8	Click on "OK"*/
	    alt.accept();
	    
	    wbcu.waitForElementPresent(driver.findElement(By.xpath("//tABLE[@class='lvt small']")));
	    
	    wbcu.waitForPageToLoad();
	    String actContactPageDisp1=driver.getTitle();
		String expContactPageDisp1="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";		
		Assert.assertEquals(actContactPageDisp1, expContactPageDisp1);
		Reporter.log("8.Contact information Page Should be displayed",true);
		//s.assertAll();
			
		Thread.sleep(5000);
	
	  
		
		}
}