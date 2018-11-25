package ua.edu.sumdu.ta.yarmolenko.pr7.tests;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class WebSiteTest {

    private static final String NETCRACKER_SU = "NetCracker Su";
    private static final String NETCRACKER_SUMY = "NetCracker Sumy";
    private static final String NETCRACKER_SUMY_UKR = "NetCracker. :: Суми";
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver","webdriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void runTest() throws IOException {
        WebElement we = tryToFindAndClickElement(NETCRACKER_SU);
        if (we != null) {
            we.click();
        } else {
            we = tryToFindAndClickElement(NETCRACKER_SUMY);
            if (we != null) {
                we.click();
            }
        }

        WebElement googleResults = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        List<WebElement> foundElements = driver.findElements(By.xpath("//*[@id='rso']//a/h3"));

        boolean ncSumyUkrFound = false;
        for (WebElement webElement : foundElements) {
            String result = webElement.getText();
            System.out.println(result);
            if (NETCRACKER_SUMY_UKR.equalsIgnoreCase(result)) {
                webElement.click();
                ncSumyUkrFound = true;
                break;
            }
        }

        if (!ncSumyUkrFound) {
            //if not found - then go to direct link
            driver.get("http://www.netcracker.com/ukr/vacancies");
        }

        //Screenshot:
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target//" + driver.getTitle() + ".png"));
    }

    private WebElement tryToFindAndClickElement(String searchString) {
        driver.get("https://google.com");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(searchString);

        //try to wait. It is not good solution, but otherwise browser has no time to find elements
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //This is xpath for Google search suggestions
        List<WebElement> listGoogleSuggestions = driver.findElement(By.xpath("//ul[@role='listbox']")) //xpath statements could be better to be set as private static final String
                .findElements(By.xpath("//li[@role='presentation']"));

        for (WebElement we : listGoogleSuggestions) {
            String result = we.getText();
            if (NETCRACKER_SUMY.equalsIgnoreCase(result)) {
                return we;
            }
        }

        return null;
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
