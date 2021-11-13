package baellerry.common.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {
    static By innerSearchField = By.id("input-search");
    static By searchResultItems = By.className("fm-module-img");
    static By noResultsMessage = By.cssSelector("#content > div.p-4 > p");
    static By categoryDropdown = By.name("category_id");
    static By categoryDropdownOptions = By.cssSelector("div.form-group > div > select > option");
    static By innerSearchButton = By.id("button-search");
    static By productTitles = By.cssSelector(".fm-module-title >a");
    static By buyButtons = By.className("fm-product-btn");
    static By heartIcons = By.className("fm-module-buttons-wishlist");

    @Step("Verify that Inner Search field contains required value.")
    public static void verifyInnerSearchValue(String value) {
        $(innerSearchField).getValue().equals(value);
    }

    @Step("Verify that Search page contains results.")
    public static void verifySearchContainsResults() {
        $$(searchResultItems).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
    }

    @Step("Verify that Search contains corresponding message for query without results.")
    public static void verifySearchPageWithoutResults() {
        String message = "Нет товаров, которые соответствуют критериям поиска.";
        $$(searchResultItems).shouldHave(CollectionCondition.size(0));
        $(noResultsMessage).shouldHave(Condition.ownText(message));
    }

    @Step("Select product category in search results")
    public static void selectProductCategoryForSearch(String categoryName) {
        $(categoryDropdown).click();
        SelenideElement category = $$(categoryDropdownOptions).stream().filter(s -> s.getText().contains(categoryName))
                .findFirst().orElseThrow(RuntimeException::new);
        category.click();
        $(innerSearchButton).click();
    }

    @Step("Verify that all products in search results contain key word.")
    public static void verifyKeyWordInSearchResults(String keyWord) {
        $$(productTitles).stream().forEach(s -> s.shouldHave(Condition.ownText(keyWord)));
    }

    @Step("Put random product into cart")
    public static void putRandomProductToCart() {
        int randomIndex = getRandomIndex($$(buyButtons).size());
        $$(buyButtons).get(randomIndex).click();
    }

    @Step("Put random product into wish list")
    public static void putRandomProductToWishList() {
        int randomIndex = getRandomIndex($$(heartIcons).size());
        $$(heartIcons).get(randomIndex).hover();
        $$(heartIcons).get(randomIndex).click();
    }

    private static int getRandomIndex(int maxIndex) {
        Random random = new Random();
        return random.ints(0, maxIndex).findFirst().getAsInt();
    }
}
