package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class SettingsTest extends BaseTest {

    @Test(priority = 1)
    public void testChangeSettings() {
        // Perform login before testing settings functionality
        performLogin("parminder@gamechangesns.com", "Qwerty@123");

        // Navigate to the settings section (update this according to your app)
        clickElement(By.id("com.gamechange.wasp.shopkey:id/settingsButton"));

        // Change a setting (update this according to your app)
        clickElement(By.id("com.gamechange.wasp.shopkey:id/notificationsToggle"));

        // Verify that the setting change was successful
        Assert.assertTrue(isToastMessageDisplayed("Settings updated successfully"), "Settings update message not displayed.");
        System.out.println("Settings updated successfully!");
    }

	private void performLogin(String string, String string2) {
		// TODO Auto-generated method stub
		
	}
}
