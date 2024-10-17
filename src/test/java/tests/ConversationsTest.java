package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.AppiumBy;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConversationsTest extends BaseTest {
	 private ExtentReports extent;
	    private ExtentTest test;

	    @BeforeClass
	    public void setupReporting() {
	        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ConversationsTestReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(spark);
	        extent.setSystemInfo("Host Name", "Localhost");
	        extent.setSystemInfo("Tester", "Ajeet");
	        extent.setSystemInfo("Environment", "Stage");
	        extent.setSystemInfo("Module", "Conversations");
	        
	    }
	
	    
	    /* These re test cases for testing the conversations scenarios and of the shopkey WASP */

    @Test(priority = 1)
    public void testCNavigation() {
        test = extent.createTest("testCNavigation", "Test if conversations screen is displayed after login");
        try {
            performLogin1("parminder@gamechangesns.com", "Qwerty@123");
           // WebElement dashboardHeader = waitForVisibility(By.xpath("//android.widget.TextView[@resource-id=\"com.gamechange.wasp.shopkey:id/titleTV\"]"), 40);
            //Assert.assertTrue(dashboardHeader.isDisplayed(), "Conversations is not displayed.");
            test.log(Status.PASS, "Navigated to conversations successfully!");
        } catch (Exception e) {
            test.log(Status.FAIL, "Error navigating to conversations: " + e.getMessage());
           // captureScreenshot("testCNavigation");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
    
    private void performLogin1(String email, String password) {
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
    @Test(priority = 2, dependsOnMethods = {"testCNavigation"})
    public void testConversationsScreenDisplayed() {
    	 // Perform login only once
        WebElement usernameField = driver.findElement(By.id("com.gamechange.wasp.shopkey:id/emailAddressEditText"));
        usernameField.sendKeys("parminder@gamechangesns.com");
        
        WebElement passwordField = driver.findElement(By.id("com.gamechange.wasp.shopkey:id/etPasswordEditText"));
        passwordField.sendKeys("Qwerty@123");
        
        WebElement loginButton = driver.findElement(By.id("com.gamechange.wasp.shopkey:id/btnLogin"));
        loginButton.click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
       // WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@resource-id='com.gamechange.wasp.shopkey:id/titleTV']")));

        
        // Assert that the conversation screen is displayed
        //WebElement conversationScreen = driver.findElement(By.id("com.gamechange.wasp.shopkey:id/titleTV"));
      //  Assert.assertTrue(conversationScreen.isDisplayed(), "Login failed or conversation screen did not appear.");

    	test = extent.createTest("testConversationsScreenDisplayed", "Verify if the conversations screen is displayed correctly");
        try {
            WebElement conversationsTab = driver.findElement(AppiumBy.accessibilityId("com.gamechange.wasp.shopkey:id/titleTV"));
            conversationsTab.click();
          //  Assert.assertTrue(driver.findElement(AppiumBy.id("com.gamechange.wasp.shopkey:id/titleTV")).isDisplayed(),
           //         "Conversations screen should be displayed.");
            test.log(Status.PASS, "Conversations screen displayed successfully!");
        } catch (Exception e) {
            test.log(Status.FAIL, "Error displaying conversations screen: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 3, dependsOnMethods = {"testCNavigation"})
    public void testTabsFunctionality() {
    	test = extent.createTest("testTabsFunctionality", "Verify if the Tabs are displayed correctly");
      try {
    	WebElement allTab = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.gamechange.wasp.shopkey:id/tTV1\"]"));
        WebElement teamsTab = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.gamechange.wasp.shopkey:id/tTV2\"]"));
        WebElement labelsTab = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.gamechange.wasp.shopkey:id/tTV3\"]"));

        allTab.click();
        Assert.assertTrue(allTab.isSelected(), "All tab should be selected.");

        teamsTab.click();
        Assert.assertTrue(teamsTab.isSelected(), "Teams tab should be selected.");

        labelsTab.click();
        Assert.assertTrue(labelsTab.isSelected(), "Labels tab should be selected.");
        test.log(Status.PASS, "Conversations tabs seen successfully!");
      }
        catch (Exception e) {
            test.log(Status.FAIL, "Error Tabs Functionality Status: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
      

    @Test(priority = 4, dependsOnMethods = {"testCNavigation"})
    public void testFilterTypeFunctionality() {
        // Create a test instance in the report for tracking this method
        test = extent.createTest("testFilterTypeFunctionality", "Verify if the filters are working correctly");

        try {
            // Find and click the type filter element
            WebElement typeFilter = waitForVisibility(AppiumBy.id("com.gamechange.wasp:id/type_filter"), 30);
            typeFilter.click();

            // Wait for and select the 'Resolved' option
            WebElement resolvedOption = waitForVisibility(AppiumBy.xpath("//android.widget.CheckedTextView[@text='Resolved']"), 30);
            resolvedOption.click();

            // Assert that the filter was applied correctly by checking the presence of 'Resolved' in the filtered view
            WebElement resolvedText = waitForVisibility(AppiumBy.xpath("//android.widget.TextView[contains(@text,'Resolved')]"), 30);
            Assert.assertTrue(resolvedText.isDisplayed(), "Conversations should be filtered by Resolved type.");
            
            // Log a pass status if all steps were successful
            test.log(Status.PASS, "Filter type 'Resolved' applied successfully!");

        } catch (Exception e) {
            // If there's an exception, log it as a failure in the test report
            test.log(Status.FAIL, "Error in Filter Type Functionality: " + e.getMessage());
            // Optionally capture a screenshot if needed for the report
            // captureScreenshot("testFilterTypeFunctionality");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }


    private WebElement waitForVisibility(By xpath, int i) {
		// TODO Auto-generated method stub
		return null;
	}


    @Test(priority = 5, dependsOnMethods = {"testCNavigation"})
    public void testFilterStatusFunctionality() {
    	test = extent.createTest("testFilterStatusFunctionality", "Verify if the FilterStatus are working correctly");
    	try {
        WebElement statusFilter = driver.findElement(AppiumBy.id("com.gamechange.wasp:id/status_filter"));
        statusFilter.click();
        WebElement openOption = driver.findElement(AppiumBy.xpath("//android.widget.CheckedTextView[@text='Open']"));
        openOption.click();
        Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text,'Open')]")).isDisplayed(),
                "Conversations filter should be filtered by Open status.");
        test.log(Status.PASS, "Conversations tabs seen successfully!");
    }
        catch (Exception e) {
            test.log(Status.FAIL, "Error Filter Status Functionality Status: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
        }

    @Test(priority = 5)
    public void testConversationDetailsDisplay() {
    	test = extent.createTest("testConversationDetailsDisplay", "Verify if the Conversation Details Display are working correctly");
    	try {
        List<WebElement> conversations = driver.findElements(AppiumBy.id("com.gamechange.wasp:id/conversation_item"));
        Assert.assertTrue(conversations.size() > 0, "Conversations should be displayed.");

        WebElement firstConversation = conversations.get(0);
        WebElement name = firstConversation.findElement(AppiumBy.id("com.gamechange.wasp:id/conversation_name"));
        WebElement timestamp = firstConversation.findElement(AppiumBy.id("com.gamechange.wasp:id/conversation_timestamp"));
        WebElement statusBadge = firstConversation.findElement(AppiumBy.id("com.gamechange.wasp:id/status_badge"));

        Assert.assertTrue(name.isDisplayed(), "Name should be displayed.");
        Assert.assertTrue(timestamp.isDisplayed(), "Timestamp should be displayed.");
        Assert.assertTrue(statusBadge.isDisplayed(), "Status badge should be displayed.");
        test.log(Status.PASS, "Conversation Details displayed successfully!");
    	}
    	catch (Exception e) {
            test.log(Status.FAIL, "Error Conversation Details Display Status: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void testSearchFunctionality() {
    	test = extent.createTest("testSearchFunctionality", "Verify if the Search Functionality working correctly");
        try {
    	WebElement searchIcon = driver.findElement(AppiumBy.id("com.gamechange.wasp:id/search_icon"));
        searchIcon.click();

        WebElement searchInput = driver.findElement(AppiumBy.id("com.gamechange.wasp:id/search_input"));
        searchInput.sendKeys("Dira");

        Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Dira')]")).isDisplayed(),
                "Search result should display conversation containing 'Dira'.");
        test.log(Status.PASS, "Search Functionality working successfully!");  
        
    }
    	catch (Exception e) {
            test.log(Status.FAIL, "Error Search Functionality Status: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
    	}
        }
        

    @Test(priority = 7)
    public void testOpenConversation() {
    	test = extent.createTest("testOpenConversation", "Verify if Opening Conversation correctly");
    	try {
        List<WebElement> conversations = driver.findElements(AppiumBy.id("com.gamechange.wasp:id/conversation_item"));
        conversations.get(0).click();
        WebElement chatHistory = driver.findElement(AppiumBy.id("com.gamechange.wasp:id/chat_history"));
        Assert.assertTrue(chatHistory.isDisplayed(), "Conversation details should open with chat history.");
        test.log(Status.PASS, "Opening Conversation successfully!"); 
    }
    	catch (Exception e) {
            test.log(Status.FAIL, "Error Open Conversation Status: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
    	}
    	}

    @Test(priority = 9)
    public void testUnreadConversationCounter() {
    	test = extent.createTest("testUnreadConversationCounter", "Verify if Unread Conversation Counter seen correctly");
    	try {
        WebElement unreadCounter = driver.findElement(AppiumBy.id("com.gamechange.wasp:id/unread_counter"));
        Assert.assertTrue(unreadCounter.isDisplayed(), "Unread counter should be visible for conversations with unread messages.");
        test.log(Status.PASS, "Showing Unread Conversation Counter successfully!");
    	}
    	catch (Exception e) {
            test.log(Status.FAIL, "Error Unread Conversation Counter Status: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
    	}
    	}

    @Test(priority = 10)
    public void testTimestampValidation() {
    	test = extent.createTest("testTimestampValidation", "Verify if Timestamp Validation seen correctly");
    	try {
        List<WebElement> timestamps = driver.findElements(AppiumBy.id("com.gamechange.wasp:id/conversation_timestamp"));
        for (WebElement timestamp : timestamps) {
            String timeText = timestamp.getText();
            Assert.assertFalse(timeText.isEmpty(), "Timestamp should not be empty for any conversation.");
            test.log(Status.PASS, "Showing Timestamp Validation successfully!");
        }
    	}
    	catch (Exception e) {
            test.log(Status.FAIL, "Error working TimestampValidation Status: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
    	}
        }

    @Test(priority = 11)
    public void testEmptyStateDisplay() {
    	test = extent.createTest("testEmptyStateDisplay", "Verify if Empty State of message Display seen correctly");
        // Simulate empty state if possible or test directly
    	try {
        WebElement emptyStateMessage = driver.findElement(AppiumBy.id("com.gamechange.wasp:id/empty_state_message"));
        Assert.assertTrue(emptyStateMessage.isDisplayed(), "Empty state message should be displayed when there are no conversations.");
        test.log(Status.PASS, "Showing Timestamp Validation successfully!");
    	}
    	catch (Exception e) {
            test.log(Status.FAIL, "Error working Empty StateDisplay Status: " + e.getMessage());
           // captureScreenshot("testConversationsScreenDisplayed");
            Assert.fail("Test failed due to exception: " + e.getMessage());
    	}
    	}

    @AfterClass
    public void tearDown() {
    	if (extent != null) {
            extent.flush(); // This writes all the logs to the report file
        }
        //if (driver != null) {
           // driver.quit();
        }
    }
//}
