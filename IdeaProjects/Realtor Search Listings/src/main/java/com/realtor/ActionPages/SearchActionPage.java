package com.realtor.ActionPages;

/**
 * Created by pavan on 17-Jun-18.
 */
public class SearchActionPage {

    public static final String findSearchById = "searchBox";

    public static final String findSearchButtonToClick = "js-searchButton";

    public static final String searchResultCount = "//div[contains(@class, '-sort')]/span[contains(text(),' Homes')]";

    public static final String searchByClassName = "(//span[contains(@class, 'data-price')])";

    public static final String searchForPrice = "data-price";

    public static final String sortOptionsByByxPath = "//select[@id='srp-sortby']";

    public static final String searchListingByxPath = "//span[@itemprop='price']";
}
