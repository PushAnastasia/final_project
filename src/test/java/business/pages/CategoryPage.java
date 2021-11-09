package business.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoryPage {
    static By productItems = By.className("fm-module-item");

    public static void selectProductByIndex(int index) {
        $$(productItems).get(index).click();
        Configuration.timeout = 15000;
        //SelenideElement selenideElement = $(".fm-product-slide-box").waitUntil(Condition.visible, 15000);
        $(By.id("product-product")).shouldBe(Condition.visible);
    }
}
