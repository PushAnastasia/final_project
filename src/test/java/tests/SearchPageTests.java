package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static business.pages.CartModal.closeCartModal;
import static business.pages.HeaderMenu.*;
import static business.pages.SearchPage.*;

public class SearchPageTests extends BaseTest {

    @Description("Search for product using valid input and verify that it contains at least one result.")
    @Test
    public void searchForProductWithValidInput() {
        String validInput = "Сумка";
        searchForProduct(validInput);
        verifyInnerSearchValue(validInput);
        verifySearchContainsResults();
        verifyKeyWordInSearchResults(validInput);
    }

    @Description("Required message is displayed for query without corresponding search results")
    @Test
    public void searchForQueryWithoutResults() {
        String invalidInput = "Телевизор";
        searchForProduct(invalidInput );
        verifyInnerSearchValue(invalidInput );
        verifySearchPageWithoutResults();
    }

    @Description("Search results are narrowed down according to selected product category")
    @Test
    public void  selectProductCategoryForSearchResults() {
        String searchInput = "Сумка";
        String categoryName = "Сумки мужские";
        String keyWordInSearchResults = "мужская";
        searchForProduct(searchInput);
        selectProductCategoryForSearch(categoryName);
        verifyKeyWordInSearchResults(keyWordInSearchResults);
    }

    @Description("Put random product from search results to cart")
    @Test
    public void putRandomProductFromSearchToCart() {
        String searchInput = "кошелёк";
        String amount = "1";
        searchForProduct(searchInput);
        putRandomProductToCart();
        closeCartModal();
        verifyHeaderCartCount(amount);
    }

    @Description("Put random product from search results to wish list")
    @Test
    public void putRandomProductFromSearchToWishList() {
        String searchInput = "ремень";
        String amount = "1";
        searchForProduct(searchInput);
        putRandomProductToWishList();
        verifyHeaderWishListCount(amount);
    }
}
