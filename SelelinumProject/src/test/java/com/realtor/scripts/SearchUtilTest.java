package com.realtor.scripts;

import com.realtor.Utils.SearchUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.realtor.Utils.constants.SearchValues;
import com.realtor.ActionPages.SearchActionPage;

/**
 * Created by pavan on 17-Jun-18.
 */
public class SearchUtilTest extends SearchUtil{

    WebDriver driver;

    @Before
    public void invokeStart(){

        String browser = System.getenv("browser");
        String URL = "https://www.realtor.com/";
        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
            /*Scenario 1 To Navigate to realtor.com*/
            driver = invokeBrowser(browser, URL);
        }else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.chrome.driver", ".\\geckodriver.exe");
            /*Scenario 1 To Navigate to realtor.com*/
            driver = invokeBrowser(browser, URL);
        }else if(browser.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.chrome.driver", ".\\MicrosoftWebDriver.exe");
            /*Scenario 1 To Navigate to realtor.com*/
            driver = invokeBrowser(browser, URL);
        }else if(browser.equalsIgnoreCase("opera")){
            System.setProperty("webdriver.chrome.driver", ".\\operadriver.exe");
            /*Scenario 1 To Navigate to realtor.com*/
            driver = invokeBrowser(browser, URL);
        }
    }

    @After
    public void cleanUpBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void ValidateSearchResults() throws Exception{
        //wait for home page to load
        tinyWait();

        /*Scenario 2 To Enter “Morgantown, WV” into the search box*/
        searchLocationInTextBox(driver, SearchActionPage.findSearchById, SearchValues.Location);

        /*Scenario 3 to Hit Enter on SearchButton*/
        hitEnterOnButton(driver, SearchActionPage.findSearchButtonToClick);

        /*To wait until the relevant SearchResult is loaded*/
        expectedResultWait(driver, SearchActionPage.searchResultCount);

        /*To validate the selected dropdown is Relevant Listings*/
        selectOptionFromDropDown(driver, SearchActionPage.sortOptionsByByxPath, SearchValues.dropDownListing);

        /*Scenario 4 to Validate the searchCount is greater than zero*/
        int totalCount = validateTotalSearchResultCount(driver, SearchActionPage.searchResultCount);
        Assert.assertTrue("Search Results greater than zero",totalCount>0 );

        /*Scenario 5 to get the price of second search result*/
        int summaryPrice = getPriceOfNthElement(driver, SearchActionPage.searchByClassName, SearchValues.nthElement, SearchActionPage.searchForPrice);

        /*To wait until the relevant SearchResult is loaded*/
        expectedResultWait(driver, SearchActionPage.searchListingByxPath);

        /*Scenario 6 to verify the price displayed on the search result and clicked on matches the price on Details page*/
        int listPrice = validateSummaryAndNthElementPrices(driver, summaryPrice, SearchActionPage.searchListingByxPath);
        Assert.assertEquals(summaryPrice, listPrice);

    }

}

