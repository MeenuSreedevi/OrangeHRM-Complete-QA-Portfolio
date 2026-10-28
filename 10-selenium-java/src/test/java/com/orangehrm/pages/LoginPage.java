package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By alert = By.cssSelector(".oxd-alert-content-text");
    private final By required = By.cssSelector(".oxd-input-field-error-message");
    private final By forgotPassword = By.xpath("//p[contains(normalize-space(.),'Forgot your password')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public void open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    }
    public boolean hasLoginControls() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(username)).isDisplayed()
          && driver.findElement(password).isDisplayed() && driver.findElement(loginButton).isDisplayed();
    }
    public void typeUsername(String value) { WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(username)); el.clear(); el.sendKeys(value); }
    public void typePassword(String value) { WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(password)); el.clear(); el.sendKeys(value); }
    public void submit() { wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click(); }
    public void login(String user, String pass) { typeUsername(user); typePassword(pass); submit(); }
    public boolean isPasswordMasked() { return "password".equalsIgnoreCase(driver.findElement(password).getAttribute("type")); }
    public String errorText() { return wait.until(ExpectedConditions.visibilityOfElementLocated(alert)).getText(); }
    public int requiredFieldCount() { return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(required, 0)).size(); }
    public boolean forgotPasswordVisible() { return driver.findElement(forgotPassword).isDisplayed(); }
    public void clickForgotPassword() { driver.findElement(forgotPassword).click(); }
    public boolean recoveryPageVisible() { return wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("requestPasswordResetCode"), ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Reset Password') or contains(text(),'Reset password') or contains(text(),'Forgot your password') ]")))); }
}

