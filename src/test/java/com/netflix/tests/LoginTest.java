package com.netflix.tests;

import com.netflix.pages.LoginMessage;
import com.netflix.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private static final String LOGIN = "john@gmal.com";
    private static final String PASSWORD = "123456";

    @Test
    @DisplayName("Тест: пустой логин и пароль")
    public void testSignInWithEmptyLoginAndPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.netflix.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_LOGIN_FIELD, loginPage.getLoginFieldErrorMessage(), "Неверный текст ошибки валидации для пустого логина");
        Assertions.assertEquals(LoginMessage.EMPTY_PASSWORD_FIELD, loginPage.getPasswordFieldErrorMessage(), "Неверный текст ошибки валидации для пустого пароля");
    }

    @Test
    @DisplayName("Тест: пустой логин")
    public void testSignInWithEmptyLogin() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.netflix.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_LOGIN_FIELD, loginPage.getLoginFieldErrorMessage(), "Неверный текст ошибки валидации для пустого логина");
    }

    @Test
    @DisplayName("Тест: пустой пароль")
    public void testSignInWithEmptyPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.netflix.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin(LOGIN);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_PASSWORD_FIELD, loginPage.getPasswordFieldErrorMessage(), "Неверный текст ошибки валидации для пустого пароля");
    }

    @Test
    @DisplayName("Тест: неверные учетные данные")
    public void testSignInWithWrongCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.netflix.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin(LOGIN);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.WRONG_CREDENTIALS + LOGIN, loginPage.getWrongCredentialsErrorMessage(), "Неверный текст ошибки при невалидных учетных данных");
    }
}
