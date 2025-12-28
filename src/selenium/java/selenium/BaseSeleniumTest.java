package com.example.careermanagement.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public abstract class BaseSeleniumTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("selenium.headless", "true"))) {
            options.addArguments(
                    "--headless",
                    "--disable-gpu",
                    "--window-size=1920,1080",
                    "--no-sandbox",
                    "--disable-dev-shm-usage"
            );
        }

        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}