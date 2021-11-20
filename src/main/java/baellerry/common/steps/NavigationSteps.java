package baellerry.common.steps;

import baellerry.common.pages.BasePage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static baellerry.common.pages.MainPage.selectCategoryByTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NavigationSteps extends BasePage {

    @Step("Select some category and then choose random product.")
    public static void selectRandomProductOnCategoryPage(String category) {
        selectCategoryByTitle(category);
        selectProductByIndex(getRandomIndex($$(By.className("fm-module-img")).size()));
    }

    @Step("Select product from the category page by index")
    public static void selectProductByIndex(int index) {
        $$(By.className("fm-module-img")).get(index).click();
        $(By.id("product-product")).shouldBe(Condition.visible);
    }
}
