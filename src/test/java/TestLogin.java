
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

/**
 * All Allure annotations provided just as example, production reports may contain much more information.
 */
public class TestLogin {

    private static WebDriver webDriver;

    @BeforeTest
    public static void initDriver() {
        webDriver = DriverInitializer.getDriver();
    }

    @AfterTest
    public static void tearDown() {
        webDriver.quit();
    }

    @BeforeMethod
    public void navigate() throws URISyntaxException {
        URL loginPageRes = TestLogin.class.getClassLoader().getResource("login.html");
        webDriver.get(Paths.get(loginPageRes.toURI()).toAbsolutePath().toString());
    }

    @Test
    public void login() {
        WebElement webElement = webDriver.findElement(By.id("username"));
        webElement.sendKeys("hi");
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys("hi");
        webElement = webDriver.findElement(By.id("login-btn"));
        webElement.click();
        webElement = webDriver.findElement(By.id("name"));
        assertEquals(webElement.getText(), "hi");

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Simple login test.")
    public void loginPageModel() {
        LoginPage.logInWithUsernameAndPassword("hi", "hi", webDriver);
        assertEquals(webDriver.findElement(IndexPage.usernameLocator).getText(), "hi");
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Simple login test using test factory.")
    public void loginPageFactory() {
        new LoginPage(webDriver).logIn("hi", "hi");
        assertEquals(webDriver.findElement(IndexPage.usernameLocator).getText(), "hi");
    }
}
