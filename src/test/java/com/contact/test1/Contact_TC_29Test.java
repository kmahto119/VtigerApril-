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
import org.testng.asserts.SoftAssert;

import com.vtiger.genericLib.BaseClass;
import com.vtiger.genericLib.WebdriverCommonUtility;
import com.vtiger.objectRepoLib.CreateContact;
import com.vtiger.objectRepoLib.Home;
@Listeners(com.vtiger.genericLib.ListImpClass.class)
public class Contact_TC_29Test extends BaseClass {
	@Test
	public void SelectmultipleContactAndDeleteMassContactWithFooter() throws Throwable, Throwable{
		
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		Home hp=PageFactory.initElements(driver, Home.class);
		CreateContact cp=PageFactory.initElements(driver, CreateContact.class);
		SoftAssert s=new SoftAssert();
		
		/*step 3:	Navigate to "Contacts" .*/
		hp.getcontactLnk().click();
		String actContactPageDisp=driver.getTitle();
	    String expContactPageDisp="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";		
		//boolean resultContactPageDisp=actContactPageDisp.contains(expContactPageDisp);
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
		wbcu.selectByTextValue(cp.getNameTitleOpt(),flib.getExcelData("Sheet1", 26, 6) );
		wbcu.waitForPageToLoad();
		cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 26, 7));
		cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 26, 8));
		cp.getSaveContactBtn().click();
		wbcu.waitForPageToLoad();
		
		/*2nd conact added */
		hp.getaddContactBtnImg().click();
		
		wbcu.selectByTextValue(cp.getNameTitleOpt(),flib.getExcelData("Sheet1", 27, 6));
		wbcu.waitForPageToLoad();
		cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 27, 7));
		cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 27, 8));
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
				if((actfirstname.equals(flib.getExcelData("Sheet1", 26, 7)))||(actfirstname.equals(flib.getExcelData("Sheet1", 27, 7))))
				{
					driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[1]")).click();
					count++;
			     }
		       }
			
		/*7	7	Delete the Contact with "Footer "delete" button */
		
		cp.getfooterDeleteBtnEdt().click();
		Alert alt=driver.switchTo().alert();
		String expMsgDelete="Are you sure you want to delete the selected "+count+" records?";
		String actMsgDelete=alt.getText();
		Assert.assertEquals(expMsgDelete, actMsgDelete);
		Reporter.log("7.Alert Window should pop-up with below details: Are you sure you want to delete the selected "+count+" records?",true);
		
		/*8	Click on "OK"*/
	     alt.accept();
	     wbcu.waitForPageToLoad();
	     String actContactPageDisp1=driver.getTitle();
		    String expContactPageDisp1="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";		
			Assert.assertEquals(actContactPageDisp1, expContactPageDisp1);
			Reporter.log("8.Contact information Page Should be displayed",true);
			s.assertAll();
			/*9	Search for all the Contacts and Verify*/
		cp.getsearchFor().sendKeys(flib.getExcelData("Sheet1", 27, 7));
		wbcu.selectByTextValue(cp.getsearchfieldTxtBox(), cp.getsearchfieldTxtBoxFirstName().getText());
		cp.getsearchNowBtnClick().click();
		
		String expDispNoContactFound="No Contact Found !";
		String actDispNoContactFound=cp.getnoContactFoundDisp().getText();
		Assert.assertEquals(actDispNoContactFound, expDispNoContactFound);
		Reporter.log("Contacts should not be present",true);
	}
	

}
