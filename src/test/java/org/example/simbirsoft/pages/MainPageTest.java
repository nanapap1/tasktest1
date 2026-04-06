package org.example.simbirsoft.pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
    WebDriver driver;
    Wait<WebDriver> wait;

    @BeforeClass
    public void driverSetup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeSteps() {
        driver.get("https://practice-automation.com/form-fields/");
    }

    @Test
    public void firstTest() {
        MainPage mainPage = new MainPage(driver,wait);
        mainPage.fill("Mark","itisstrongpass",
                List.of("Milk","Coffee"),"Yellow", "Yes",
                "name@example.com",
                mainPage.countTools() + " " + mainPage.largestTool()
        );
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        AssertJUnit.assertEquals("Message received!", alert.getText());
    }

    @Test
    public void secondTest() {
        MainPage mainPage = new MainPage(driver,wait);
        mainPage.setNameInput("Mark").clickSubmitButton();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        AssertJUnit.assertEquals("Message received!", alert.getText());
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void thirdTest() {
        MainPage mainPage = new MainPage(driver,wait);
        mainPage.setPasswordInput("asdsadsad").setFavDrinks(List.of("Water","Milk","Coffee"))
                .setFavColor("Red").setSelectAutomation("No")
                .setEmailInput("markbednyagin@yandex.ru").setMessageInput("Hello,world!")
                .clickSubmitButton();
        try {
            wait.until(ExpectedConditions.alertIsPresent());
        }
        catch (Exception error) {
            throw error;
        }
    }

    @AfterMethod
    public void afterAlert() {
        try{
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }
        catch (TimeoutException e) {
            return;
        }
    }
    @AfterClass
    public void afterAll() {
        driver.close();
    }

}
