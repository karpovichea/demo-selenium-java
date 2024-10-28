package by.mall.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterLogin(String login) {
        WebElement loginField = driver.findElement(By.xpath(LoginXpath.PHONE_NUMBER_FIELD));
        loginField.sendKeys(login);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath(LoginXpath.PASSWORD_FIELD));
        passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        WebElement signInButton = driver.findElement(By.xpath(LoginXpath.SIGN_IN_BUTTON));
        signInButton.click();
    }

    public String getWrongCredentialsErrorMessage() {
        WebElement textMessage = driver.findElement(By.xpath(LoginXpath.WRONG_CREDENTIALS_ERROR_MESSAGE));
        return textMessage.getText();
    }
}
