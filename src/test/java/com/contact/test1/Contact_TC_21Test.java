package com.contact.test1;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.vtiger.genericLib.BaseClass;
import com.vtiger.genericLib.WebdriverCommonUtility;
import com.vtiger.objectRepoLib.CreateContact;
import com.vtiger.objectRepoLib.Home;

/**
 * 
 * @author kamlesh
 *
 */
@Listeners(com.vtiger.genericLib.ListImpClass.class)
public class Contact_TC_21Test extends BaseClass{
	@Test
	public void createcontactwithAdministratorTest() throws Throwable, Throwable
	{
		
		/*create an object of contact class */
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		Home hp=PageFactory.initElements(driver, Home.class);
		CreateContact cp=PageFactory.initElements(driver, CreateContact.class);

		/*step 3	click on "+" image Button to create new Contact*/
				
				hp.getcontactLnk().click();
				wbcu.waitForPageToLoad();
				hp.getaddContactBtnImg().click();
				wbcu.waitForPageToLoad4();

				String expOptCreateContact="Creating New Contact";
				String actOptCreateContact=cp.getCreateContactPageDisp().getText();
				boolean stat=actOptCreateContact.contains(expOptCreateContact);
				Assert.assertEquals(stat, true);
				Reporter.log(actOptCreateContact+" is avilable", true);
				
				
		/*step 4	In Assigned To component select Administrator if we select Administrator drop down should must be visible*/
				
	
				Select sel=new Select(cp.getAssignUserType());
				List<WebElement> lst=sel.getOptions();
				
				String expOpt="  Administrator  ";
				for (WebElement wb : lst) {
					  String userList=wb.getText();
				   if(userList.equals(expOpt))
				      {
					     sel.selectByVisibleText(expOpt);
					    
					     break;
				      }
				}
				
				//contactPage.getcreateContact(ContactFirstname, ContactLastname); /*add the contact*/
				/*add 1st contact*/
				
				
				wbcu.selectByTextValue(cp.getNameTitleOpt(),flib.getExcelData("Sheet1", 4, 6) );
				wbcu.waitForPageToLoad();
				cp.getCustomerFirstNameEdt().sendKeys(flib.getExcelData("Sheet1", 4, 7));
				cp.getCustomerLastNameEdt().sendKeys(flib.getExcelData("Sheet1", 4, 8));
				cp.getSaveContactBtn().click();
				wbcu.waitForPageToLoad();
		
				Reporter.log("contact created",true);
				
		/*validation for select Administrator drop down should must be visible*/
				
				
				String actOpt=cp.getverifyAssignUserTypeEdt().getText();	
				//SoftAssert s1=new SoftAssert();
				//boolean stat1=actOpt.contains(expOpt);
				//Assert.assertEquals(stat1, true);
				Assert.assertEquals(actOpt, expOpt);
				
				Reporter.log(actOpt+"is avilable", true);
				//s1.assertAll();
				wbcu.waitForPageToLoad();
				System.out.println("success123");
				
				
	}
	

}
 