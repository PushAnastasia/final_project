package baellerry.common.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage extends BasePage {

    static By innerSearchField = By.id("input-search");
    static By searchResultItems = By.className("fm-module-img");
    static By noResultsMessage = By.cssSelector("#content > div.p-4 > p");
    static By categoryDropdown = By.name("category_id");
    static By categoryDropdownOptions = By.cssSelector("div.form-group > div > select > option");
    static By innerSearchButton = By.id("button-search");
    static By productTitles = By.cssSelector(".fm-module-title >a");
    static By buyButtons = By.className("fm-product-btn");
    static By heartIcons = By.className("fm-module-buttons-wishlist");
    static By quickViewIcons = By.className("fm-module-buttons-quickview");
    static By itemLimitDropdown = By.id("input-limit");
    static By itemLimitDropdownOptions = By.cssSelector("#input-limit > option");
    static By listLayoutItem = By.className("product-list");
    static By gridLayoutItem = By.className("product-grid");
    static By listViewButton = By.id("list-view");
    static By gridViewButton = By.id("grid-view");

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

    @Step("Open Quick View modal for random product")
    public static void openQuickViewModalForRandomProduct() {
        int randomIndex = getRandomIndex($$(quickViewIcons).size());
        $$(quickViewIcons).get(randomIndex).hover();
        $$(quickViewIcons).get(randomIndex).click();
        $(quickViewModal).shouldBe(Condition.visible);
    }

    @Step("Change the product items limit per page")
    public static void setProductLimitPerPage(String limit) {
        $(itemLimitDropdown).click();
        SelenideElement option = $$(itemLimitDropdownOptions).stream().filter(s -> s.getText().contains(limit))
                .findFirst().orElseThrow(RuntimeException::new);
        option.click();
    }

    @Step("Verify product items count on page")
    public static void verifyProductItemsCount(int count) {
        $$(searchResultItems).shouldHave(CollectionCondition.size(count));
    }

    @Step("Change view to List and verify the layout")
    public static void selectListViewAndVerifyLayout() {
        $(listViewButton).click();
        $$(listLayoutItem).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Step("Change view to Grid and verify the layout")
    public static void selectGridViewAndVerifyLayout() {
        $(gridViewButton).click();
        $$(gridLayoutItem).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
