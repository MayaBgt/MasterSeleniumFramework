package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CouponTest extends BaseTest {
    @Test
    public void validateFreeShipCouponForFreeShipping () {
        String couponCode = "freeship";
        CartPage cartPage = new CartPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());

        Assert.assertEquals(cartPage.load().applyFreeShipmentCouponCode(couponCode), "Free shipping");
    }

    @Test
    public void validateOffCartFiveCouponForFiveDollarsDiscount () {
        String couponCode = "offcart5";
        CartPage cartPage = new CartPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());

        Assert.assertEquals(cartPage.load().applyOffCartFiveCouponCode(couponCode), "-$5.00 [Remove]");
    }

    @Test
    public void validateOffTwentyFivePercentCouponForTwentyFivePercentDiscount () {
        String couponCode = "off25";
        CartPage cartPage = new CartPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());

        Assert.assertEquals(cartPage.load().applyOffTwentyFivePercentCouponCode(couponCode),
                "-$" + cartPage.calculateTwentyFivePercentDiscount() + " [Remove]");
    }



}


