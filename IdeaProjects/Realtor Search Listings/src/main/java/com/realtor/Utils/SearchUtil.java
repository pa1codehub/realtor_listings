package com.realtor.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by pavan on 17-Jun-18.
 */
public class SearchUtil {

    public static WebDriver driver;

    protected static final int TINY_WAIT_MS = 600;

    protected String txtListingPrice;

    protected int finalCount;

    protected int summaryFinalPrice;

    //To invoke browser
    public WebDriver invokeBrowser(String browserName, String URL){

        try{
            if(browserName.equalsIgnoreCase("chrome"))
            {
                driver = new ChromeDriver();
                driver.manage().deleteAllCookies();
            }else if(browserName.equalsIgnoreCase("firefox"))
            {
                driver = new FirefoxDriver();
                driver.manage().deleteAllCookies();
            }else if(browserName.equalsIgnoreCase("edge"))
            {
                driver = new EdgeDriver();
                driver.manage().deleteAllCookies();
            }else if(browserName.equalsIgnoreCase("opera"))
            {
                driver = new OperaDriver();
                driver.manage().deleteAllCookies();
            }
            driver.navigate().to(URL);
        }catch(Exception e){

        }
        return driver;
    }

    //method to wait for a page
    public void tinyWait() {
        sleepMillis(TINY_WAIT_MS);
    }

    public void sleepMillis(long millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            System.out.println("Interrupted");
            Thread.currentThread().interrupt();
        }
    }

    //Method to enter text for any textbox
    public void searchLocationInTextBox(WebDriver driver, String searchElement, String text) {
        try {
            WebElement element = driver.findElement(By.id(searchElement));
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method to enter text for any textbox
    public void hitEnterOnButton(WebDriver driver, String searchElement) {
        try {
            WebElement button = driver.findElement(By.className(searchElement));
            button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expectedResultWait(WebDriver driver, String searchIdElement){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchIdElement)));
    }

    public int validateTotalSearchResultCount(WebDriver driver, String searchIdElement){
        try {
            String totalSearchResults = "";
            WebElement elementSearchCount = driver.findElement(By.xpath(searchIdElement));
            totalSearchResults = elementSearchCount.getText();

            String strCount = totalSearchResults.split(" ")[0];

            strCount = strCount.replace(",", "");

            finalCount = Integer.parseInt(strCount);
        } catch (Exception e){
            e.printStackTrace();
        }
        return finalCount;
    }

    //Method to select option from dropdown
    public void selectOptionFromDropDown(WebDriver driver, String sortOptionsByByxPath, String verifytext) {
        try {
            WebElement sortListingElement = driver.findElement(By.xpath(sortOptionsByByxPath));
            Select selectedValue = new Select(sortListingElement);
            selectedValue.selectByVisibleText(verifytext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPriceOfNthElement(WebDriver driver, String searchByClassName, String nthElement, String searchForPrice){
        String concatNthElement = "["+nthElement+"]";
        WebElement summaryPrice = driver.findElement(By.xpath(searchByClassName.concat(concatNthElement)));
        String txtSummaryPrice = summaryPrice.getText();
        txtSummaryPrice = txtSummaryPrice.replace(",", "");
        txtSummaryPrice = txtSummaryPrice.replace("$", "");
        summaryPrice.click();
        summaryFinalPrice = Integer.parseInt(txtSummaryPrice);
        return summaryFinalPrice;
    }

    public int validateSummaryAndNthElementPrices(WebDriver driver, int summaryPrice, String searchListingByxPath){
        try {
            WebElement listingPrice = driver.findElement(By.xpath(searchListingByxPath));
            txtListingPrice = listingPrice.getText();
            txtListingPrice = txtListingPrice.replace(",", "");
            txtListingPrice = txtListingPrice.replace("$", "");
        }catch(Exception e){
            e.printStackTrace();
        }
        return Integer.parseInt(txtListingPrice);
    }
}
