package baellerry.common.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoryPage {
    static By productItems = By.className("fm-module-img");

    @Step("Select product from the category page by index")
    public static void selectProductByIndex(int index) {
        $$(productItems).get(index).click();
        $(By.id("product-product")).shouldBe(Condition.visible);
    }
}
