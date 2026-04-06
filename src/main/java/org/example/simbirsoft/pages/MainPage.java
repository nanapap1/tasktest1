package org.example.simbirsoft.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Comparator;
import java.util.List;

public class MainPage {

    private WebDriver driver;

    @FindBy(xpath = "//label[contains(text(),'Name')]//input")
    private WebElement nameInput;

    @FindBy(xpath = "//label[contains(text(),'Password')]//input")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[contains(@name,'email')]")
    private WebElement emailInput;

    @FindBy(xpath = "//textarea[contains(@name, 'message')]")
    private WebElement messageInput;

    @FindBy(xpath = "//select[contains(@name, 'automation')]")
    private WebElement selectAutomation;
    private Select selectChoice = null;

    @FindBy(xpath = "//input[contains(@name, 'fav_drink')]")
    private List<WebElement> favDrinks;

    @FindBy(xpath = "//input[contains(@name, 'fav_color')]")
    private List<WebElement> favColor;

    @FindBy(xpath = "//label[contains(text(), 'Automation tools')]//ul//li")
    private List<WebElement> toolsAutomation;

    @FindBy(xpath = "//button[contains(text(), 'Submit')]")
    private WebElement submitButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
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
        selectChoice.selectByVisibleText(choice);
        return this;
    }

    public MainPage setFavDrinks(List<String> drinks) {
        for(String drink : drinks)
            for(WebElement one : favDrinks) {
                if (one.getAttribute("value").equals(drink))
                    one.click();
            }
        return this;
    }


    public MainPage setFavColor(String color) {
        for(WebElement one : favColor) {
            if (one.getAttribute("value").equals(color))
                one.click();
        }
        return this;
    }

    public MainPage clickSubmitButton() {
        this.submitButton.click();
        return this;
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
