package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(){
        //System.setProperty("webdriver.chrome.driver","D:\\Driver\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        if(null!=driver){
            driver.close();
        }
    }
    public WebDriver getDriver() {
        return driver;
    }
}
