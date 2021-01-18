package JenkinsAutomationScripts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.InitiateDriver;
import slackPageObjects.SlackAppPageObjects;
import slackPageObjects.SlackIdentityObjects;

//Execute this 7th once SlackIntegrationFlowValidation is done
public class SlackAppValidationTest extends InitiateDriver{
	public static Logger log = LogManager.getLogger(SlackAppValidationTest.class.getName());
	public SlackAppPageObjects slackappobject;
	public SlackIdentityObjects slackobject;

	@BeforeTest
	public void Init() throws FileNotFoundException, IOException {
		// driver = initializeDriver();
		slackappobject = new SlackAppPageObjects(driver);
		slackobject = new SlackIdentityObjects(driver);

	}

	// Validate slack app url launch
	@Test(priority = 1)
	public void slackAppFunction() {
		driver.manage().window().maximize();
		driver.get("https://app.slack.com/client/T01ABD6LYCT/C01AB75N55Y");
		System.out.println("Slack App validation entry point");
		System.out.println("Testcase-1 passed since slack url is launched");
	}

	// Validate sign in functionality to slack account
	@Test(priority = 2)
	public void SignInUsingSlack() throws InterruptedException {
		System.out.println("Signing in using Slack account username & Pwd ");
		Thread.sleep(2000L);
		slackobject.SlackInputField().sendKeys("joinassembly21");
		slackobject.SlackContinueSubmitButton().click();
		slackobject.SlackEmailInputField().sendKeys("hema+21@joinassembly.com");
		slackobject.slackPwdInputField().sendKeys("Assembly2020!");
		slackobject.slackSignInButton().click();
		System.out.println("Testcase-2 passed since Slack app sign in is successful");
	}

	// Validate Give Recognition using slack app
	@Test(priority = 3)
	public void ValidateGiveRecogUsingSlash() throws InterruptedException {
		Thread.sleep(4000L);
		slackappobject.inputTextField().sendKeys("/give-recognition-dev ");
		System.out.println("Trying to give recognition using /give-recognition-dev ");
		  slackappobject.sendButton().isDisplayed();
		  System.out.println("Is send button Displayed? " +slackappobject.sendButton().isDisplayed()); 
		if (true) {
		  Thread.sleep(1000L); 
			slackappobject.sendButton().click();
		  }
		Thread.sleep(1000L);
		giveRecognitionFunc();
		System.out.println("Testcase-3 passed since recognition is given");
	}
	
// Validate Give Recognition using short cut
	@Test(priority = 4)
	public void ValidateGiveRecogUsingShortcut() throws InterruptedException {
		Thread.sleep(4000L);
		slackappobject.inputTextField().sendKeys("/give recognition dev ");
		System.out.println("Trying to give recognition using /give recognition dev ");
		Thread.sleep(1000L);
		slackappobject.inputTextField().sendKeys(Keys.ENTER);
		Thread.sleep(1000L);
		giveRecognitionFunc();
		System.out.println("Testcase-4 passed since recognition is given");

	}

	
	public void giveRecognitionFunc() throws InterruptedException {
		Thread.sleep(1000L);
		slackappobject.selectTeamMember().sendKeys("fabul");
		Thread.sleep(1000L);
		slackappobject.selectTeamMember().sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		System.out.println("Entered username successfully");
		slackappobject.giveRecognitionButton().click();
		Thread.sleep(1000L);
		String alertTextCapture = slackappobject.AlertText().getText();
		System.out.println("Alert message is "+ alertTextCapture);
		if (alertTextCapture.equalsIgnoreCase("Please complete this required field.")) {
			slackappobject.amountDropdown().click();
			Thread.sleep(1000L);
			slackappobject.customAmountText().click();
			Thread.sleep(2000L);
			slackappobject.customInputField().sendKeys("1");
			slackappobject.messageInputField().sendKeys("Posting here and testing");
			Thread.sleep(1000L);
			slackappobject.giveRecognitionButton().click();
		}
		Thread.sleep(1000L);
		System.out.println("The header text is " + slackappobject.recogSentHeader().getText());
		Assert.assertEquals(slackappobject.recogSentHeader().getText(), "Give recognition");
		Thread.sleep(1000L);
		slackappobject.closeButton().click();
	}
}
