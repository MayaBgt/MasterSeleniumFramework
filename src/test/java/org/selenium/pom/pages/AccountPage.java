package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

public class AccountPage extends BasePage {

    private MyHeader myHeader;
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.xpath("//button[@name = 'login']");
    private final By errorMessage = By.className("woocommerce-error");


    public AccountPage(WebDriver driver) {
        super(driver);
        myHeader = new MyHeader(driver);
    }


    public AccountPage load() {
        load("/account/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public AccountPage enterUsername() {
        WebElement e = waitForElementToBeVisible(usernameFld);
        e.sendKeys("eagle");
        return this;
    }
    public AccountPage clickLoginBtn() {
        WebElement e = waitForElementToBeVisible(loginBtn);
        e.click();
        return this;
    }

    public String getErrorMessageText() {
        WebElement e = waitForElementToBeVisible(errorMessage);
        return e.getText();
    }

}
