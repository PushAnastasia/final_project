package baellerry.common.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class HeaderMenu extends BasePage {
    static By profileButton = By.id("fm-account-dropdown");
    static By loginModal = By.className("modal-content");
    static By emailField = By.id("emailLoginInput");
    static By passwordField = By.id("passwordLoginInput");
    static By loginButton = By.id("popup-login-button");
    static By profileMenu = By.cssSelector("#fm-account-dropdown > ul");
    static By logOutButton = By.linkText("Выход");
    static By headerCartCount = By.cssSelector("#cart > span.fm-menu-buttons-index.fm-menu-cart-index");
    static By cartButton = By.id("cart");
    static By sideMenuCart = By.id("fm_sidebar");
    static By sideMenuCartIncreaseButton = By.xpath("html/body/div[2]/div[2]/div[2]/div/div[2]/div/button[2]");
    static By sideMenuCartDecreaseButton = By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div/button[1]");
    static By sideMenuCartCount = By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div/input[4]");
    static By sideMenuCloseButton = By.className("fm_sidebar-title-close");
    static By sideMenuRemoveButton = By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/button");
    static By sideMenuEmptyImage = By.className("fm_mobile_menu_cart-empty");
    static By sideMenuCreateOrderButton = By.linkText("Оформить заказ");
    static By headerWishListCount = By.cssSelector("body > div.menu-row.sticky-top > div > div > div.col-lg-9.fm-menu-buttons-list-col.d-flex.justify-content-between > div.fm-menu-buttons-list.d-flex > button.fm-menu-buttons-item.fm-menu-buttons-wishlist > span > span");
    static By headerWishListButton = By.cssSelector("body > div.menu-row.sticky-top > div > div > div.col-lg-9.fm-menu-buttons-list-col.d-flex.justify-content-between > div.fm-menu-buttons-list.d-flex > button.fm-menu-buttons-item.fm-menu-buttons-wishlist");
    static By logoButton = By.id("logo");
    static By searchField = By.id("input_search");
    static By searchButton = By.id("fm-search-button");

    @Step("Enter credentials into login modal and submit.")
    public static void logIn(String email, String password) {
        Configuration.timeout = 10000;
        $(profileButton).click();
        $(loginModal).should(Condition.visible);
        $(emailField).sendKeys(email);
        $(passwordField).sendKeys(password);
        $(loginButton).click();
    }
    @Step("Validate that toast message contains required message.")
    public static void validateAlertToast(String message) {
        $(alertToast).should(Condition.appear);
        $(alertToastText).should(Condition.ownText(message));
    }

    @Step("Validate that profile menu is available and contains required items.")
    public static void validateProfileMenu() {
        $(profileButton).should(Condition.visible);
        $(profileButton).click();
        $(profileMenu).should(Condition.visible);
        $$(By.className("dropdown-item")).shouldHave(size(5));
    }

    @Step("Open profile menu, click logout button and verify that on Logout page.")
    public static void logOut() {
        $(profileButton).click();
        $(profileMenu).should(Condition.visible);
        $(logOutButton).click();
        $("title").shouldHave(attribute("text", "Выход"));
    }

    @Step("Verify that cart count in header menu equals required value.")
    public static void verifyHeaderCartCount(String count) {
        $(headerCartCount).shouldHave(Condition.ownText(count));
    }

    @Step("Open side bar menu cart.")
    public static void openSideMenuCart() {
        $(cartButton).click();
        $(sideMenuCart).should(Condition.visible);
    }

    @Step("Close side bar menu cart.")
    public static void closeSideMenuCart() {
        $(sideMenuCloseButton).click();
        $(sideMenuCart).should(Condition.not(Condition.visible));
    }

    public static void changeProductCount(Boolean increase) {
        int count = Integer.valueOf($(sideMenuCartCount).getValue());
        if (increase) {
            $(sideMenuCartIncreaseButton).click();
            $(sideMenuCartCount).shouldHave(Condition.value(String.valueOf(count + 1)));
        } else {
            $(sideMenuCartDecreaseButton).click();
            $(sideMenuCartCount).shouldHave(Condition.value(String.valueOf(count - 1)));
        }
    }

    @Step("Increase the amount of products in side bar menu cart by one.")
    public static void increaseProductCountInSideMenu() {
        changeProductCount(true);
    }

    @Step("Decrease the amount of products in side bar menu cart by one.")
    public static void decreaseProductCountInSideMenu() {
        changeProductCount(false);
    }

    @Step("Enter the required amount in the product count in side menu cart.")
    public static void changeProductCountManuallyInSideMenu(String newCount) {
        $(sideMenuCartCount).clear();
        $(sideMenuCartCount).sendKeys(newCount);
        $(sideMenuCartCount).click();
        $(sideMenuCartCount).shouldHave(Condition.value(newCount));
    }

    @Step("Remove product from side menu cart")
    public static void removeProductFromSideMenu() {
        $(sideMenuRemoveButton).click();
        $(sideMenuEmptyImage).should(Condition.visible);
    }

    @Step("Click Create order button in side menu cart")
    public static void goToCreateOrderFromSideMenuCart() {
        $(sideMenuCreateOrderButton).click();
        $("title").shouldHave(attribute("text", "Оформление заказа"));
    }

    @Step("Verify that wish list count in header menu equals required value.")
    public static void verifyHeaderWishListCount(String count) {
        $(headerWishListCount).shouldHave(Condition.ownText(count));
    }

    @Step("Navigate to Wish list page.")
    public static void openWishListPage() {
        $(headerWishListButton).click();
    }

    @Step("Go to Main page via Logo button.")
    public static void goToMainPageViaLogo() {
        $(logoButton).click();
        $("title").shouldHave(attribute("text", "Кожаные аксессуары Baellerry - официальный магазин в Украине"));
    }

    @Step("Search for some product")
    public static void searchForProduct(String searchInput) {
        $(searchField).clear();
        $(searchField).sendKeys(searchInput);
        $(searchButton).click();
    }
}
