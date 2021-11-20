package baellerry.tests;

import baellerry.common.pages.SearchPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static baellerry.common.pages.CartModal.closeCartModal;
import static baellerry.common.pages.HeaderMenu.*;
import static baellerry.common.pages.SearchPage.*;

public class SearchPageTests extends BaseTest {

    @Test
    @Description("Search for product using valid input and verify that it contains at least one result.")
    public void searchForProductWithValidInput() {
        String validInput = "Сумка";
        searchForProduct(validInput);
        verifyInnerSearchValue(validInput);
        verifySearchContainsResults();
        verifyKeyWordInSearchResults(validInput);
    }

    @Test
    @Description("Required message is displayed for query without corresponding search results")
    public void searchForQueryWithoutResults() {
        String invalidInput = "Телевизор";
        searchForProduct(invalidInput );
        verifyInnerSearchValue(invalidInput );
        verifySearchPageWithoutResults();
    }

    @Test
    @Description("Search results are narrowed down according to selected product category")
    public void  selectProductCategoryForSearchResults() {
        String searchInput = "Сумка";
        String categoryName = "Сумки мужские";
        String keyWordInSearchResults = "мужская";
        searchForProduct(searchInput);
        selectProductCategoryForSearch(categoryName);
        verifyKeyWordInSearchResults(keyWordInSearchResults);
    }

    @Test
    @Description("Put random product from search results to cart")
    public void putRandomProductFromSearchToCart() {
        String searchInput = "кошелёк";
        String amount = "1";
        searchForProduct(searchInput);
        putRandomProductToCart();
        closeCartModal();
        verifyHeaderCartCount(amount);
    }

    @Test
    @Description("Put random product from search results to wish list")
    public void putRandomProductFromSearchToWishList() {
        String searchInput = "ремень";
        String amount = "1";
        searchForProduct(searchInput);
        putRandomProductToWishList();
        verifyHeaderWishListCount(amount);
    }

    @Test
    @Description("Open Quick View modal for random product and put into Cart")
    public void openQuickViewModalAndPutToCart() {
        String searchInput = "кошелёк";
        String amount = "1";
        searchForProduct(searchInput);
        openQuickViewModalForRandomProduct();
        SearchPage.quickViewPutToCart();
        SearchPage.closeQuickViewModal();
        verifyHeaderCartCount(amount);
    }

    @Test
    @Description("Open Quick View modal for random product and put into Wish List")
    public void openQuickViewModalAndPutToWishList() {
        String searchInput = "сумка";
        String amount = "1";
        searchForProduct(searchInput);
        openQuickViewModalForRandomProduct();
        SearchPage.quickViewPutToWishList();
        SearchPage.closeQuickViewModal();
        verifyHeaderWishListCount(amount);
    }

    @Test
    @Description("Open Quick Order modal from Quick View modal")
    public void openQuickOrderModalFromQuickView() {
        String searchInput = "кошелёк";
        searchForProduct(searchInput);
        openQuickViewModalForRandomProduct();
        SearchPage.openQuickOrderModalFromQuickView();
    }

    @Test
    @Description("Change the product items limit per page and verify items count")
    public void changeProductLimitAndVerifyItemsCount() {
        String searchInput = "сумка";
        int count = 50;
        searchForProduct(searchInput);
        setProductLimitPerPage(String.valueOf(count));
        verifyProductItemsCount(count);
    }

    @Test
    @Description("Change the view to List and then back to Grid")
    public void changeViewToListAndBackToGrid() {
        String searchInput = "ремень";
        searchForProduct(searchInput);
        selectListViewAndVerifyLayout();
        selectGridViewAndVerifyLayout();
    }
}
