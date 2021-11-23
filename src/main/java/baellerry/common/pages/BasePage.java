package baellerry.common.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    static By alertToast = By.className("alert-block");
    static By alertToastText = By.className("fm-alert-text");
    static By quickViewModal = By.id("fm-quickview");
    static By quickViewBuyButton = By.id("oct-popup-button-cart");
    static By quickViewModalCloseButton = By.cssSelector("#fm-quickview-modal > div > div > div.modal-header > button");
    static By quickViewModalWishListButton = By.className("fm-product-buttons-link-wishlist");
    static By quickOrderButton = By.cssSelector(".fm-product-btn-group > button:nth-child(2)");
    static By quickOrderModal = By.id("one-click-main-block");

    public static int getRandomIndex(int maxIndex) {
        Random random = new Random();
        return random.ints(0, maxIndex).findFirst().getAsInt();
    }

    @Step("Put product to cart using Buy button of Quick View modal")
    public static void quickViewPutToCart() {
        $(quickViewBuyButton).click();
        $(alertToast).shouldBe(Condition.visible);
    }

    @Step("Close Quick View modal")
    public static void closeQuickViewModal() {
        $(quickViewModalCloseButton).click();
        $(quickViewModal).shouldBe(Condition.not(Condition.visible));
    }

    @Step("Put product to wish list using Wish List button of Quick View modal")
    public static void quickViewPutToWishList() {
        $(quickViewModalWishListButton).click();
        $(alertToast).shouldBe(Condition.visible);
    }

    @Step("Click Quick Order button and verify Quick Order modal is displayed")
    public static void openQuickOrderModalFromQuickView() {
        $(quickOrderButton).click();
        $(quickOrderModal).shouldBe(Condition.visible);
    }
}
