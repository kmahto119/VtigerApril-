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
public class Contact_TC_25Test extends BaseClass{
	@Test
	public void SelectContactAndDeleteOneContactWithHeaderDeleteAndCancel() throws Throwable, Throwable{
		
		Home hp=PageFactory.initElements(driver, Home.class);
		CreateContact cp=PageFactory.initElements(driver, CreateContact.class);
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		
		/*3	Navigate to "Contacts" .*/
		hp.getcontactLnk().click();
		String actContactPageDisp=driver.getTitle();
		String expContactPageDisp="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(actContactPageDisp, expContactPageDisp);
		Reporter.log("Contact Page Should be displayed",true);
		
		/*4	Create Contact by clicking '+' icon*/
		
		hp.getaddContactBtnImg().click();/*click + icon*/
		wbcu.waitForPageToLoad();
		String actAddContactInfo=cp.getaddContactInfoPageDisp().getText();
		String expAddContactInfo="Contact Information";
		Assert.assertEquals(actAddContactInfo, expAddContactInfo);
		Reporter.log("Contact information Page should be displayed",true);
		
		
		/*5	Enter the Valid Data and click on Save*/
		
	

		wbcu.selectByTextValue(cp.getNameTitleOpt(),flib.getExcelData("Sheet1", 14, 6));
		cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 14, 7));
		cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 14, 8));
		cp.getSaveContactBtn().click();
		
		
		/*6	Select created Contact From Checkbox*/
		hp.getcontactLnk().click();
		cp.getsearchFor().sendKeys(flib.getExcelData("Sheet1", 14, 7));
		wbcu.selectByTextValue(cp.getsearchfieldTxtBox(), cp.getsearchfieldTxtBoxFirstName().getText());
		cp.getsearchNowBtnClick().click();
		
		/*7	Delete the Contact with header "delete" button*/
		List<WebElement> lst=cp.getfirstNameContactList();
		int noOfColumn=lst.size();
		for (int i = 3; i < noOfColumn+1; i++) {
			String actFirstName=driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[3]")).getText();
			String expFirstName=flib.getExcelData("Sheet1", 14, 7);
			if(expFirstName.equals(actFirstName))
			{
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[1]")).click();
				cp.getheaderDeleteBtnEdt().click();
				//Thread.sleep(2000);
				wbcu.waitForPageToLoad();
				Alert alt=driver.switchTo().alert();
				String actAltMsg=alt.getText();
				String expAltMsg="Are you sure you want to delete the selected 1 records?";
				Assert.assertEquals(actAltMsg, expAltMsg);
				Reporter.log("Alert Window should pop-up with below details: Are you sure you want to delete the selected 1 records?");

				/*8	Click on "Cancel"*/
				alt.dismiss();
				
				break;
			}
			
		}
		
		

		

		
		
	}



}
