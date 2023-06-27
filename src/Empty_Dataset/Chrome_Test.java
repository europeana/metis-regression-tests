package Empty_Dataset;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static util.ConfigReader.*;

public class Chrome_Test {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        //driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    //    @After
//    public void tearDown() {
//        driver.quit();
//    }
    @Test
    public void testMetis1() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Deepti Pandit\\IdeaProjects\\Sandbox_Regression_Acceptance_1\\Resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get(getUrl());
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/app-root/app-header/div/header/div/div[2]/ul/li/a")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-header/div/header/div/div[2]/ul/li/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(getUsername());
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(getPassword());
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-login/div/form/div[2]/div/app-loading-button/span/button/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/app-root/div/div/ng-component/div/div[2]/div/app-dashboardactions/div/span/a[1]")).click();

        Set<String> windowhandles = driver.getWindowHandles();
        System.out.println(windowhandles);
        Iterator<String> iterator = windowhandles.iterator();
        String parent_iterator = iterator.next();
        String child_iterator = iterator.next();
        driver.switchTo().window(child_iterator);
        driver.findElement(By.xpath("//*[@id=\"dataset-name\"]")).sendKeys("Automation_Empty_Dataset_chrome_0101");
        driver.findElement(By.xpath("//*[@id=\"provider\"]")).sendKeys("Automation");
        {
            WebElement dropdown = driver.findElement(By.id("country"));
            dropdown.findElement(By.xpath("//option[. = 'Georgia']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.id("language"));
            dropdown.findElement(By.xpath("//option[. = 'Gaelic (Scottish)']")).click();
        }
        Thread.sleep(2000);
//Create the dataset

        driver.findElement(By.xpath("/html/body/app-root/div/div/app-newdataset/div/div[2]/app-datasetform/div/form/div/div[12]/app-loading-button/span")).click();
        Thread.sleep(2000);
        WebElement Dataset = driver.findElement(By.cssSelector(".active > a:nth-child(1)"));
        Dataset.click();

        //Tab to workflow
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.ENTER).build().perform();

        //Select all workflows
        driver.findElement(By.xpath("//app-workflow-header/div/div/span/a")).click();
        Thread.sleep(2000);

        //Select http upload
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.SPACE).build().perform();
        action.sendKeys(Keys.TAB).build().perform();

        //Upload http file
        driver.findElement(By.cssSelector("#url")).sendKeys("https://metis-repository-rest.test.eanadev.org/repository/zip/deepti_empty_test.zip");
        Thread.sleep(2000);

        //Save and Run the workflow
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("body > app-root > div > div > app-dataset > div > div > div.tab-content > app-workflow > div > form > div > div > div > app-loading-button > span > button")).click();

        // Display the dataset id and number of published items//
        WebElement dataset_name = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[1]/div[1]/app-generalinfo/div/div[1]"));
        WebElement no_of_published_records = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[1]/div[1]/app-generalinfo/div/dl/dd[2]"));
        driver.navigate().back();
        WebElement dataset_Id = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-datasetform/div/form/div/div[1]/span"));

        System.out.println("The Dataset Id is :" + dataset_Id.getText());
        System.out.println("The Dataset name is :" + dataset_name.getText());
        Thread.sleep(1000*60*5);
        System.out.println("Number of items published : " + no_of_published_records.getText());

        // Close the browser
        driver.close();
    }
}