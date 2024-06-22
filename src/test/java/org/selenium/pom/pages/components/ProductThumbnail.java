package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.ProductPage;

import java.util.List;

public class ProductThumbnail extends BasePage {
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }


    private By getAddToCartBtnElement(String productName) {
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public ProductThumbnail clickAddToCartBtn(String productName) {
        By addToCartBtn = getAddToCartBtnElement(productName);
        WebElement e = waitForElementToBeVisible(addToCartBtn);
        e.click();
        return this;
    }

    public CartPage clickViewCart() {
        WebElement e = waitForElementToBeVisible(viewCartLink);
        e.click();
        return new CartPage(driver);
    }

    private By getProductNameElement(String productName) {
        String productNameFormatted = productName.replace(" ", "-").toLowerCase();
        return By.xpath("//a[@href= 'https://askomdch.com/product/" + productNameFormatted + "/']");
    }
    public ProductPage clickProductNameElement(String productName) {
        WebElement e = waitForElementToBeVisible(getProductNameElement(productName));
        e.click();
        return new ProductPage(driver);
    }



}
