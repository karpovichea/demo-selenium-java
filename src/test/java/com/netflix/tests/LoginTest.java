package com.netflix.tests;

import com.netflix.pages.LoginMessage;
import com.netflix.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    @Test
    public void testSignInWithEmptyLoginAndPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.netflix.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_LOGIN_FIELD, loginPage.getLoginFieldErrorMessage(), "Неверный текст ошибки валидации для пустого логина");
        Assertions.assertEquals(LoginMessage.EMPTY_PASSWORD_FIELD, loginPage.getPasswordFieldErrorMessage(), "Неверный текст ошибки валидации для пустого пароля");
    }
}
