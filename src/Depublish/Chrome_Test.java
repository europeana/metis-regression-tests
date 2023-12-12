package Depublish;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

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

    @Test
    public void testMetis1() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Deepti Pandit\\IdeaProjects\\Sandbox-Regression\\Resources\\chromedriver.exe");
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
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-login/div/form/div[2]/div/app-loading-button/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/app-root/div/div/ng-component/div/div[2]/div/app-dashboardactions/div/span/a[1]")).click();

        Set<String> windowhandles = driver.getWindowHandles();
        System.out.println(windowhandles);
        Iterator<String> iterator = windowhandles.iterator();
        String parent_iterator = iterator.next();
        String child_iterator = iterator.next();
        driver.switchTo().window(child_iterator);
        driver.findElement(By.xpath("//*[@id=\"dataset-name\"]")).sendKeys("Automation_depublish_chrome_MET5257_4");
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
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-newdataset/div/div[2]/app-datasetform/div/form/div/div[12]/app-loading-button/button/span")).click();
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
        driver.findElement(By.cssSelector("#url")).sendKeys("https://metis-repository-rest.test.eanadev.org/repository/zip/deepti_all_types.zip");
        Thread.sleep(2000);

        //Save the workflow
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);

        //Run the workflow
        driver.findElement(By.cssSelector(".submit")).click();

        // Display the dataset id and number of published items//
        WebElement dataset_name = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[1]/div[1]/app-generalinfo/div/div[1]"));
        WebElement no_of_published_records = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[1]/div[1]/app-generalinfo/div/dl/dd[2]"));
        driver.navigate().back();
        WebElement dataset_Id = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-datasetform/div/form/div/div[1]/span"));

        System.out.println("The Dataset Id is :" + dataset_Id.getText());
        System.out.println("The Dataset name is :" + dataset_name.getText());
        Thread.sleep(1000*60*5);
        System.out.println("Number of items published : " + no_of_published_records.getText());

        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[2]/app-tab-headers/ul/li[3]/a")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-depublication/div[1]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-depublication/div[1]/ul/li[3]/div/ul/li[2]/a")).click();
        // action.sendKeys(Keys.ENTER).build().perform();
        WebElement input_file = driver.findElement(By.cssSelector("textarea.ng-pristine"));
        input_file.click();
        input_file.sendKeys("data_sounds_http___archive_org_download_BTO2012_09_16_BTO_2012_09_16_set2_08_mp3");
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-depublication/lib-modal[1]/div/div/div[3]/p/button")).click();
        Thread.sleep(1000*60*16);
        driver.navigate().refresh();
        Thread.sleep(2000);

        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.SPACE).build().perform();

        //driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-depublication/div[2]/app-sortable-header[1]/span/lib-checkbox/label/span")).click();

        driver.findElement(By.cssSelector("li.dropdown:nth-child(2) > a:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".dropdown-content > li:nth-child(1) > a:nth-child(1)")).click();
        driver.findElement(By.cssSelector("button.button:nth-child(2)")).click();;

        System.out.println("The Dataset Id is :" + dataset_Id.getText());
        System.out.println("The Dataset name is :" + dataset_name.getText());
        System.out.println("Number of items published : " + no_of_published_records.getText());

        // Close the browser
        driver.close();
    }
}