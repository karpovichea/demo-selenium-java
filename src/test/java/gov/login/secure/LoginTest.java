package gov.login.secure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    @Test
    @DisplayName("Тест: пустой емейл и пароль")
    public void testSignInWithEmptyEmailAndPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://secure.login.gov/");

        WebElement buttonSignInWebElement = driver.findElement(By.xpath("html/body/main/div/form/lg-captcha-submit-button/lg-spinner-button/button"));
        buttonSignInWebElement.click();

        String expectedEmptyFieldErrorMessage = "This field is required";
        String actualEmptyEmailErrorMessage = driver.findElement(By.xpath("/html/body/main/div/form/lg-validated-field/div/div")).getText();
        String actualEmptyPasswordErrorMessage = driver.findElement(By.xpath("/html/body/main/div/form/lg-password-toggle/lg-validated-field/div/div")).getText();

        Assertions.assertEquals(expectedEmptyFieldErrorMessage, actualEmptyEmailErrorMessage);
        Assertions.assertEquals(expectedEmptyFieldErrorMessage, actualEmptyPasswordErrorMessage);
    }

    @Test
    @DisplayName("Тест: пустой пароль")
    public void testSignInWithEmptyPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://secure.login.gov/");

        WebElement inputEmailAddressWebElement = driver.findElement(By.xpath("/html/body/main/div/form/lg-validated-field/div/input"));
        inputEmailAddressWebElement.sendKeys("testA@test.com");

        WebElement buttonSignInWebElement = driver.findElement(By.xpath("html/body/main/div/form/lg-captcha-submit-button/lg-spinner-button/button"));
        buttonSignInWebElement.click();

        String expectedEmptyFieldErrorMessage = "This field is required";
        String actualEmptyPasswordErrorMessage = driver.findElement(By.xpath("/html/body/main/div/form/lg-password-toggle/lg-validated-field/div/div")).getText();

        Assertions.assertEquals(expectedEmptyFieldErrorMessage, actualEmptyPasswordErrorMessage);
    }

    @Test
    @DisplayName("Тест: пустой емейл")
    public void testSignInWithEmptyEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://secure.login.gov/");

        WebElement inputPasswordWebElement = driver.findElement(By.xpath("/html/body/main/div/form/lg-password-toggle/lg-validated-field/div/input"));
        inputPasswordWebElement.sendKeys("1q2w#E4r");

        WebElement buttonSignInWebElement = driver.findElement(By.xpath("html/body/main/div/form/lg-captcha-submit-button/lg-spinner-button/button"));
        buttonSignInWebElement.click();

        String expectedEmptyFieldErrorMessage = "This field is required";
        String actualEmptyEmailErrorMessage = driver.findElement(By.xpath("/html/body/main/div/form/lg-validated-field/div/div")).getText();

        Assertions.assertEquals(expectedEmptyFieldErrorMessage, actualEmptyEmailErrorMessage);
    }

    @Test
    @DisplayName("Тест: неверные учетные данные")
    public void testSignInWithWrongCredentials() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://secure.login.gov/");

        WebElement inputEmailAddressWebElement = driver.findElement(By.xpath("html/body/main/div/form/lg-validated-field/div/input"));
        inputEmailAddressWebElement.sendKeys("testB@test.com");

        WebElement inputPasswordWebElement = driver.findElement(By.xpath("/html/body/main/div/form/lg-password-toggle/lg-validated-field/div/input"));
        inputPasswordWebElement.sendKeys("a1S@d3F$");

        WebElement buttonSignInWebElement = driver.findElement(By.xpath("html/body/main/div/form/lg-captcha-submit-button/lg-spinner-button/button"));
        buttonSignInWebElement.click();

        String expectedWrongCredentialsErrorMessage = "The email or password you’ve entered is wrong. Try resetting your password.";
        String actualWrongCredentialsErrorMessage = driver.findElement(By.xpath("/html/body/main/div/div[1]/div")).getText();

        Assertions.assertEquals(expectedWrongCredentialsErrorMessage, actualWrongCredentialsErrorMessage);
    }
}