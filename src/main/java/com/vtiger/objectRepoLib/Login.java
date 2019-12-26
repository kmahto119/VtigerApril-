package com.vtiger.objectRepoLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * 
 * @author kamlesh
 *
 */
public class Login {  //Rule-1
	
	 //IDentification or initialization of Elements  //Rule -2
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginButon;
	 //usage                             //Rule -3
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}
	public WebElement getPasswordEdt() {
		return passwordEdt;
	}
	public WebElement getLoginButon() {
		return loginButon;
	}
	//Business mtd                      //Rule-4 
	public void loginToApp(String userName , String password){
		usernameEdt.sendKeys(userName);
		passwordEdt.sendKeys(password);
		loginButon.click();
	}
	
	
}
