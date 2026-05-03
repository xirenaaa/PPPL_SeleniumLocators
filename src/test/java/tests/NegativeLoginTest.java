package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
public class NegativeLoginTest extends BaseTest {
    @Test(priority = 6)
    public void test06_NegativeLogin_LockedOutUser_NameAndXPath() {
        driver.findElement(By.name("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface')]"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message harus ditampilkan");
        Assert.assertTrue(errorMsg.getText().contains("locked out"), "Error message harus mengandung 'locked out'");
    }
    @Test(priority = 7)
    public void test07_NegativeLogin_LockedOutUser_XPathAxes() {
        driver.findElement(By.xpath("//*[starts-with(@id,'user')]")).sendKeys("locked_out_user");
        driver.findElement(By.xpath("//input[@name='user-name']/parent::div/following-sibling::div[1]/input[@type='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        WebElement errorContainer = driver.findElement(By.xpath("//div[contains(@class,'error-message-container')]"));
        Assert.assertTrue(errorContainer.isDisplayed(), "Error container harus ditampilkan");
        WebElement errorHeading = driver.findElement(By.xpath("//div[contains(@class,'error-message-container')]/descendant::h3"));
        Assert.assertTrue(errorHeading.getText().contains("locked out"), "Error heading harus mengandung 'locked out'");
    }
}
