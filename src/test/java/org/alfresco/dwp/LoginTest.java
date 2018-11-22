package org.alfresco.dwp;

import org.alfresco.common.BaseTest;
import org.alfresco.common.EnvProperties;
import org.alfresco.po.LoginPage;
import org.alfresco.po.UserDashboardPage;
import org.alfresco.utility.Utility;
import org.alfresco.utility.data.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test login to Digital Workspace Application
 */
public class LoginTest extends BaseTest
{
    @Autowired
    private LoginPage loginPage;

    @Autowired
    private UserDashboardPage userDashboardPage;

    @Autowired
    protected EnvProperties properties;

    @Test
    public void loginAsAdminUser() {
        loginPage.navigate();
        loginPage.typeUserName(properties.getAdminUser());
        loginPage.typePassword(properties.getAdminPassword());
        loginPage.clickLogin();

        //getBrowser().waitInSeconds(3);
        assertEquals(properties.getAdminName(), userDashboardPage.getUserFullName());

        userDashboardPage.logout();

        getBrowser().waitInSeconds(3);
    }

    @Test
    public void loginAsInvalidUser() {
        loginPage.navigate();
        loginPage.typeUserName("foo");
        loginPage.typePassword("bar");
        loginPage.clickLogin();

        //getBrowser().waitInSeconds(3);
        assertTrue(loginPage.isAuthenticationErrorDisplayed());
    }

}
