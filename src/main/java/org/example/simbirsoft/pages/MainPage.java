package org.example.simbirsoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.Comparator;
import java.util.List;

public class MainPage {

    private WebDriver driver;
    private Wait<WebDriver> wait;

    @FindBy(xpath = "//label[contains(text(),'Name')]//input")
    private WebElement nameInput;

    @FindBy(xpath = "//label[contains(text(),'Password')]//input")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[contains(@name,'email')]")
    private WebElement emailInput;

    @FindBy(id = "message")
    private WebElement messageInput;

    @FindBy(xpath = "//select[contains(@name, 'automation')]")
    private WebElement selectAutomation;
    private Select selectChoice = null;

    @FindBy(xpath = "//label[contains(@for, 'drink')]")
    private List<WebElement> favDrinks;

    @FindBy(xpath = "//label[contains(@for, 'color')]")
    private List<WebElement> favColors;

    @FindBy(xpath = "//form//ul//li")
    private List<WebElement> toolsAutomation;

    @FindBy(css = "button[data-testid='submit-btn'].custom_btn.btn_hover")
    private WebElement submitButton;

    public MainPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);

    }

    public MainPage setNameInput(String nameInput) {
        this.nameInput.sendKeys(nameInput);
        return this;
    }

    public MainPage setPasswordInput(String passwordInput) {
        this.passwordInput.sendKeys(passwordInput);
        return this;
    }

    public MainPage setEmailInput(String emailInput) {
        this.emailInput.sendKeys(emailInput);
        return this;
    }

    public MainPage setMessageInput(String messageInput) {
        this.messageInput.sendKeys(messageInput);
        return this;
    }

    public MainPage setSelectAutomation(String choice) throws NoSuchElementException {
        if(this.selectChoice == null) {
            selectChoice = new Select(selectAutomation);
        }
        wait.until(x -> selectAutomation.isDisplayed());
        selectChoice.selectByVisibleText(choice);
        return this;
    }

    public MainPage setFavDrinks(List<String> drinks) {
        for(String drink : drinks)
            for(WebElement one : favDrinks) {
                if (one.getText().equals(drink)) {
                        new Actions(driver).moveToElement(one).click().perform();
                }
            }
        return this;
    }


    public MainPage setFavColor(String color) {
        for(WebElement one : favColors) {
            if (one.getText().equals(color)) {
                new Actions(driver).moveToElement(one).click().perform();
            }
        }
        return this;
    }

    public MainPage clickSubmitButton() {
        new Actions(driver).moveToElement(submitButton).click().perform();
        return this;
    }

    public String getValidationMessage() {
        return nameInput.getAttribute("validationMessage");
    }

    public MainPage fill(String nameInput, String passwordInput, List<String> favDrinks, String favColor, String selectAutomation, String emailInput, String messageInput) {
        return this.setNameInput(nameInput)
                .setPasswordInput(passwordInput).setEmailInput(emailInput)
                .setMessageInput(messageInput).setSelectAutomation(selectAutomation)
                .setFavDrinks(favDrinks).setFavColor(favColor)
                .clickSubmitButton();
    }

    public int countTools() {
        return toolsAutomation.size();
    }

    public String largestTool() {
        return toolsAutomation.stream().max(Comparator.comparingInt(el -> el.getText().length())).get().getText();
    }

}
