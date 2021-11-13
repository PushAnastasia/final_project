package baellerry.common.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CartModal {
    static By cartModal = By.id("fm-cart-modal");
    static By closeCartButton = By.cssSelector("#fm-popup-cart > div > div > div.modal-header > button");

    @Step("Close cart modal")
    public static void closeCartModal() {
        $(cartModal).should(Condition.visible);
        $(closeCartButton).click();
        $(cartModal).should(Condition.not(Condition.visible));
    }
}
