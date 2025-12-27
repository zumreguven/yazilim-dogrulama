package com.example.careermanagement.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.assertj.core.api.Assertions.assertThat;

public class CareerGoalSeleniumTest extends BaseSeleniumTest {

    @Test
    public void whenCreateNewCareerGoal_thenSuccess() {
        // 1. Giriş yap
        login();
        
        // 2. Kariyer hedefleri sayfasına git
        WebElement careerGoalsLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.linkText("Kariyer Hedeflerim"))
        );
        careerGoalsLink.click();
        
        // 3. Yeni hedef oluştur butonuna tıkla
        WebElement newGoalButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("new-goal-button"))
        );
        newGoalButton.click();
        
        // 4. Formu doldur
        WebElement titleField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("goal-title"))
        );
        WebElement descriptionField = driver.findElement(By.id("goal-description"));
        Select prioritySelect = new Select(driver.findElement(By.id("goal-priority")));
        WebElement targetDateField = driver.findElement(By.id("goal-target-date"));
        WebElement saveButton = driver.findElement(By.cssSelector("button[type='submit']"));
        
        titleField.sendKeys("Yazılım Mimarı Olmak");
        descriptionField.sendKeys("Önümüzdeki 2 yıl içinde yazılım mimarı pozisyonuna yükselmek");
        prioritySelect.selectByVisibleText("Yüksek");
        targetDateField.sendKeys("31/12/2025");
        
        // 5. Formu gönder
        saveButton.click();
        
        // 6. Başarı mesajını kontrol et
        WebElement successMessage = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("alert-success"))
        );
        
        assertThat(successMessage.getText()).contains("Kariyer hedefi başarıyla oluşturuldu");
        
        // 7. Oluşturulan hedefin listede olduğunu doğrula
        WebElement goalTitle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Yazılım Mimarı Olmak')]"))
        );
        
        assertThat(goalTitle.isDisplayed()).isTrue();
    }
    
    private void login() {
        driver.get(getBaseUrl() + "/login");
        
        WebElement usernameField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        
        usernameField.sendKeys("testuser");
        passwordField.sendKeys("testpass");
        loginButton.click();
        
        // Giriş sonrası yönlendirmeyi bekle
        wait.until(ExpectedConditions.urlContains("/dashboard"));
    }
}
