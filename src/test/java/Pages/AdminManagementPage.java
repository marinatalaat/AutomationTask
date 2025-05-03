package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static Helpers.Utils.ClickOnElement;
import static Helpers.Utils.waitVisibilityOfElement;

public class AdminManagementPage {
    //testM123456789

    // By Locators and driver
    private WebDriver driver;
    private WebDriverWait wait;
    private By recordsFields = By.cssSelector(".oxd-table-card");
    private final By addRecordBtn = By.cssSelector(".orangehrm-header-container > button");
    private final By userRoleFieldArrow = By.xpath("//*[contains(text(),'User Role')]/../..//i");
    private final By fieldDropDownList = By.xpath("//div[@role='listbox']");
    private final By employeeNameField = By.xpath("//*[contains(text(),'Employee Name')]/../..//input");
    private final By statusField = By.xpath("//*[contains(text(),'Status')]/../..//i");
    private final By usernameField = By.xpath("//*[contains(text(),'Username')]/../..//input");
    private final By passwordField = By.xpath("//*[contains(text(),'Password')]/../..//input");
    private final By confirmPasswordField = By.xpath("//*[contains(text(),'Confirm Password')]/../..//input");
    private final By actionBtn = By.xpath("//button[@type='submit']");
    private final By deleteBtn = By.cssSelector(".bi-trash");
    private final By confirmDeleteAction = By.cssSelector(".oxd-button--label-danger");
    private final By resetBtn = By.xpath("//div[@class='oxd-form-actions']//button[@type='button']");



    // Constructor
    public AdminManagementPage(WebDriver driver) {
        this.driver= driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public int returnNumberOfRecords(){
        waitVisibilityOfElement(wait, recordsFields);
        System.out.println("num func:  " + driver.findElements(recordsFields).size());
        return driver.findElements(recordsFields).size();
    }

    public AdminManagementPage addNewRecord(String userRole, String employeeName, String status, String username, String password){
        waitVisibilityOfElement(wait, addRecordBtn);

        ClickOnElement(driver, wait, addRecordBtn );

        waitVisibilityOfElement(wait, employeeNameField);
        driver.findElement(employeeNameField).sendKeys(employeeName);
        ClickOnElement(driver, wait, By.xpath("//span[contains(text(),'"+ employeeName +"')]"));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);

        ClickOnElement(driver, wait, userRoleFieldArrow);
        driver.findElement(By.xpath("//span[contains(text(),'"+ userRole +"')]")).click();

        ClickOnElement(driver, wait, statusField);
        driver.findElement(By.xpath("//span[contains(text(),'"+ status +"')]")).click();

        ClickOnElement(driver, wait, actionBtn);

        return this;
    }

    public void checkTheRecordsIncreased(int oldRecordsNum){
        waitVisibilityOfElement(wait, recordsFields);

        System.out.println("old:  " + oldRecordsNum  +  "  new:  " + driver.findElements(recordsFields).size());
        Assert.assertTrue(driver.findElements(recordsFields).size() > oldRecordsNum);
    }


    public AdminManagementPage searchForAddedRecord(String userName, String userRole, String employeeName, String status){
        waitVisibilityOfElement(wait, usernameField);

        driver.findElement(usernameField).sendKeys(userName);
        ClickOnElement(driver, wait, userRoleFieldArrow);
        driver.findElement(By.xpath("//span[contains(text(),'"+ userRole +"')]")).click();

        ClickOnElement(driver, wait, statusField);
        driver.findElement(By.xpath("//span[contains(text(),'"+ status +"')]")).click();

        driver.findElement(employeeNameField).sendKeys(employeeName);
        ClickOnElement(driver, wait, By.xpath("//span[contains(text(),'"+ employeeName +"')]"));

        ClickOnElement(driver, wait, actionBtn);

        waitVisibilityOfElement(wait, recordsFields);
        Assert.assertEquals(driver.findElements(recordsFields).size(), 1);

        return this;
    }

    public AdminManagementPage deleteARecord(){
        ClickOnElement(driver, wait, deleteBtn);
        waitVisibilityOfElement(wait, confirmDeleteAction);
        ClickOnElement(driver, wait, confirmDeleteAction);
        ClickOnElement(driver, wait, resetBtn);
        return this;
    }

    public void checkTheRecordsDecreased(int oldRecordsNum){
        waitVisibilityOfElement(wait, recordsFields);

        System.out.println("old:  " +  oldRecordsNum +  "  new:  " + driver.findElements(recordsFields).size());
        Assert.assertTrue(driver.findElements(recordsFields).size() < oldRecordsNum);
    }

}
