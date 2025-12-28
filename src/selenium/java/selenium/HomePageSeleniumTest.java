package com.example.careermanagement.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageSeleniumTest extends BaseSeleniumTest {

    @Test
    public void whenHomePageLoaded_thenTitleIsCorrect() {
        // 1. Ana sayfaya git
        driver.get(getBaseUrl());
        
        // 2. Sayfa başlığını kontrol et
        String pageTitle = driver.getTitle();
        assertThat(pageTitle).contains("Career Management");
        
        // 3. Sayfanın yüklenmesini bekle
        WebElement header = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))
        );
        
        // 4. Başlık metnini kontrol et
        assertThat(header.getText()).contains("Welcome to Career Management System");
    }
}
