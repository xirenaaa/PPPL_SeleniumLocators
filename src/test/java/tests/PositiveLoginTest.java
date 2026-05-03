package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
public class PositiveLoginTest extends BaseTest {
    @Test(priority = 1)
    public void test01_PositiveLogin_NameLocator() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        verifySuccessfulLogin();
    }
    @Test(priority = 2)
    public void test02_PositiveLogin_ClassLocator() {
        List<WebElement> formInputs = driver.findElements(By.className("form_input"));
        formInputs.get(0).sendKeys("standard_user");
        formInputs.get(1).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        verifySuccessfulLogin();
    }
    @Test(priority = 3)
    public void test03_PositiveLogin_TagLocator() {
        List<WebElement> allInputs = driver.findElements(By.tagName("input"));
        allInputs.get(0).sendKeys("standard_user");
        allInputs.get(1).sendKeys("secret_sauce");
        allInputs.get(2).click();
        verifySuccessfulLogin();
    }
    @Test(priority = 4)
    public void test04_PositiveLogin_XPathFunctions() {
        driver.findElement(By.xpath("//*[contains(@class,'form_input') and @type='text']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[starts-with(@name,'pass')]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[contains(@class,'submit-button')]")).click();
        verifySuccessfulLogin();
    }
    @Test(priority = 5)
    public void test05_PositiveLogin_XPathAxes() {
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@name='user-name']/parent::div/following-sibling::div[1]/child::input[@type='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::input[@type='submit']")).click();
        verifySuccessfulLogin();
    }
    private void verifySuccessfulLogin() {
        String currentUrl = driver.getCurrentUrl();
        String pageTitle  = driver.getTitle();
        Assert.assertTrue(currentUrl.contains("inventory"), "URL harus mengandung 'inventory'");
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title harus 'Swag Labs'");
    }
}
