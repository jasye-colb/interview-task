import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public static By usernameLocator = By.id("username");
    public static By passwordLocator = By.id("password");
    public static By loginButtonLocator = By.id("login-btn");

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void logInWithUsernameAndPassword
            (String username, String password, WebDriver driver) {

        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }

    @FindBy(how = How.ID, using = "username")
    private WebElement userName;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.ID, using = "login-btn")
    private WebElement login;

    public void logIn(String userName, String password) {
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        this.login.click();
    }


}
