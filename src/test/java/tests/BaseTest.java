package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.DriverProvider;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeAll

    public static void suiteSetUp() {
        Configuration.startMaximized = true;
        Configuration.timeout = 15000;
    }

    @BeforeEach
    public void testSetUp() {
        DriverProvider driverProvider = new DriverProvider();
        Configuration.browser = driverProvider.getDriver();
        open("https://baellerry.ua/");

    }

    @AfterEach
    public void cleanUp() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

//    @DataProvider(name = "setInvalidLoginPassword")
//    public Object[][] setInvalidLoginPassword() {
//        return new Object[][]{
//                {"test", "test"},
//                {"test", ""},
//                {"", "test"},
//                {"", ""}
//        };
//    }
//
//    @DataProvider(name = "setCategoryAndProduct")
//    public Object[][] setCategoryAndProduct() {
//        return new Object[][]{
//                {"Кошельки и портмоне женские", 2},
//                {"Кошельки и портмоне мужские", 0},
//        };
//    }
}
