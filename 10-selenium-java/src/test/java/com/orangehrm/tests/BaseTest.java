package com.orangehrm.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseTest {
    protected WebDriver driver;
    protected final String baseUrl = System.getenv().getOrDefault("ORANGEHRM_BASE_URL", "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) options.addArguments("--headless=new");
        options.addArguments("--window-size=1440,900");
        driver = new ChromeDriver(options); // Selenium Manager handles ChromeDriver automatically.
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (driver != null && !result.isSuccess()) {
                Path folder = Path.of("target", "failure-screenshots");
                Files.createDirectories(folder);
                String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS"));
                String name = result.getMethod().getMethodName().replaceAll("[^A-Za-z0-9_-]", "_");
                Path dest = folder.resolve(name + "-" + stamp + ".png");
                Files.write(dest, ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
                System.out.println("Failure screenshot: " + dest.toAbsolutePath());
            }
        } catch (Exception error) {
            System.err.println("Could not capture failure screenshot: " + error.getMessage());
        } finally {
            if (driver != null) driver.quit();
        }
    }
    protected String requireDemoEnv(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) throw new org.testng.SkipException("Set " + name + " in your local environment or CI secrets to run authenticated tests");
        return value;
    }
}

