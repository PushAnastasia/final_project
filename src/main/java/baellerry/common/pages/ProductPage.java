package baellerry.common.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static baellerry.common.pages.CartModal.closeCartModal;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends BasePage {

    static By buyButton = By.id("button-cart");
    static By wishListButton = By.linkText("В закладки");
    static By increaseAmountButton = By.xpath("//*[@id=\"product\"]/div[2]/div[1]/div[1]/div/button[2]");
    static By decreaseAmountButton = By.xpath("//*[@id=\"product\"]/div[2]/div[1]/div[1]/div/button[1]");
    static By amountField = By.id("input-quantity");

    @Step("Put product into cart using Buy button on Product page")
    public static void putProductInCart() {
        $(buyButton).click();
        closeCartModal();
    }

    @Step("Put product into Wish List using Wish List button on Product page")
    public static void putToWishList() {
        $(wishListButton).click();
        $(wishListButton).click();
        $(alertToast).should(Condition.visible);
        $(alertToastText).shouldHave(Condition.ownText("Вы добавили"));
        $(alertToast).should(Condition.disappear);
    }

    public static void changeProductAmount(Boolean increase) {
        int count = Integer.valueOf($(amountField).getValue());
        if (increase) {
            $(increaseAmountButton).click();
            $(amountField).shouldHave(Condition.value(String.valueOf(count + 1)));
        } else {
            $(decreaseAmountButton).click();
            $(amountField).shouldHave(Condition.value(String.valueOf(count - 1)));
        }
    }

    @Step("Increase the amount of products in Cart Modal by one.")
    public static void increaseProductAmount() {
        changeProductAmount(true);
    }

    @Step("Decrease the amount of products in Cart Modal by one.")
    public static void decreaseProductAmount() {
        changeProductAmount(false);
    }

    @Step("Enter amount of products in Product Count field of Cart Modal.")
    public static void changeProductAmountManually(String amount) {
        $(amountField).clear();
        $(amountField).sendKeys(amount);
        $(amountField).click();
        $(amountField).shouldHave(Condition.value(amount));
    }
}
