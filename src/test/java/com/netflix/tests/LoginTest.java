package com.netflix.tests;

import com.netflix.pages.LoginMessage;
import com.netflix.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginTest {
    private static final String LOGIN = "john@gmal.com";
    private static final String PASSWORD = "123456";
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.netflix.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Тест: пустой логин и пароль")
    public void testSignInWithEmptyLoginAndPassword() {
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_LOGIN_FIELD, loginPage.getLoginFieldErrorMessage(), "Неверный текст ошибки валидации для пустого логина");
        Assertions.assertEquals(LoginMessage.EMPTY_PASSWORD_FIELD, loginPage.getPasswordFieldErrorMessage(), "Неверный текст ошибки валидации для пустого пароля");
    }

    @Test
    @DisplayName("Тест: пустой логин")
    public void testSignInWithEmptyLogin() {
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_LOGIN_FIELD, loginPage.getLoginFieldErrorMessage(), "Неверный текст ошибки валидации для пустого логина");
    }

    @Test
    @DisplayName("Тест: пустой пароль")
    public void testSignInWithEmptyPassword() {
        loginPage.enterLogin(LOGIN);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.EMPTY_PASSWORD_FIELD, loginPage.getPasswordFieldErrorMessage(), "Неверный текст ошибки валидации для пустого пароля");
    }

    @Test
    @DisplayName("Тест: неверные учетные данные")
    public void testSignInWithWrongCredentials() {
        loginPage.enterLogin(LOGIN);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignInButton();

        Assertions.assertEquals(LoginMessage.WRONG_CREDENTIALS + LOGIN, loginPage.getWrongCredentialsErrorMessage(), "Неверный текст ошибки при невалидных учетных данных");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
