package OAI_Upload;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static util.ConfigReader.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import org.openqa.selenium.chrome.ChromeOptions;

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
        driver.findElement(By.id("name")).sendKeys("Automation_OAI_Upload_Chrome_Regression");
        {
            WebElement dropdown = driver.findElement(By.id("country"));
            dropdown.findElement(By.xpath("//option[. = 'Georgia']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.id("language"));
            dropdown.findElement(By.xpath("//option[. = 'Gaelic (Scottish)']")).click();
        }

        //Selecting OAI Upload
        WebElement radio_button = driver.findElement(By.cssSelector(".form-group-radios > lib-radio-button:nth-child(2) > label:nth-child(1) > span:nth-child(2)"));
        radio_button.click();

        //Entering data in harvestURL
        WebElement harvestURL = driver.findElement(By.cssSelector("#harvest-url"));
        harvestURL.sendKeys("https://aggregator.ekt.gr/aggregator-oai/request");

        //Entering data in Setspec
        WebElement setspec = driver.findElement(By.cssSelector("#setspec"));
        setspec.sendKeys("mantamado");

        //Entering data in Metadata format
        WebElement metadata_format = driver.findElement(By.cssSelector("#metadata-format"));
        metadata_format.sendKeys("edm");

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


        //Navigating to Dataset Tier Summary
        driver.findElement(By.cssSelector(".pie-orb")).click();
        //Close the browser
        driver.quit();
    }
}
