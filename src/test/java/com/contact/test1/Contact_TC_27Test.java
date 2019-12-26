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
public class Contact_TC_27Test extends BaseClass{
	@Test
	public void SelectContactAndDeleteOneContactWithHeaderDeleteAndCancel() throws Throwable, Throwable{
		Home hp=PageFactory.initElements(driver, Home.class);
		CreateContact cp=PageFactory.initElements(driver, CreateContact.class);
		/*3	Navigate to "Contacts" .*/
		hp.getcontactLnk().click();
		String expContactPageDisp="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		String actContactPageDisp=driver.getTitle();
		Assert.assertEquals(actContactPageDisp, expContactPageDisp);
		Reporter.log("Contact Page Should be displayed",true);
		
		/*4	Create Contact by clicking '+' icon*/
		hp.getaddContactBtnImg().click();
		String expInfoDisp="Contact Information";
		String actInfoDisp=cp.getaddContactInfoPageDisp().getText();
		Assert.assertEquals(actInfoDisp, expInfoDisp);
		Reporter.log("Contact information Page should be displayed",true);
		
		/*5	Enter the Valid Data and click on Save*/
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		wbcu.selectByTextValue(cp.getNameTitleOpt(), flib.getExcelData("Sheet1", 20, 6));
		cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 20, 7));
		cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 20, 8));
		cp.getSaveContactBtn().click();
		
		String expContactDisp=cp.getcontactInfoFirstName().getText()+" - Contact Information";
		String actContactDisp=cp.getcontactInfoDisp().getText();
		boolean resultInfDisp=actContactDisp.contains(expContactDisp);
		Assert.assertEquals(resultInfDisp, true);
		Reporter.log("Contact should be created Successfully",true);
		
		/*6	Select created Contact From Checkbox*/
		hp.getcontactLnk().click();
		cp.getsearchFor().sendKeys(flib.getExcelData("Sheet1", 20, 7));
		wbcu.selectByTextValue(cp.getsearchfieldTxtBox(),cp.getsearchfieldTxtBoxFirstName().getText());
		cp.getsearchNowBtnClick().click();
		
		/*7	Delete the Contact with "Footer "delete" button */
		List<WebElement> lst=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[3]"));
		int noOfRow=lst.size();
		for (int i = 3; i <= noOfRow+1; i++) {
			String expContact=flib.getExcelData("Sheet1", 20, 7);
			String actContact=driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[3]")).getText();
			if (actContact.equals(expContact)) {
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+i+"]/td[1]")).click();
				break;
				
			}
		}
			
			cp.getfooterDeleteBtnEdt().click();
			Alert alt=driver.switchTo().alert();
			String actAltMsg=alt.getText();
			String expAltMsg="Are you sure you want to delete the selected 1 records?";
			Assert.assertEquals(actAltMsg, expAltMsg);
			Reporter.log("Alert Window should pop-up with below details: Are you sure you want to delete the selected 1 records?",true);
			
			/*8	Click on "Cancel"*/
			alt.dismiss();
			String expInfoPage="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
			String actInfoPage=driver.getTitle();
			Assert.assertEquals(actInfoPage, expInfoPage);
			Reporter.log("Contact information Page Should be displayed",true);
			
			
			
			
		
		
		

		
	}
	

}
