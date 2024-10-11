package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ContactsTest extends BaseTest {

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

    @Test(priority = 1)
    public void testScreenLoad() {
        // Perform login only once
        WebElement usernameField = driver.findElement(By.id("com.gamechange.wasp.shopkey:id/emailAddressEditText"));
        usernameField.sendKeys("parminder@gamechangesns.com");
        
        WebElement passwordField = driver.findElement(By.id("com.gamechange.wasp.shopkey:id/etPasswordEditText"));
        passwordField.sendKeys("Qwerty@123");
        
        WebElement loginButton = driver.findElement(By.id("com.gamechange.wasp.shopkey:id/btnLogin"));
        loginButton.click();
        
        // Verify that the screen loads successfully
        WebElement screenTitle = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.gamechange.wasp.shopkey:id/contactsRv\"]/android.view.ViewGroup[1]"));
        Assert.assertTrue(screenTitle.isDisplayed(), "Kontak screen title is not displayed.");
    }

    @Test(priority = 2)
    public void testDisplayOfContacts() {
        // Verify that all contacts are displayed correctly
        WebElement firstContact = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.gamechange.wasp.shopkey:id/nameTv\" and @text=\"Yest\"]"));
        Assert.assertTrue(firstContact.isDisplayed(), "First contact is not displayed.");
    }

    @Test(priority = 3)
    public void testAddNewContact() {
        // Click on the + Tambahkan button
        WebElement addButton = driver.findElement(By.id("com.example.yourapp:id/add_contact_button"));
        addButton.click();

        // Fill in the contact form
        WebElement nameField = driver.findElement(By.id("com.example.yourapp:id/contact_name"));
        nameField.sendKeys("Test User");

        WebElement phoneField = driver.findElement(By.id("com.example.yourapp:id/contact_phone"));
        phoneField.sendKeys("+1234567890");

        WebElement emailField = driver.findElement(By.id("com.example.yourapp:id/contact_email"));
        emailField.sendKeys("testuser@example.com");

        WebElement saveButton = driver.findElement(By.id("com.example.yourapp:id/save_contact_button"));
        saveButton.click();

        // Verify that the contact is added
        WebElement newContact = driver.findElement(By.xpath("//android.widget.TextView[@text='Test User']"));
        Assert.assertTrue(newContact.isDisplayed(), "New contact is not added.");
    }

    @Test(priority = 4)
    public void testSearchContact() {
        // Click on the search icon
        WebElement searchIcon = driver.findElement(By.id("com.example.yourapp:id/search_icon"));
        searchIcon.click();

        // Enter the search term
        WebElement searchField = driver.findElement(By.id("com.example.yourapp:id/search_input"));
        searchField.sendKeys("Manish");

        // Verify search result
        WebElement searchResult = driver.findElement(By.xpath("//android.widget.TextView[@text='Manish']"));
        Assert.assertTrue(searchResult.isDisplayed(), "Search result for 'Manish' not found.");
    }

    @Test(priority = 5)
    public void testSortContacts() {
        // Click on the sort icon
        WebElement sortIcon = driver.findElement(By.id("com.example.yourapp:id/sort_icon"));
        sortIcon.click();

        // Select a sorting option (e.g., Sort by name)
        WebElement sortByName = driver.findElement(By.xpath("//android.widget.TextView[@text='Sort by Name']"));
        sortByName.click();

        // Verify that contacts are sorted correctly
        WebElement firstContactAfterSort = driver.findElement(By.xpath("//android.widget.TextView[@text='Keron']"));
        Assert.assertTrue(firstContactAfterSort.isDisplayed(), "Contacts are not sorted correctly.");
    }

    @Test(priority = 6)
    public void testDeleteContact() {
        // Long press or click on a contact to delete (Assuming it's long press)
        WebElement contactToDelete = driver.findElement(By.xpath("//android.widget.TextView[@text='Keron']"));
        // Use TouchAction or the appropriate gesture to long press if needed

        // Confirm deletion
        WebElement confirmDeleteButton = driver.findElement(By.id("com.example.yourapp:id/confirm_delete_button"));
        confirmDeleteButton.click();

        // Verify the contact is deleted
        boolean isContactDeleted;
        try {
            driver.findElement(By.xpath("//android.widget.TextView[@text='Keron']"));
            isContactDeleted = false;
        } catch (Exception e) {
            isContactDeleted = true;
        }

        Assert.assertTrue(isContactDeleted, "Contact was not deleted.");
    }

    @Test(priority = 7)
    public void testBottomNavigationBar() {
        // Test navigating to Percakapan screen
        WebElement chatButton = driver.findElement(By.id("com.example.yourapp:id/percakapan_button"));
        chatButton.click();
        WebElement chatScreenTitle = driver.findElement(By.xpath("//android.widget.TextView[@text='Percakapan']"));
        Assert.assertTrue(chatScreenTitle.isDisplayed(), "Failed to navigate to Percakapan screen.");

        // Test navigating to Pengaturan screen
        WebElement settingsButton = driver.findElement(By.id("com.example.yourapp:id/pengaturan_button"));
        settingsButton.click();
        WebElement settingsScreenTitle = driver.findElement(By.xpath("//android.widget.TextView[@text='Pengaturan']"));
        Assert.assertTrue(settingsScreenTitle.isDisplayed(), "Failed to navigate to Pengaturan screen.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
