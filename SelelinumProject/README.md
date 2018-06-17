Realtor.com Search Listings

This projects automates the Realtor.com website search results page using Selenium webdriver in Java and Junit on Maven build.

Steps to execute:
 - Install IntelliJ IDEA, you can download from [IntelliJ IDEA/downloads](https://www.jetbrains.com/idea/download/#section=windows) to your local box.
 - Navigate to Menu -> VCS -> Git -> Clone
 - Give the [Git repository URL](https://github.com/pa1codehub/realtor_search_listings) and click on clone.
 - Import the project from local machine.
 - mvn clean install is the command to build the project.
 - Name of the browser can be updated in the <browser> tag in pom.xml which will be passed as an environment variable.
 - Run the SearchUtilTest file which satisfies all the scenarios below:
    - Navigates to Realtor.com
    - Waits for page to be loaded
    - Location is given in the text box
    - Search button will be hit once the location is loaded.
    - Waits for the results to load in the listings page.
    - Relevant Listings option is selected once the page is loaded.
    - Validates the total number of records.
    - Captures the price of the second search results and clicks on it.
    - Validates the price in the search results page and view details page.
 
