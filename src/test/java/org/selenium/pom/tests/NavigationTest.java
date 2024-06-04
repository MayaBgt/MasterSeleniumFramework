package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void natigateFromHomeToStoreUsingMainMenu() {

        StorePage storePage = new HomePage(getDriver()).
                load().getMyHeader().navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTile(), "Store");
    }
}
