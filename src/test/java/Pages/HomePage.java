package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Helpers.Utils.waitVisibilityOfElement;

public class HomePage {

    // By Locators and driver
    private WebDriver driver;
    private WebDriverWait wait;
    private By adminLink = By.xpath("//a[contains(@href, 'viewAdminModule')]");


    // Constructor
    public HomePage(WebDriver driver) {
        this.driver= driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public AdminManagementPage clickOnAdminLink(){
        waitVisibilityOfElement(wait, adminLink);
        driver.findElement(adminLink).click();
        return new AdminManagementPage(driver);
    }
}
