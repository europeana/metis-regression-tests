package Large_Dataset;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
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
    public void testMetis1() throws InterruptedException, IOException, AWTException {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Deepti Pandit\\IdeaProjects\\Sandbox-Regression\\Resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get(getUrl());
        driver.manage().window().maximize();
        Thread.sleep(5000);

        //Accept cookies
        driver.findElement(By.xpath("/html/body/sb-root/div[3]/div[2]/sb-cookie-consent/div/div/div/button[3]")).click();

        //Get Started
        driver.findElement(By.xpath("/html/body/sb-root/div[3]/sb-sandbox-navigation/main/div[2]/sb-home/div/a")).click();

        //Create new Dataset
        driver.findElement(By.xpath("/html/body/sb-root/div[3]/sb-sandbox-navigation/main/div[2]/ul/li/label/a")).click();

        //Upload a Dataset
        driver.findElement(By.id("name")).sendKeys("Automation_Large_Dataset_Chrome_Regression_MET5984");
        {
            WebElement dropdown = driver.findElement(By.id("country"));
            dropdown.findElement(By.xpath("//option[. = 'Georgia']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.id("language"));
            dropdown.findElement(By.xpath("//option[. = 'Gaelic (Scottish)']")).click();
        }

        //uploading the zip file
        WebElement chooseFile = driver.findElement(By.cssSelector("body > sb-root > div.pusher > sb-sandbox-navigation > main > div.sandbox-navigation-content > sb-upload > form > div.form-group.protocol-wrapper > lib-protocol-field-set > div:nth-child(2) > lib-file-upload > label > input"));
        chooseFile.sendKeys("C:\\Users\\Deepti Pandit\\IdeaProjects\\Sandbox-Regression\\Records\\records_1004.zip");

        //Submit the request
        WebElement submit_button = driver.findElement(By.xpath("/html/body/sb-root/div[3]/sb-sandbox-navigation/main/div[2]/sb-upload/form/button"));
        submit_button.submit();
        Thread.sleep(40000);

        ///Verifying the number of published records
        WebElement records_count = driver.findElement(By.cssSelector("span.step-progress:nth-child(30)"));
        System.out.println("Number of Published records are : " + records_count.getText());

        //Verifying the dataset name
        WebElement dataset_info = driver.findElement(By.xpath("/html/body/sb-root/div[3]/sb-sandbox-navigation/main/div[2]/sb-progress-tracker/sb-dataset-info/ul/li[1]/ul/li[1]/h2/a/span"));
        System.out.println("Date and Time info : " + dataset_info.getText());

        //Verifying the date and time dataset was created
        WebElement date_info = driver.findElement(By.xpath("/html/body/sb-root/div[3]/sb-sandbox-navigation/main/div[2]/sb-progress-tracker/sb-dataset-info/ul/li[1]/ul/li[3]/span[2]/span"));
        System.out.println("Dataset info : " + date_info.getText());

        //Fetching the dataset id
        WebElement dataset_id = driver.findElement(By.xpath("/html/body/sb-root/div[3]/sb-sandbox-navigation/main/div[2]/sb-progress-tracker/sb-dataset-info/ul/li[2]/ul/li[1]/h2"));
        System.out.println("Dataset Id is : " + dataset_id.getText());

        //Close the browser
        driver.quit();
    }
}