package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class ProductPage extends BasePage {

    private final By productPageTitle = By.cssSelector(".product_title.entry-title");
    private final By addToCartBtn = By.xpath("//button[@name='add-to-cart']");
    private final By viewCartBtn = By.xpath("//*[@id='main']/div/div[1]/div/a");


    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public ProductPage loadProductPage(String productName) {
        String productNameFormatted = productName.replace(" ", "-").toLowerCase();
        load("/product/" + productNameFormatted + "/");
        return this;
    }

    public Boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("/product"));
    }

    public String getProductPageTitle() {
        WebElement e = waitForElementToBeVisible(productPageTitle);
        return e.getText();
    }

    public ProductPage clickAddToCartBtn() {
        WebElement e = waitForElementToBeVisible(addToCartBtn);
        e.click();
        return this;
    }

    public String getViewCartBtn() {
        WebElement e = waitForElementToBeVisible(viewCartBtn);
        return e.getText();
    }


}
