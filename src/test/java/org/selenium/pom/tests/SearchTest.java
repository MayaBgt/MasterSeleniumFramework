package org.selenium.pom.tests;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch() {
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver()).
                load().
                searchPartialMatch(searchFor);
        Assert.assertEquals(storePage.getTile(), "Search results: “" + searchFor + "”");
    }

    @Test
    public void searchWithExactMatch() {
        String searchFor = "Blue Denim Shorts";
        ProductPage productPage = new StorePage(getDriver()).
                load().
                searchExactMatch(searchFor);
        Assert.assertEquals(productPage.getProductPageTitle(), searchFor);
    }

    @Test
    public void searchNonExistingProduct() {
        String searchFor = "Big Blue Bounty";
        String noMatchesFoundMessage = "No products were found matching your selection.";
        StorePage storePage = new StorePage(getDriver()).
                load().
                searchPartialMatch(searchFor);
        Assert.assertEquals(storePage.getNonExistingProductText(), noMatchesFoundMessage);
    }
}
