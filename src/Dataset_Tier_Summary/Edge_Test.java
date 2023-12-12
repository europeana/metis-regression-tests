package Dataset_Tier_Summary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import static util.ConfigReader.*;

public class Edge_Test {
    public WebDriver driver;
// @Parameters("browserName")
@Test
public void EdgeTest() throws InterruptedException, IOException {
        //if (browser.equalsIgnoreCase("chrome")) {
        String driverPath = System.getProperty("driverPath1");
        //System.setProperty("webdriver.edge.driver", "C:\\Users\\Deepti Pandit\\IdeaProjects\\Sandbox_Regression_Acceptance_1\\Resources\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new EdgeDriver(options);
        driver.get(getUrl());
        // maximize the browser
        driver.manage().window().maximize();
        Thread.sleep(5000);


        //Sign in
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

        driver.findElement(By.xpath("//*[@id=\"dataset-name\"]")).sendKeys("Automation_Date_Normalization_edge_2");

        driver.findElement(By.xpath("//*[@id=\"provider\"]")).sendKeys("Automation");

        // Selecting a country from drop down
        WebElement country_drop_down = driver.findElement(By.id("country"));
        Select select = new Select(country_drop_down);
        select.selectByVisibleText("Europe");
        select.selectByIndex(18);

        //Selecting a language from language drop down
        WebElement language_drop_down = driver.findElement(By.id("language"));
        Select select1 = new Select(language_drop_down);
        select1.selectByVisibleText("Croatian (hrvatski jezik)");
        select1.selectByIndex(11);

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
        driver.findElement(By.cssSelector("#url")).sendKeys("https://metis-repository-rest.test.eanadev.org/repository/zip/Deepti_date_normalization.zip");
        Thread.sleep(2000);

        //Save and Run the workflow
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.TAB).build().perform();
        action.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);

        //Run the workflow
        driver.findElement(By.cssSelector(".submit")).click();

//        // Display the dataset id and number of published items//
//        WebElement dataset_name = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[1]/div[1]/app-generalinfo/div/div[1]"));
//        WebElement no_of_published_records = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[1]/div[1]/app-generalinfo/div/dl/dd[2]"));
//        driver.navigate().back();
//        WebElement dataset_Id = driver.findElement(By.xpath("/html/body/app-root/div/div/app-dataset/div/div/div[3]/app-datasetform/div/form/div/div[1]/span"));
//
//        System.out.println("The Dataset Id is :" + dataset_Id.getText());
//        System.out.println("The Dataset name is :" + dataset_name.getText());
//        Thread.sleep(1000*60*5);
//        System.out.println("Number of items published : " + no_of_published_records.getText());

    //Navigating to Dataset Tier Summary
    driver.findElement(By.cssSelector(".pie-orb")).click();
    Thread.sleep(3000);

    //Sorting via Record ID
    driver.findElement(By.cssSelector("span.grid-header:nth-child(3) > a:nth-child(1)")).click();
    Thread.sleep(2000);

    //Sorting via Content Tier
    driver.findElement(By.cssSelector("span.grid-header:nth-child(1)")).click();
    Thread.sleep(2000);

    //Sorting via Metadata Tier
    driver.findElement(By.cssSelector("span.grid-header:nth-child(2)")).click();
    Thread.sleep(2000);

    driver.findElement(By.cssSelector("span.grid-header:nth-child(3) > a:nth-child(1)")).click();
    Thread.sleep(2000);

    WebElement rows_per_page = driver.findElement(By.id("maxPageSize"));
    rows_per_page.findElement(By.xpath("//option[. = '25']")).click();

    WebElement go_to_page = driver.findElement(By.id("goTo"));
    go_to_page.click();
    go_to_page.sendKeys("2");

    WebElement search = driver.findElement(By.cssSelector("input.ng-valid:nth-child(2)"));
    search.click();
    search.sendKeys("92");
    Thread.sleep(2000);

        // Close the browser
//        driver.quit();
}
}