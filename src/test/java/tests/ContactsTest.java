package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class ContactsTest extends BaseTest {

    @Test(priority = 1)
    public void testAddContacts() {
        // Perform login before testing profile functionality
        performLogin("parminder@gamechangesns.com", "Qwerty@123");

        // Navigate to the profile section (update this according to your app)
        clickElement(By.id("com.gamechange.wasp.shopkey:id/profileButton"));

        // Update profile information (this is just an example)
        enterText(By.id("com.gamechange.wasp.shopkey:id/profileName"), "New Name");
        clickElement(By.id("com.gamechange.wasp.shopkey:id/saveButton"));

        // Verify that the update was successful
        Assert.assertTrue(isToastMessageDisplayed("Profile updated successfully"), "Profile update message not displayed.");
        System.out.println("Profile updated successfully!");
    }

	private void performLogin(String string, String string2) {
		// TODO Auto-generated method stub
		
	}
}
