package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {

    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By billingAddress1Fld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostcodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotification = By.cssSelector(".woocommerce-notice");
    private final By loginLink = By.className("showlogin");
    private final By userNameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By countryDropdown = By.id("billing_country");
    private final By stateDropdown = By.id("billing_state");
    By alternateCountryDropdown = By.id("select2-billing_country-container");
    By alternateStateDropdown = By.id("select2-billing_state-container");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By productName = By.cssSelector("td[class='product-name']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load() {
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement e = waitForElementToBeVisible(firstNameFld);
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement e = waitForElementToBeVisible(lastNameFld);
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
//        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)));
//        select.selectByVisibleText(countryName);
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropdown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage selectState(String stateName) {
//        Select select = new Select(driver.findElement(stateDropdown));
//        select.selectByVisibleText(stateName);
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropdown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();

        return this;
    }

    public CheckoutPage enterBillingAddress1(String billingAddress1) {
        WebElement e = waitForElementToBeVisible(billingAddress1Fld);
        e.clear();
        e.sendKeys(billingAddress1);
        return this;
    }

    public CheckoutPage enterBillingCity(String billingCity) {
        WebElement e = waitForElementToBeVisible(billingCityFld);
        e.clear();
        e.sendKeys(billingCity);
        return this;
    }

    public CheckoutPage enterBillingPostcode(String billingPostcode) {
        WebElement e = waitForElementToBeVisible(billingPostcodeFld);
        e.clear();
        e.sendKeys(billingPostcode);
        return this;
    }

    public CheckoutPage enterBillingEmail(String billingEmail) {
        WebElement e = waitForElementToBeVisible(billingEmailFld);
        e.clear();
        e.sendKeys(billingEmail);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterBillingAddress1(billingAddress.getAddressLineOne()).
                enterBillingCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterBillingPostcode(billingAddress.getPostalCode()).
                enterBillingEmail(billingAddress.getEmail());
    }

    private CheckoutPage waitForLoginBtnToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckoutPage login(User user) {
        return enterUsername(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLoginBtn().waitForLoginBtnToDisappear();
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotification() {
        WebElement e = waitForElementToBeVisible(successNotification);
        return e.getText();
    }

    public CheckoutPage clickToLogin() {
        WebElement e = waitForElementToBeVisible(loginLink);
        e.click();
        return this;
    }

    public CheckoutPage enterUsername(String username) {
        WebElement e = waitForElementToBeVisible(userNameFld);
        e.sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        WebElement e = waitForElementToBeVisible(passwordFld);
        e.sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        WebElement e = waitForElementToBeVisible(loginBtn);
        e.click();
        return this;
    }

    public CheckoutPage selectDirectBankTransfer() {
        WebElement e = waitForElementToBeClickable(directBankTransferRadioBtn);
        if (!e.isSelected()) {
            e.click();
        }
        return this;
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

}

