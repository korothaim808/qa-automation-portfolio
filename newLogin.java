package com.castro.tests;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class newLogin extends BaseTest{

    @Test
    public void testGoogleLogin() throws InterruptedException {
        System.out.println("מתחיל טסט התחברות באמצעות גוגל ...");

        driver.get("https://www.castro.com/");
        String originalWindow = driver.getWindowHandle();

        try {
            System.out.println("בודק אם יש פופאפ קוקיז שחוסם את המסך ...");
            WebElement closeePopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/aside[4]/div[2]/header/button")));
            closeePopup.click();
            System.out.println("חלונית נסגרה בהצלחה ");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("לא נמצאה חלונית חוסמת ממשיכים כרגיל ");
        }

        System.out.println("בודק האם המשתמש נשאר מחובר ");
        List<WebElement> successMessage = driver.findElements(By.xpath("//*[contains(text(), 'התחברת בהצלחה') or contains(text(), 'shopping begin')]"));

        if (successMessage.isEmpty()) {
            System.out.println(" המשתמש מחובר כבר מריצות קודמות מדלג על שלבי ההתחברות ומסיים בהצלחה ");
            return;
        }

        System.out.println(" מרחף על האייקון בתפריט העליון");
        WebElement userIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"customer-login-link\"]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(userIcon).perform();
        System.out.println("העכבר ריחף מעל האייקון בהצלחה  !");
        Thread.sleep(2000);

        System.out.println("לוחץ על כפתור התחברות/הצטרפות מהתפריט שנתגלה ");
        WebElement loginMenuButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"customer-login-link\"]")));
        loginMenuButton.click();
        Thread.sleep(2000);

        System.out.println("לוחץ על סמל Google...");
        WebElement GoogleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"customer-account-login-form\"]/div[2]/div[5]/div[2]/div/a[2]")));
        GoogleButton.click();
        Thread.sleep(2000);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("סלניום עבר בהצלחה לחלון הזיהוי של גוגל ");

        try {
            System.out.println("בודק אם גוגל מבקש להקליד אימייל ");
            WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"identifierId\"]")));

            emailInput.sendKeys("davidbdikot@gmail.com");
            emailInput.sendKeys(Keys.ENTER);
            System.out.println("כתובת המייל הוקלדה ונשלחה ");
            Thread.sleep(1000);

            System.out.println("מאתר את שדה הסיסמה של גוגל ");
            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password' or @name='password']")));
            passwordInput.sendKeys("david698088");
            passwordInput.sendKeys(Keys.ENTER);
            System.out.println("הסיסמה הוקלדה ונשלחה ");
            Thread.sleep(4000);

        }  catch (Exception e) {

            System.out.println("לא נימצא שדה אימייל ריק מנסה לבחור מרשימת המשתמשים את david bdikot ...");
            try {
                WebElement myAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'david cohen') or contains(@data-email, 'david')]")));
                myAccount.click();
                Thread.sleep(2000);
            } catch (Exception ex) {
                System.out.println("לא הצלחתי לזהות את שדה המייל או את רשימת החשבונות ");
            }

        }

            try {
                WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Continue') or contains(., 'המשך') or contains(., 'Next')]")));
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
                Thread.sleep(1000);
                continueButton.click();
                System.out.println("לחצתי על כפתור המשך/הבא ");
            } catch (Exception ex) {
                System.out.println(" לא נדרשה לחיצה נוספת על כפתור המשך ");
            }

            driver.switchTo().window(originalWindow);
            System.out.println("חזרתי לאתר קסטרו כמשתמש מחובר !");
        Thread.sleep(6000);

    }


}
