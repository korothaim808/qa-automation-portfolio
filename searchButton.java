package com.castro.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class searchButton {

    public  static void main(String[] args)
    {
        WebDriver driver = new ChromeDriver();

        try {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.castro.com/");
        System.out.println("החיבור הצליח ! כותרת העמוד היא: " + driver.getTitle());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            System.out.println("מחפש חלון קופץ...");
            WebElement closeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/aside[4]/div[2]/header/button"))
            );

            closeButton.click();
            System.out.println("חלון הקוקיז נסגר בהצלחה : ");
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("החלון לא הופיע ממשיכים בטסט ");
        }
            Thread.sleep(2000);
            System.out.println("מאתר את שדה החיפוש ... ");
            WebElement searchButton = driver.findElement(By.xpath("/html/body/div[5]/header/div/div/div[3]/div/div/div[1]/div[2]/div/div/a"));
            searchButton.click();

//            System.out.println("מאתר את שדה ההקלדה ומקליד את המוצר");

            WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"header-search-input\"]"));
            String productTosearch = "חולצה";
            searchInput.sendKeys(productTosearch);
            Thread.sleep(1000);

            WebElement searchSubmitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            searchSubmitButton.click();
//            searchInput.sendKeys(productTosearch);
//            searchInput.sendKeys(org.openqa.selenium.Keys.ENTER);
            System.out.println("בוצע חיפוש עבור המוצר: " + productTosearch);

        }  catch (Exception e) {

            System.out.println("אירעה שגיאה בהרצת הטסט");
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println(" הטסט יסתיים ");
        }




    }



}
