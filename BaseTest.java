package com.castro.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.Collections;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // 1. הגדרת פרופיל קבוע (כדי שישמור את החיבור לאחר שתתחבר פעם אחת)
        String profilePath = System.getProperty("user.dir") + File.separator + "AutomationProfile";
        options.addArguments("user-data-dir=" + profilePath);
        options.addArguments("--profile-directory=Default");

        // 2. עקיפת מנגנון הזיהוי של גוגל (הפיכת הדפדפן לאנונימי ואנושי)
        options.addArguments("--disable-blink-features=AutomationControlled"); // מעלים את הדגל שמספר לסלניום שהוא בוט
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // מעלים את הכיתוב "Chrome is being controlled by automated test software"
        options.setExperimentalOption("useAutomationExtension", false);

        // 3. הוספת User-Agent אנושי רגיל
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        // פתיחת הדפדפן עם ההגדרות החדשות
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}