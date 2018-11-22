package org.alfresco.po;

import org.alfresco.utility.web.HtmlPage;
import org.alfresco.utility.web.annotation.PageObject;
import org.alfresco.utility.web.annotation.RenderWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Login Page Page Object class
 */
@PageObject
public class LoginPage extends Page<HtmlPage>
{
    @FindBy(css = "[id='username']")
    private WebElement usernameInput;

    @FindBy(css = "[id='password']")
    private WebElement passwordInput;

    @RenderWebElement
    @FindBy(css = "[id='login-button']")
    private WebElement loginButton;

    @FindBy(css = ".login-error-message")
    private WebElement errorLogin;

    public void navigate() {
        browser.navigate().to(properties.getDigitalWorkspaceUrl().toString());
        renderedPage();
    }

    /**
     * Type user name
     * @param userName
     */
    public void typeUserName(String userName) {
        usernameInput.clear();
        usernameInput.sendKeys(userName);
    }

    /**
     * Type password
     * @param password to be filled in
     */
    public void typePassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Click login button
     */
    public void clickLogin()
    {
        loginButton.click();
    }

    /**
     * Login on Share using login form
     * @param username to be filled in
     * @param password to be filled in
     */
    public void login(String username, String password) {
        typeUserName(username);
        typePassword(password);
        clickLogin();        
    }

    /**
     * Get the error when the login fails
     * @return String error message
     */
    public String getAuthenticationError() {
        browser.waitUntilWebElementIsDisplayedWithRetry(errorLogin);
        return errorLogin.getText();
    }

    /**
     * Verify if the login error is displayed
     * @return true if displayed
     */
    public boolean isAuthenticationErrorDisplayed()
    {
        return browser.isElementDisplayed(errorLogin);
    }
}