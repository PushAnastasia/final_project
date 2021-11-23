package baellerry.common.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WishListPage {

    static By productItem = By.className("fm-acc-info-block");
    static By deleteItemButton = By.className("fm-acc-info-link-del");
    static By emptyWishListMessage = By.cssSelector("#content > div > div > p");

    @Step("Verify the count of products in a Wish list.")
    public static void verifyWishListProductCount(int count) {
        $$(productItem).shouldHave(CollectionCondition.size(count));
    }

    @Step("Remove an item from a Wish list by index.")
    public static void removeItemFromWishListByIndex(int index) {
        $$(deleteItemButton).get(index).click();
    }

    @Step("Verify that Wish list is empty.")
    public static void verifyWishListIsEmpty() {
        verifyWishListProductCount(0);
        $(emptyWishListMessage).should(Condition.visible);
    }
}
