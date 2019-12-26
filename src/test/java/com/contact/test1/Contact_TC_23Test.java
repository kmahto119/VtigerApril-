package com.contact.test1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
public class Contact_TC_23Test extends BaseClass {
	@Test
	public void OpenContactAndDeleteThatContactAndCancelTest() throws Throwable, Throwable
	{
		/*step 3: Navigate to "Contacts" .*/
		Home hp=PageFactory.initElements(driver, Home.class);
		CreateContact cp=PageFactory.initElements(driver, CreateContact.class);
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		
		hp.getcontactLnk().click();
		
		wbcu.waitForPageToLoad();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String actContactPage=driver.getTitle();
		String expContactPage="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(actContactPage, expContactPage);/*hard assert validation of contact page*/
		Reporter.log("Contact Page displayed",true);
		
		/* pre conditions Create a contact with "Barbara"*/
		hp.getaddContactBtnImg().click();/*add image click to add contact*/
		wbcu.waitForPageToLoad();
		
		wbcu.selectByTextValue(cp.getNameTitleOpt(), flib.getExcelData("Sheet1", 8, 6));
		cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 8, 7));
		cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 8, 8));
		cp.getSaveContactBtn().click();
		wbcu.waitForPageToLoad();
		hp.getcontactLnk().click();
		wbcu.waitForPageToLoad();
		
		/*Step 4: Open contact by Name*/
		cp.getsearchFor().sendKeys(flib.getExcelData("Sheet1", 8, 7));
		wbcu.selectByTextValue(cp.getsearchfieldTxtBox(), cp.getsearchfieldTxtBoxFirstName().getText());
		cp.getsearchNowBtnClick().click();
		
		List<WebElement> lst=cp.getfirstNameContactList();
		for (WebElement webElement : lst) {
			String expContactName=flib.getExcelData("Sheet1", 8, 7);
			String actContactName=webElement.getText();
			if(expContactName.equals(actContactName))
			{
				webElement.click();
				wbcu.waitForPageToLoad();
				break;
			}
			
		}
		
	   String actDisp=cp.getcontactInfoDisp().getText();
	   String expDisp=flib.getExcelData("Sheet1", 8, 7)+" - Contact Information";
		
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
		
		/* step 6:  Click on "Cancel"*/
		alt.dismiss();
		String ExpResultAfterAltCancel=cp.getcontactInfoFirstName().getText()+" - Contact Information";
        String actResultAfterAltCancel=cp.getcontactInfoDisp().getText();
        boolean resultContact=actResultAfterAltCancel.contains(ExpResultAfterAltCancel);
        Assert.assertEquals(resultContact, true);
        Reporter.log("Contact information Page Should be displayed",true);
        

		


		
		
		
		
		
		
		
	}
}
