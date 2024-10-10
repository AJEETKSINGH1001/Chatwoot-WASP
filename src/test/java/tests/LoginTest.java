package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LoginTest extends BaseTest {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Chatwoot WASP";
    protected AndroidDriver driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    
    // ExtentReports variables
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupExtent() {
        // Set up the ExtentReports and HTML Reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/LoginTestReport.html");
        sparkReporter.config().setDocumentTitle("Login Test Report");
        sparkReporter.config().setReportName("Login Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Ajeet");
    }
    
    @SuppressWarnings("deprecation")
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability(MobileCapabilityType.UDID, "084113125P054404");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.gamechange.wasp");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.bitnudge.ime.parent.view.activities.SplashActivity");
        
        // Initialize driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test(priority = 1)
    public void testValidLogin() {
        test = extent.createTest("testValidLogin", "Test for valid login credentials");
        try {
            performLogin("parminder@gamechangesns.com", "Qwerty@123");
           // WebElement welcomeMessage = driver.findElement(By.id("com.gamechange.wasp.shopkey:id/titleTV")); // Update this ID to match the app's element ID for verification
           // Assert.assertTrue(welcomeMessage.isDisplayed(), "Login successful");
            test.pass("Valid login test passed successfully");
        } catch (Exception e) {
            test.fail("Valid login test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testInvalidEmail() {
        test = extent.createTest("testInvalidEmail", "Test for invalid email format");
        try {
            performLogin("invalid-email-format", "Qwerty@123");
            WebElement errorMessage = driver.findElement(By.xpath("//android.widget.Toast[1]"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message for invalid email should be displayed");
            test.pass("Invalid email test passed");
        } catch (Exception e) {
            test.fail("Invalid email test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testIncorrectPassword() {
        test = extent.createTest("testIncorrectPassword", "Test for incorrect password");
        try {
            performLogin("parminder@gamechangesns.com", "wrongPassword");
            Assert.assertTrue(isErrorMessageDisplayed("Invalid login credentials. Please try again."), "Error message should be displayed for incorrect password");
            test.pass("Incorrect password test passed");
        } catch (Exception e) {
            test.fail("Incorrect password test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 4)
    public void testEmptyFields() {
        test = extent.createTest("testEmptyFields", "Test for empty email and password fields");
        try {
            performLogin("", "");
            Assert.assertTrue(isErrorMessageDisplayed("Please Enter a valid Email"), "Error message should be displayed for empty fields");
            test.pass("Empty fields test passed");
        } catch (Exception e) {
            test.fail("Empty fields test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 5)
    public void testNonRegisteredEmail() {
        test = extent.createTest("testNonRegisteredEmail", "Test for non-registered email");
        try {
            performLogin("nonregistered@example.com", "Qwerty@123");
            Assert.assertTrue(isErrorMessageDisplayed("Invalid login credentials. Please try again."), "Error message should be displayed for non-registered email");
            test.pass("Non-registered email test passed");
        } catch (Exception e) {
            test.fail("Non-registered email test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    private void performLogin(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait for email field and input
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.gamechange.wasp.shopkey:id/emailAddressEditText")));
        emailField.clear(); // Clear field before entering new data
        emailField.sendKeys(email);

        // Wait for password field and input
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.gamechange.wasp.shopkey:id/etPasswordEditText")));
        passwordField.clear(); // Clear field before entering new data
        passwordField.sendKeys(password);

        // Wait for login button and click
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.gamechange.wasp.shopkey:id/btnLogin")));
        loginButton.click();
    }

    private boolean isErrorMessageDisplayed(String expectedError) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Modify this selector to locate the actual error message on the screen
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.gamechange.wasp.shopkey:id/errorMessage"))); // Change to your actual ID
            return errorMessage.getText().contains(expectedError);
        } catch (Exception e) {
            return false; // Return false if error message not found
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush(); // Make sure this is called to write the report
    }

    @AfterSuite
    public void tearDownExtent() {
        extent.flush(); // Write the report at the end of the suite
    }
}
