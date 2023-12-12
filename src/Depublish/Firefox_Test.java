package Depublish;

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
        Thread.sleep(3000);

        driver.findElement(By.id("dataset-name")).sendKeys("Automation_All_types_Firefox_depublish_MET5654");
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
        Thread.sleep(3000);

        //Select all workflows
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/app-workflow-header/div/div[1]/span[1]/a[1]")).click();


        //Select http upload
        driver.findElement(By.cssSelector("lib-radio-button:nth-child(2) span")).click();

        //Upload http file
        driver.findElement(By.cssSelector("#url")).sendKeys("https://metis-repository-rest.test.eanadev.org/repository/zip/deepti_all_types.zip");

        //Save and Run the workflow
        driver.findElement(By.cssSelector("body > app-root > div > div > app-dataset > div > div > div.tab-content > app-workflow > div > form > div > div > div > app-loading-button > span > button")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("body > app-root > div > div > app-dataset > div > div > div.tab-content > app-workflow > div > form > div > div > div > app-loading-button > span > button")).click();

        // Display the dataset id and number of published items//
        WebElement dataset_name = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[1]/div[1]/app-generalinfo/div/div[1]"));
        WebElement no_of_published_records = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[1]/div[1]/app-generalinfo/div/dl/dd[2]"));
        driver.navigate().back();
        WebElement dataset_Id = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-datasetform/div/form/div/div[1]/span"));

        System.out.println("The Dataset Id is :" + dataset_Id.getText());
        System.out.println("The Dataset name is :" + dataset_name.getText());
        System.out.println("Number of items published : " + no_of_published_records.getText());


        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[2]/app-tab-headers/ul/li[3]/a")).click();

        driver.findElement(By.cssSelector("li.dropdown:nth-child(3) > a:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".dropdown-content > li:nth-child(2) > a:nth-child(1)")).click();
       // action.sendKeys(Keys.ENTER).build().perform();
        WebElement input_file = driver.findElement(By.cssSelector("textarea.ng-pristine"));
        input_file.click();
        input_file.sendKeys("data_sounds_http___archive_org_download_BTO2012_09_16_BTO_2012_09_16_set2_08_mp3");
        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-depublication/lib-modal[1]/div/div/div[3]/p/button")).click();
        Thread.sleep(1000*60*16);
        driver.navigate().refresh();
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-depublication/div[2]/app-sortable-header[1]/span/lib-checkbox/label/span")).click();

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
