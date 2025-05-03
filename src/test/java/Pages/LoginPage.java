package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Helpers.Utils.ClickOnElement;
import static Helpers.Utils.waitVisibilityOfElement;

public class LoginPage {

    // By Locators and driver
    private WebDriver driver;
    private WebDriverWait wait;
    private By userNameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginBtn = By.cssSelector(".orangehrm-login-button");


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver= driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public LoginPage navigateToHomepage(String URL){
        driver.navigate().to(URL);
        return this;
    }

    public HomePage loginWithValidCredentials(String userName, String password){
        waitVisibilityOfElement(wait, userNameField);
        driver.findElement(userNameField).sendKeys(userName);

        waitVisibilityOfElement(wait, passwordField);
        driver.findElement(passwordField).sendKeys(password);

        ClickOnElement(driver, wait, loginBtn);
        return new HomePage(driver);
    }

}
