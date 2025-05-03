package TestCases;

import Helpers.PropertiesLoader;
import Pages.AdminManagementPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

import static Helpers.Utils.takeScreenShot;

public class AdminRecordsManagementTests {


    public WebDriver driver;
    String URL, userName, password, UserRole, EmployeeName, Status, UsernameData, PasswordData;
    int recordsNumber = 0;

    @Test()
    public void addNewRecord(){
        recordsNumber = new LoginPage(driver).loginWithValidCredentials(userName, password).
                clickOnAdminLink()
                .returnNumberOfRecords();


        new AdminManagementPage(driver)
                .addNewRecord(UserRole, EmployeeName, Status, UsernameData, PasswordData)
                .checkTheRecordsIncreased(recordsNumber);

        recordsNumber = new AdminManagementPage(driver)
                .returnNumberOfRecords();

        new AdminManagementPage(driver)
                .searchForAddedRecord(UsernameData, UserRole, EmployeeName, Status)
                .deleteARecord()
                .checkTheRecordsDecreased(recordsNumber);
    }


    // Configurations
    @BeforeClass
    public void startDriver() {
        String FilePath="ConfigData/Data.properties";
        URL = PropertiesLoader.readPropertyFile(FilePath).getProperty("URL");
        userName = PropertiesLoader.readPropertyFile(FilePath).getProperty("userName");
        password = PropertiesLoader.readPropertyFile(FilePath).getProperty("password");
        UserRole = PropertiesLoader.readPropertyFile(FilePath).getProperty("UserRole");
        EmployeeName = PropertiesLoader.readPropertyFile(FilePath).getProperty("EmployeeName");
        Status = PropertiesLoader.readPropertyFile(FilePath).getProperty("Status");
        UsernameData = PropertiesLoader.readPropertyFile(FilePath).getProperty("UsernameData");
        PasswordData = PropertiesLoader.readPropertyFile(FilePath).getProperty("PasswordData");
    }

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        new LoginPage(driver).navigateToHomepage(URL);
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE) {
            takeScreenShot(driver, method);
        }
        driver.quit();
    }

}
