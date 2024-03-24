package InnvocationCount;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestInvocationCount {
	
	WebDriver driver;
	
	@BeforeMethod
    public void setup() throws Exception {
         
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//test//resources//chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(chromeOptions);       
       driver.manage().window().maximize();
       driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.get("https://opensource-demo.orangehrmlive.com/");
    }
     
    @Test(invocationCount = 3)
    public void verifyLinkedIn() throws InterruptedException {
 
        System.out.println("Test Case 1 with Thread Id - "+Thread.currentThread().getId());
        driver.findElement(By.name("username")).sendKeys("Admin");
		System.out.println("Username :" + "Admin");

		Thread.sleep(2000);
		driver.findElement(By.name("password")).sendKeys("admin123");
		System.out.println("password :" + "admin123");

		driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.manage().window().maximize();
        Boolean timeAtWork = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/p")).isEnabled();
        assertTrue(timeAtWork);
    }
 
     
    @Test(invocationCount = 2)
    public void validLoginTest() throws InterruptedException {
         
        System.out.println("Test Case 2 with Thread Id - "+Thread.currentThread().getId());
 
		driver.findElement(By.name("username")).sendKeys("Admin");
		System.out.println("Username :" + "Admin");

		Thread.sleep(2000);
		driver.findElement(By.name("password")).sendKeys("admin123");
		System.out.println("password :" + "admin123");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(2000);
        String expectedTitle = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[1]/div[1]/span/h6")).getText();
        Assert.assertTrue(expectedTitle.contains("Dashboard"));
    }
 
        @AfterMethod
        public  void closeBrowser() {
             
            driver.quit();
                       
        }
}
