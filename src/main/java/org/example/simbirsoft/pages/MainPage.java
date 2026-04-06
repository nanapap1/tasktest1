package org.example.simbirsoft.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

    @FindAll({@FindBy(xpath = "//input[contains(@name, 'fav_drink')]")})
    private List<WebElement> favDrinks;

    @FindAll({@FindBy(xpath = "//input[contains(@name, 'fav_color')]")})
    private List<WebElement> favColors;

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


    public MainPage setFavColors(String color) {
        for(WebElement one : favColors) {
            if (one.getAttribute("value").equals(color))
                one.click();
        }
        return this;
    }

    public MainPage clickSubmitButton() {
        this.submitButton.click();
        return this;
    }

    public MainPage fill(String nameInput, String passwordInput, String emailInput, String messageInput, String selectAutomation, String selectChoice, List<String> favDrinks, String favColors) {
        return this.setNameInput(nameInput)
                .setPasswordInput(passwordInput).setEmailInput(emailInput)
                .setMessageInput(messageInput).setSelectAutomation(selectAutomation)
                .setFavDrinks(favDrinks).setFavColors(favColors)
                .clickSubmitButton();
    }

}
