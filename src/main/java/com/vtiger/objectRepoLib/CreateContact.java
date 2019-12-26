package com.vtiger.objectRepoLib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.vtiger.genericLib.WebdriverCommonUtility;

public class CreateContact {
	@FindBy(name="firstname")
	private WebElement customerFirstNameEdt;

	@FindBy(name="lastname")
	private WebElement customerLastNameEdt;
	
	@FindBy(xpath="(//input[@name='button'])[3]")
	private WebElement saveContactBtn;
	
	@FindBy(xpath="//span[text()='Creating New Contact']")
	private WebElement createContactPageDisp;
	
	@FindBy(name="salutationtype")
	private WebElement nameTitleOpt;
	
	@FindBy(name="assigned_user_id")
	private WebElement assignUserType;
	
	@FindBy(id="mouseArea_Assigned To")
	private WebElement verifyAssignUserType;
	
	@FindBy(xpath="//div[@id='editarea_ModCommentsDetailViewBlockCommentWidget']/input[@value='Save']")
	private WebElement contactInfoSave;
	
	@FindBy(xpath="(//input[@value='Delete'])[2]")
	private WebElement footerDeleteBtnEdt;
	
	@FindBy(name="search_text")
	private WebElement searchFor;
	
	@FindBy(xpath="//div[@id='basicsearchcolumns_real']/select[@id='bas_searchfield']")
	private WebElement searchfieldTxtBox;
	
	@FindBy(xpath="//input[@name='search_text']/../../td[5]/div[1]/select/option[2]")
	private WebElement searchfieldTxtBoxFirstName;
	
	@FindBy(name="submit")
	private WebElement searchNowBtnClick;
	//
	@FindBy(xpath="//table[@class='lvt small']/tbody/tr[*]/td[3]/a")
	private List<WebElement> firstNameContactList;
	
	public List<WebElement> getfirstNameContactList(){
		return firstNameContactList;
	}
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactInfoDisp;
	
	@FindBy(xpath="//td[@class='dvtTabCache']/input[@name='Delete']")
	private WebElement contactInfoDelete;
	
	@FindBy(xpath="//span[@class='genHeaderSmall']")
	private WebElement noContactFoundDisp;
	
	
	@FindBy(xpath="//span[@id='dtlview_First Name']")
	private WebElement contactInfoFirstName;
	
	
	@FindBy(xpath="//td[@class='dvtSelectedCell']")
	private WebElement addContactInfoPageDisp;
	
	public WebElement getaddContactInfoPageDisp(){
		return addContactInfoPageDisp;
	}
	
	
	public WebElement getcontactInfoFirstName(){
		return contactInfoFirstName;
		
	}
	
	public WebElement getnoContactFoundDisp(){
		return noContactFoundDisp;
		
	}
	
	public WebElement getcontactInfoDelete(){
		return contactInfoDelete;
	}
	
	public WebElement getcontactInfoDisp(){
		return contactInfoDisp;
	}

	
	public WebElement getsearchNowBtnClick(){
		return searchNowBtnClick;
	}
	
	public WebElement getsearchfieldTxtBoxFirstName(){
		return searchfieldTxtBoxFirstName;
	}
	
	public WebElement getsearchfieldTxtBox(){
		return searchfieldTxtBox;
	}
	
	
	public WebElement getsearchFor(){
	return searchFor;	
	}
	
	public WebElement getcontactInfoSaveBtnEdt(){
		return contactInfoSave;
	}
	public WebElement getfooterDeleteBtnEdt(){
		return footerDeleteBtnEdt;
		
	}
	
	@FindBy(xpath="(//input[@value='Delete'])[1]")
	private WebElement headerDeleteBtnEdt;
	
	
	public WebElement getheaderDeleteBtnEdt(){
		return headerDeleteBtnEdt;
		
	}
	
	

	public WebElement getCustomerFirstNameEdt() {
		return customerFirstNameEdt;
	}

	public WebElement getCustomerLastNameEdt() {
		return customerLastNameEdt;
	}

	public WebElement getSaveContactBtn() {
		return saveContactBtn;
	}

	public WebElement getCreateContactPageDisp() {
		return createContactPageDisp;
	}

	public WebElement getNameTitleOpt() {
		return nameTitleOpt;
	}

	public WebElement getAssignUserType() {
		return assignUserType;
	}
	
	public WebElement getverifyAssignUserTypeEdt(){
		return verifyAssignUserType;
	}
	
	/*business methods*/
	
	public void getcreateContact(String customerNAme){
		customerFirstNameEdt.sendKeys(customerNAme);
		saveContactBtn.click();
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		wbcu.waitForPageToLoad();
	}	
	public void getcreateContact(String customerNAme,String ContactLastname){
		customerFirstNameEdt.sendKeys(customerNAme);
		customerLastNameEdt.sendKeys(ContactLastname);
		saveContactBtn.click();
		WebdriverCommonUtility wbcu=new WebdriverCommonUtility();
		wbcu.waitForPageToLoad();
	}
	
	
	
	
	
	
}
