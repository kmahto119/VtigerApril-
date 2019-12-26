package com.vtiger.objectRepoLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home {

	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement addContactBtnImg;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLnk;
	
	@FindBy(linkText="Sign Out")
	private WebElement logoutLnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement logOutBtnImg;
	
	public WebElement getlogOutBtnImg(){
		return logOutBtnImg;
	}
	
	public WebElement getaddContactBtnImg() {
		return addContactBtnImg;
	}
	public WebElement getcontactLnk() {
		return contactLnk;
	}
	public WebElement getLogOutLnk(){
		return logoutLnk;
	}
}
