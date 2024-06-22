package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.base.BasePage;

import static java.sql.DriverManager.getDriver;

public class CartPage extends BasePage {

    /*    private final By productName = By.cssSelector("td[class = 'product-name'] a");
        private final By checkoutBtn = By.className("checkout-button"); */
    private final By cartHeader = By.cssSelector(".has-text-align-center");
    @FindBy(css = "td[class = 'product-name'] a")
    private WebElement productName;
    @FindBy(css = ".checkout-button")
    @CacheLookup
    private WebElement checkoutBtn;
    @FindBy(id = "coupon_code")
    private WebElement couponCodeFld;
    @FindBy(name = "apply_coupon")
    private WebElement couponCodeBtn;
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By freeShipmentMessage = By.xpath("//label[@for ='shipping_method_0_free_shipping2']");
    private final By offFiveDollarMessage = By.xpath("//td[@data-title = 'Coupon: offcart5']");
    private final By offTwentyFivePercentMessage = By.xpath("//td[@data-title = 'Coupon: off25']");
    private final By cartSubtotal = By.xpath("//*[@id=\"post-1220\"]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td/span/bdi");


    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage load() {
        load("/cart/");
        return this;
    }

    public Boolean isLoaded() {
        return wait.until(ExpectedConditions.textToBe(cartHeader, "Cart"));
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public CheckoutPage checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }

    public String applyFreeShipmentCouponCode(String couponCode) {
        couponCodeFld.sendKeys(couponCode);
        couponCodeBtn.click();
        waitForOverlaysToDisappear(overlay);
        WebElement e = waitForElementToBeVisible(freeShipmentMessage);
        return e.getText();
    }

    public String applyOffCartFiveCouponCode(String couponCode) {
        couponCodeFld.sendKeys(couponCode);
        couponCodeBtn.click();
        waitForOverlaysToDisappear(overlay);
        WebElement e = waitForElementToBeVisible(offFiveDollarMessage);
        return e.getText();
    }

    public String applyOffTwentyFivePercentCouponCode(String couponCode) {
        couponCodeFld.sendKeys(couponCode);
        couponCodeBtn.click();
        waitForOverlaysToDisappear(overlay);
        WebElement e = waitForElementToBeVisible(offTwentyFivePercentMessage);
        return e.getText();
    }

    public double calculateTwentyFivePercentDiscount() {
        WebElement cartSubtotalElement = waitForElementToBeVisible(cartSubtotal);
        String subtotalValueString = cartSubtotalElement.getText().replace("$", "");
        double subtotalValueInt = Double.parseDouble(subtotalValueString);
        return 0.25 * subtotalValueInt;
    }
}
