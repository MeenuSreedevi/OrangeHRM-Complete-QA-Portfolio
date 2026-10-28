package com.orangehrm.tests;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.DashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test(groups = {"smoke", "ui"}, description = "ORG-TC-LOGIN-001")
    public void loginControlsVisible() {
        LoginPage page = new LoginPage(driver); page.open(baseUrl);
        Assert.assertTrue(page.hasLoginControls(), "Username, password and Login must be visible");
    }
    @Test(groups = {"smoke", "positive"}, description = "ORG-TC-LOGIN-002 / ORG-TC-LOGIN-011")
    public void validLoginNavigatesToDashboard() {
        String user = requireDemoEnv("ORANGEHRM_USERNAME"); String pass = requireDemoEnv("ORANGEHRM_PASSWORD");
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.login(user, pass);
        Assert.assertTrue(new DashboardPage(driver).isLoaded(), "User menu should appear after successful login");
    }
    @Test(groups = {"negative"}, description = "ORG-TC-LOGIN-003")
    public void invalidUsernameRejected() {
        String pass = requireDemoEnv("ORANGEHRM_PASSWORD");
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.login("InvalidUser123", pass);
        Assert.assertFalse(page.errorText().isBlank(), "Invalid credentials must display an error");
    }
    @Test(groups = {"negative"}, description = "ORG-TC-LOGIN-004 / ORG-TC-LOGIN-010")
    public void invalidPasswordRejected() {
        String user = requireDemoEnv("ORANGEHRM_USERNAME");
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.login(user, "WrongPassword123");
        Assert.assertFalse(page.errorText().isBlank(), "Invalid credentials must display an error");
    }
    @Test(groups = {"negative"}, description = "ORG-TC-LOGIN-005")
    public void invalidUsernameAndPasswordRejected() {
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.login("InvalidUser123", "WrongPassword123");
        Assert.assertFalse(page.errorText().isBlank(), "Invalid credentials must display an error");
    }
    @Test(groups = {"smoke", "validation"}, description = "ORG-TC-LOGIN-006")
    public void emptyUsernameIsRequired() {
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.typePassword("ExamplePass123"); page.submit();
        Assert.assertTrue(page.requiredFieldCount() >= 1, "Username required validation expected");
    }
    @Test(groups = {"smoke", "validation"}, description = "ORG-TC-LOGIN-007")
    public void emptyPasswordIsRequired() {
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.typeUsername("Admin"); page.submit();
        Assert.assertTrue(page.requiredFieldCount() >= 1, "Password required validation expected");
    }
    @Test(groups = {"smoke", "validation"}, description = "ORG-TC-LOGIN-008")
    public void bothFieldsRequired() {
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.submit();
        Assert.assertTrue(page.requiredFieldCount() >= 2, "Both fields should be marked required");
    }
    @Test(groups = {"ui"}, description = "ORG-TC-LOGIN-009")
    public void passwordIsMasked() {
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.typePassword("NotARealPassword123");
        Assert.assertTrue(page.isPasswordMasked());
    }
    @Test(groups = {"smoke", "navigation"}, description = "ORG-TC-LOGIN-012")
    public void forgotPasswordLinkVisible() {
        LoginPage page = new LoginPage(driver); page.open(baseUrl);
        Assert.assertTrue(page.forgotPasswordVisible());
    }
    @Test(groups = {"smoke", "navigation"}, description = "ORG-TC-LOGIN-013")
    public void forgotPasswordNavigates() {
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.clickForgotPassword();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.urlContains("requestPasswordResetCode"));
        Assert.assertTrue(driver.getCurrentUrl().contains("requestPasswordResetCode"));
    }
    @Test(groups = {"smoke", "session"}, description = "ORG-TC-LOGIN-015")
    public void logoutReturnsToLogin() {
        String user = requireDemoEnv("ORANGEHRM_USERNAME"); String pass = requireDemoEnv("ORANGEHRM_PASSWORD");
        LoginPage page = new LoginPage(driver); page.open(baseUrl); page.login(user, pass);
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.isLoaded()); dashboard.logout();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        Assert.assertTrue(page.hasLoginControls(), "Login page should appear after logout");
    }
}

