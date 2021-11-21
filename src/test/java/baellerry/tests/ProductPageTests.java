package baellerry.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;

import static baellerry.common.pages.Categories.*;
import static baellerry.common.pages.HeaderMenu.*;
import static baellerry.common.pages.MainPage.selectCategoryByTitle;
import static baellerry.common.pages.ProductPage.*;
import static baellerry.common.pages.WishListPage.*;
import static baellerry.common.steps.NavigationSteps.selectProductByIndex;
import static baellerry.common.steps.NavigationSteps.selectRandomProductOnCategoryPage;

public class ProductPageTests extends BaseTest {

    @Test
    @Description("Navigate to Product Page from Category Page and put it into cart")
    public void navigateToProductViaCategoryAndPutToCart() {
        String productCount = "1";
        selectRandomProductOnCategoryPage(WOMEN_BAGS.getCategory());
        putProductInCart();
        verifyHeaderCartCount(productCount);
    }

    @RetryingTest(maxAttempts = 3, minSuccess = 1)
    @Description("Put product into cart open side menu cart and increase product count")
    public void increaseProductCountInSideMenuCart() {
        String productCount = "2";
        selectRandomProductOnCategoryPage(WOMEN_PURSES.getCategory());
        putProductInCart();
        openSideMenuCart();
        increaseProductCountInSideMenu();
        closeSideMenuCart();
        verifyHeaderCartCount(productCount);
    }

    @Test
    @Description("Put product into cart open side menu cart and decrease product count")
    public void decreaseProductCountInSideMenuCart() {
        String productCount = "1";
        int productIndex = 0;
        selectCategoryByTitle(MEN_WALLETS.getCategory());
        selectProductByIndex(productIndex);
        putProductInCart();
        openSideMenuCart();
        increaseProductCountInSideMenu();
        decreaseProductCountInSideMenu();
        closeSideMenuCart();
        verifyHeaderCartCount(productCount);
    }

    @Test
    @Description("Put product into cart open side menu cart and change product count using amount field")
    public void changeProductCountManuallyInSideMenuCart() {
        String productCount = "3";
        int productIndex = 0;
        selectCategoryByTitle(WOMEN_PURSES.getCategory());
        selectProductByIndex(productIndex);
        putProductInCart();
        openSideMenuCart();
        changeProductCountManuallyInSideMenu(productCount);
        closeSideMenuCart();
        verifyHeaderCartCount(productCount);
    }

    @RetryingTest(maxAttempts = 3, minSuccess = 1)
    @Description("Put product into cart open side menu cart and remove product from the cart")
    public void removeProductFromSideMenuCart() {
        String productCount = "0";
        selectRandomProductOnCategoryPage(MEN_BAGS.getCategory());
        putProductInCart();
        openSideMenuCart();
        removeProductFromSideMenu();
        closeSideMenuCart();
        verifyHeaderCartCount(productCount);
    }

    @Test
    @Description("Put product into cart and go to Order Page")
    public void putProductIntoCartAndGoToOrderPage() {
        selectRandomProductOnCategoryPage(MEN_WALLETS.getCategory());
        putProductInCart();
        openSideMenuCart();
        goToCreateOrderFromSideMenuCart();
    }

    @Test()
    @Description("Put product to Wish List and verify Wish List count in header")
    public void putProductToWishList() {
        String productCount ="1";
        selectRandomProductOnCategoryPage(MEN_BRACELETS.getCategory());
        putToWishList();
        verifyHeaderWishListCount(productCount);
    }

    @RetryingTest(maxAttempts = 3, minSuccess = 1)
    @Description("Put product to Wish List and open Wish List page")
    public void navigateToWishListPage() {
        String productCount ="1";
        selectRandomProductOnCategoryPage(BELT.getCategory());
        putToWishList();
        verifyHeaderWishListCount(productCount);
        openWishListPage();
        verifyWishListProductCount(Integer.parseInt(productCount));
    }

    @Test
    @Description("Increase product amount using up button and then put it into the cart")
    public void increaseProductAmountAndPutIntoCart() {
        String productCount ="2";
        int productIndex = 0;
        selectCategoryByTitle(BELT.getCategory());
        selectProductByIndex(productIndex);
        increaseProductAmount();
        putProductInCart();
        verifyHeaderCartCount(productCount);
    }

    @RetryingTest(maxAttempts = 3, minSuccess = 1)
    @Description("Set product amount using count field and put it into the cart")
    public void changeProductAmountAndPutIntoCart() {
        String productCount ="2";
        selectRandomProductOnCategoryPage(WOMEN_PURSES.getCategory());
        changeProductAmountManually(productCount);
        putProductInCart();
        verifyHeaderCartCount(productCount);
    }
}
