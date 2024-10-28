package by.mall.tests;

import by.mall.pages.LoginMessage;
import by.mall.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private static final String PHONE_NUMBER = "293334455";
    private static final String PASSWORD = "123456";

    @Test
    @DisplayName("Тест: пустой номер телефона и пароль")
    public void testSignInWithEmptyPhoneNumberAndPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://emall.by/login/password");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_PHONE_NUMBER_AND_PASSWORD_FIELDS, loginPage.getWrongCredentialsErrorMessage(), "Неверный текст ошибки валидации для пустого номера телефона и пароля");
    }

    @Test
    @DisplayName("Тест: пустой номер телефона")
    public void testSignInWithEmptyPhoneNumber() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://emall.by/login/password");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_PHONE_NUMBER_FIELD, loginPage.getWrongCredentialsErrorMessage(), "Неверный текст ошибки валидации для пустого номера телефона");
    }

    @Test
    @DisplayName("Тест: пустой пароль")
    public void testSignInWithEmptyPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://emall.by/login/password");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin(PHONE_NUMBER);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_PASSWORD_FIELD, loginPage.getWrongCredentialsErrorMessage(), "Неверный текст ошибки валидации для пустого пароля");
    }

    @Test
    @DisplayName("Тест: неверные учетные данные")
    public void testSignInWithWrongCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://emall.by/login/password");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin(PHONE_NUMBER);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.WRONG_CREDENTIALS, loginPage.getWrongCredentialsErrorMessage(), "Неверный текст ошибки при невалидных учетных данных");
    }
}
