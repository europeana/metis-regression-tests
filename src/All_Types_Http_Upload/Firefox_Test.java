package All_Types_Http_Upload;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
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
        driver.findElement(By.xpath("/html/body/app-root/app-header/div/header/div/div[2]/ul/li/a")).click();
        driver.findElement(By.xpath("/html/body/app-root/app-header/div/header/div/div[2]/ul/li/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(getUsername());
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(getPassword());
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-login/div/form/div[2]/div/app-loading-button")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/app-root/div/div/ng-component/div/div[2]/div/app-dashboardactions/div/span/a[1]/span")).click();

        Set<String> windowhandles = driver.getWindowHandles();
        System.out.println(windowhandles);
        Iterator<String> iterator = windowhandles.iterator();

        String parent_iterator = iterator.next();
        String child_iterator = iterator.next();
        driver.switchTo().window(child_iterator);
        Thread.sleep(3000);

        driver.findElement(By.id("dataset-name")).sendKeys("Automation_All_types_Firefox_MET6320");
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
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-newdataset/div/div[2]/app-datasetform/div/form/div/div[12]/app-loading-button/button")).click();
        Thread.sleep(2000);
        WebElement Dataset = driver.findElement(By.cssSelector(".active > a:nth-child(1)"));
        Dataset.click();

        //Tab to workflow
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);

        //Select all workflows
        driver.findElement(By.xpath("//app-workflow-header/div/div/span/a")).click();
        Thread.sleep(2000);

        //Select http upload
        driver.findElement(By.cssSelector("lib-radio-button:nth-child(2) span")).click();

        //Upload http file
        driver.findElement(By.cssSelector("#url")).sendKeys("https://metis-repository-rest.test.eanadev.org/repository/zip/deepti_all_types.zip");

        //Save and Run the workflow
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-workflow/div/form/div/div/div/app-loading-button/button")).click();
        Thread.sleep(2000);
       driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-workflow/div/form/div/div/div/app-loading-button/button/span")).click();

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