package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.pages.components.ProductThumbnail;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationTest extends BaseTest {

    @Test
    public void navigateFromHomeToStoreUsingMainMenu() {

        StorePage storePage = new HomePage(getDriver()).
                load().getMyHeader().navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTile(), "Store");
    }

    @Test
    public void navigateFromStoreToProductPage() throws IOException {
        Product product = new Product(1198);
        ProductPage productPage = new StorePage(getDriver()).
                load().
                clickProductTitle();
        productPage.isLoaded();

        Assert.assertEquals(productPage.getProductPageTitle(), "Anchor Bracelet");
    }

    @Test
    public void navigateFromHomeToFeaturedProductPage() throws IOException {
        Product product = new Product(1209);
        String productName = product.getName();
        ProductPage productpage = new HomePage(getDriver()).
                load().
                getProductThumbnail().
                clickProductNameElement(product.getName());
        productpage.isLoaded();
        Assert.assertEquals(productpage.getProductPageTitle(), productName);
    }
}