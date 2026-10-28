package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By userMenu = By.cssSelector(".oxd-userdropdown-tab");
    private final By logout = By.xpath("//a[normalize-space()='Logout']");
    public DashboardPage(WebDriver driver) { this.driver = driver; wait = new WebDriverWait(driver, Duration.ofSeconds(20)); }
    public boolean isLoaded() { return wait.until(ExpectedConditions.visibilityOfElementLocated(userMenu)).isDisplayed(); }
    public void logout() { wait.until(ExpectedConditions.elementToBeClickable(userMenu)).click(); wait.until(ExpectedConditions.elementToBeClickable(logout)).click(); }
}

