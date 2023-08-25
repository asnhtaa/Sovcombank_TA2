package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.FormPage;

public class BaseTest {
        private WebDriver driver;
        protected FormPage FormPage;

        @BeforeMethod
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(getChromeOptions());
            driver.manage().window().maximize();
            driver.get("https://demoqa.com/automation-practice-form");
            FormPage = new FormPage(driver);
        }

    @AfterMethod
    public void tearDown(){
        ;
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

}
