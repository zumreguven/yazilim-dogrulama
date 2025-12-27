package com.example.careermanagement.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSeleniumTest extends BaseSeleniumTest {

    @Test
    public void whenValidCredentials_thenLoginSuccess() {
        // 1. Giriş sayfasına git
        driver.get(getBaseUrl() + "/login");
        
        // 2. Form alanlarını doldur
        WebElement usernameField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        
        // 3. Test kullanıcısı ile giriş yap
        usernameField.sendKeys("testuser");
        passwordField.sendKeys("testpass");
        loginButton.click();
        
        // 4. Başarılı giriş sonrası yönlendirmeyi kontrol et
        WebElement welcomeMessage = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("welcome-message"))
        );
        
        assertThat(welcomeMessage.getText()).contains("Hoş geldiniz, testuser");
    }
}
