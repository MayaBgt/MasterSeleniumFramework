package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {

    private MyHeader myHeader;
    private ProductThumbnail productThumbnail;
    private final By productTitle = By.xpath(("//h2[@class='woocommerce-loop-product__title']"));



    public HomePage(WebDriver driver) {
        super(driver);
        myHeader = new MyHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }


    public HomePage load() {
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private By getProductTitleInThumbnail(String productName) {
        return By.xpath("//h2[text()="  + productName + "]");
    }
    public HomePage clickProductTitleInThumbnail(String productName) {
        By productTitleInThumbnail = getProductTitleInThumbnail(productName);
        WebElement e = waitForElementToBeVisible(productTitleInThumbnail);
        e.click();
        return this;
    }
}
