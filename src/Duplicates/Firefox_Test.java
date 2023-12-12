package Duplicates;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import static util.ConfigReader.*;

public class Firefox_Test {
    public WebDriver driver;

    @Test
    public void FirefoxTest() throws InterruptedException, IOException {
        String driverPath = System.getProperty("driverPath");
        WebDriver driver = new FirefoxDriver();
        driver.get(getUrl());
        // maximize the browser
        driver.manage().window().maximize();
        Thread.sleep(5000);

        //Accept cookies
        driver.findElement(By.xpath("/html/body/sb-root/div[3]/div[2]/sb-cookie-consent/div/div/div/button[3]")).click();

        //Get Started
        driver.findElement(By.xpath("/html/body/sb-root/div[3]/sb-sandbox-navigation/main/div[2]/sb-home/div/a")).click();

        //Create new Dataset
        driver.findElement(By.xpath("/html/body/sb-root/div[3]/sb-sandbox-navigation/main/div[2]/ul/li/label/a")).click();

        //Upload a Dataset
        driver.findElement(By.id("name")).sendKeys("Automation_Firefox_Duplicates_Release_MET6040");
        {
            WebElement dropdown = driver.findElement(By.id("country"));
            dropdown.findElement(By.xpath("//option[. = 'Georgia']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.id("language"));
            dropdown.findElement(By.xpath("//option[. = 'Gaelic (Scottish)']")).click();
        }
        WebElement chooseFile = driver.findElement(By.cssSelector("body > sb-root > div.pusher > sb-sandbox-navigation > main > div.sandbox-navigation-content > sb-upload > form > div.form-group.protocol-wrapper > lib-protocol-field-set > div:nth-child(2) > lib-file-upload > label > input"));
        chooseFile.sendKeys("C:\\Users\\Deepti Pandit\\IdeaProjects\\Sandbox-Regression\\Records\\record_with_duplicate_3_records.zip");

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

        // Close the browser
        driver.quit();

    }
}