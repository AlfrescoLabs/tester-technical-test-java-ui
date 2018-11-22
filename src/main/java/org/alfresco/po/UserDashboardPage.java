package org.alfresco.po;

import org.alfresco.utility.web.annotation.PageObject;
import org.alfresco.utility.web.annotation.RenderWebElement;
import org.alfresco.utility.web.browser.WebBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@PageObject
public class UserDashboardPage extends Page<UserDashboardPage>
{
    @RenderWebElement
    @FindBy(css = ".adf-app-title")
    private WebElement appTitle;

    @FindBy(css = ".aca-current-user .current-user__full-name")
    private WebElement userFullName;

    @FindBy(css = ".aca-current-user .current-user__avatar")
    private WebElement userAvatar;

    private final By signOutButtonSelector = By.xpath("//button[@class='mat-menu-item' and contains(text(), 'Sign out')]");

    private final By menuItemSelector = By.cssSelector(".mat-menu-item");

    public String getAppTitle()
    {
        return appTitle.getText();
    }

    public String getUserFullName()
    {
        return userFullName.getText();
    }

    public UserDashboardPage logout() {
        WebBrowser browser = getBrowser();
        userAvatar.click();

        browser.waitUntilElementIsPresent(menuItemSelector, 2L);
        List<WebElement> buttons = browser
            .findElements(menuItemSelector)
            .stream()
            .filter(t -> t.getText().contains("Sign out"))
            .collect(Collectors.toList());

        WebElement signOutButton = buttons.get(0);
        browser.mouseOver(signOutButton);
        signOutButton.click();

        return this;
    }
}
