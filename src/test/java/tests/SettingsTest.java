package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.SupportsRotation;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SettingsTest extends BaseTest {

    private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Set up desired capabilities for Appium
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        caps.setCapability(MobileCapabilityType.UDID, "084113125P054404");
        caps.setCapability("appPackage", "com.gamechange.wasp");
        caps.setCapability("appActivity", "com.bitnudge.ime.parent.view.activities.SplashActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        caps.setCapability("noReset", true);
        caps.setCapability("fullReset", false);

        // Initialize the Appium driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //Test scripts for testing settings screen of chatwoot-wasp
    
    @Test(priority = 1)
    public void verifyProfileDetails() {
        // Locate profile picture
        WebElement profilePicture = driver.findElement(By.id("com.example.yourapp:id/profile_picture"));
        Assert.assertTrue(profilePicture.isDisplayed(), "Profile picture is not displayed.");
        
        // Locate username
        WebElement username = driver.findElement(By.id("com.example.yourapp:id/username"));
        Assert.assertEquals(username.getText(), "Test", "Username is incorrect.");
        
        // Locate email
        WebElement email = driver.findElement(By.id("com.example.yourapp:id/user_email"));
        Assert.assertEquals(email.getText(), "parminder@gamechangesns.com", "Email is incorrect.");
    }
    @Test(priority = 2)
    public void verifyStatusOptions() {
        // Verify Online status
        WebElement onlineStatus = driver.findElement(By.id("com.example.yourapp:id/status_online"));
        onlineStatus.click();
        Assert.assertTrue(onlineStatus.isSelected(), "Online status is not selected.");

        // Verify Offline status
        WebElement offlineStatus = driver.findElement(By.id("com.example.yourapp:id/status_offline"));
        offlineStatus.click();
        Assert.assertTrue(offlineStatus.isSelected(), "Offline status is not selected.");

        // Verify Busy status
        WebElement busyStatus = driver.findElement(By.id("com.example.yourapp:id/status_busy"));
        busyStatus.click();
        Assert.assertTrue(busyStatus.isSelected(), "Busy status is not selected.");
    }

    @Test(priority = 3)
    public void verifyNavigationToAddAttribute() {
        WebElement addAttribute = driver.findElement(By.xpath("//android.widget.TextView[@text='Tambahkan Atribut']"));
        addAttribute.click();
        
        // Verify navigation to the Add Attribute screen
        WebElement addAttributeHeader = driver.findElement(By.id("com.example.yourapp:id/header_add_attribute"));
        Assert.assertTrue(addAttributeHeader.isDisplayed(), "Failed to navigate to the Add Attribute screen.");
    }
    @Test(priority = 4)
    public void verifyNavigationToTeam() {
        WebElement teamOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Tim']"));
        teamOption.click();
        
        // Verify navigation to the Team screen
        WebElement teamHeader = driver.findElement(By.id("com.example.yourapp:id/header_team"));
        Assert.assertTrue(teamHeader.isDisplayed(), "Failed to navigate to the Team screen.");
    }

    @Test(priority = 5)
    public void verifyNavigationToTemplate() {
        WebElement templateOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Template']"));
        templateOption.click();
        
        // Verify navigation to the Template screen
        WebElement templateHeader = driver.findElement(By.id("com.example.yourapp:id/header_template"));
        Assert.assertTrue(templateHeader.isDisplayed(), "Failed to navigate to the Template screen.");
    }

    @Test(priority = 6)
    public void verifyNavigationToLabel() {
        WebElement labelOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Label']"));
        labelOption.click();
        
        // Verify navigation to the Label screen
        WebElement labelHeader = driver.findElement(By.id("com.example.yourapp:id/header_label"));
        Assert.assertTrue(labelHeader.isDisplayed(), "Failed to navigate to the Label screen.");
    }
    @Test(priority = 7)
    public void verifyNavigationToCannedResponses() {
        WebElement cannedResponsesOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Canned Responses']"));
        cannedResponsesOption.click();
        
        // Verify navigation to the Canned Responses screen
        WebElement cannedResponsesHeader = driver.findElement(By.id("com.example.yourapp:id/header_canned_responses"));
        Assert.assertTrue(cannedResponsesHeader.isDisplayed(), "Failed to navigate to the Canned Responses screen.");
    }

    @Test(priority = 8)
    public void verifyNavigationToCampaigns() {
        WebElement campaignsOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Campaigns']"));
        campaignsOption.click();
        
        // Verify navigation to the Campaigns screen
        WebElement campaignsHeader = driver.findElement(By.id("com.example.yourapp:id/header_campaigns"));
        Assert.assertTrue(campaignsHeader.isDisplayed(), "Failed to navigate to the Campaigns screen.");
    }

    @Test(priority = 9)
    public void verifyNavigationToWABADashboard() {
        WebElement wabaDashboardOption = driver.findElement(By.xpath("//android.widget.TextView[@text='WABA Dashboard']"));
        wabaDashboardOption.click();
        
        // Verify navigation to the WABA Dashboard screen
        WebElement wabaDashboardHeader = driver.findElement(By.id("com.example.yourapp:id/header_waba_dashboard"));
        Assert.assertTrue(wabaDashboardHeader.isDisplayed(), "Failed to navigate to the WABA Dashboard screen.");
    }

    @Test(priority = 10)
    public void verifyLogoutFunctionality() {
        WebElement logoutButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Logout']"));
        logoutButton.click();
        
        // Verify the logout confirmation dialog appears
        WebElement logoutConfirm = driver.findElement(By.id("com.example.yourapp:id/logout_confirmation"));
        Assert.assertTrue(logoutConfirm.isDisplayed(), "Logout confirmation dialog is not displayed.");
        
        // Confirm logout
        WebElement confirmButton = driver.findElement(By.id("com.example.yourapp:id/confirm_logout_button"));
        confirmButton.click();
        
        // Verify the user is redirected to the login screen
        WebElement loginScreen = driver.findElement(By.id("com.example.yourapp:id/login_screen"));
        Assert.assertTrue(loginScreen.isDisplayed(), "User is not redirected to the login screen after logout.");
    }

    @Test(priority = 11)
    public void verifyBottomNavigationBar() {
        // Test Percakapan button
        WebElement chatButton = driver.findElement(By.id("com.example.yourapp:id/percakapan_button"));
        chatButton.click();
        WebElement chatScreenTitle = driver.findElement(By.xpath("//android.widget.TextView[@text='Percakapan']"));
        Assert.assertTrue(chatScreenTitle.isDisplayed(), "Failed to navigate to Percakapan screen.");
        
        // Test Kontak button
        WebElement contactButton = driver.findElement(By.id("com.example.yourapp:id/kontak_button"));
        contactButton.click();
        WebElement contactScreenTitle = driver.findElement(By.xpath("//android.widget.TextView[@text='Kontak']"));
        Assert.assertTrue(contactScreenTitle.isDisplayed(), "Failed to navigate to Kontak screen.");
        
        // Test Pengaturan button
        WebElement settingsButton = driver.findElement(By.id("com.example.yourapp:id/pengaturan_button"));
        settingsButton.click();
        WebElement settingsScreenTitle = driver.findElement(By.xpath("//android.widget.TextView[@text='Pengaturan']"));
        Assert.assertTrue(settingsScreenTitle.isDisplayed(), "Failed to navigate to Pengaturan screen.");
    }
    
    @Test(priority = 12)
    public void verifyScreenResponsiveness() {
        // Rotate screen to landscape
        ((SupportsRotation) driver).rotate(ScreenOrientation.LANDSCAPE);
        WebElement settingsHeader = driver.findElement(By.xpath("//android.widget.TextView[@text='Pengaturan']"));
        Assert.assertTrue(settingsHeader.isDisplayed(), "Settings header not displayed in landscape mode.");

        // Rotate back to portrait
        ((SupportsRotation) driver).rotate(ScreenOrientation.PORTRAIT);
        Assert.assertTrue(settingsHeader.isDisplayed(), "Settings header not displayed in portrait mode.");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
