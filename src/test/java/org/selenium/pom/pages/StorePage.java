package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {
    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector("#main > div > header > h1");

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private ProductThumbnail productThumbnail;


    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    public Boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    private StorePage enterTextInSearchFld(String txt) {
        WebElement e = waitForElementToBeVisible(searchFld);
        e.sendKeys(txt);
        return this;
    }

    private StorePage clickSearchBtn() {
        WebElement e = waitForElementToBeVisible(searchBtn);
        e.click();
        return this;
    }

    public StorePage search(String txt) {
        //   driver.findElement(searchFld).sendKeys(txt);
        //    driver.findElement(searchBtn).click();
        enterTextInSearchFld(txt).clickSearchBtn();
        return this;
    }

    public String getTile() {
        WebElement e = waitForElementToBeVisible(title);
        return e.getText();
    }


}
