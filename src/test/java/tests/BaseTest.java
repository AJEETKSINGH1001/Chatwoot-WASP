package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Level;


/* These test scripts are for Chatwoot-WASP android application which is an open-source as well as cloud-based customer 
 * engagement platform. Manage all of your customer conversation channels from one place.
 * Creation Date:- 09-10-2024
 * Creator:- Ajeet Kumar Singh
 * 
 */

public class BaseTest {
    protected AndroidDriver driver;

    // Application constants
    private static final String APP_PACKAGE = "com.gamechange.wasp";
    private static final String APP_ACTIVITY = "com.bitnudge.ime.parent.view.activities.SplashActivity";
    private static final String DEVICE_UDID = "084113125P054404";
    private static final String AUTOMATION_NAME = "UiAutomator2";
    private static final String BASE_URL = "http://127.0.0.1:4723/";

    @SuppressWarnings("deprecation")
	@BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("automationName", AUTOMATION_NAME);
        dc.setCapability(MobileCapabilityType.UDID, DEVICE_UDID);

        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);

        // Initialize the AndroidDriver
        driver = new AndroidDriver(new URL(BASE_URL), dc);
        driver.setLogLevel(Level.INFO);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
    }
}
}
