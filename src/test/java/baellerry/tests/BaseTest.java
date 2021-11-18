package baellerry.tests;

import baellerry.utils.extensions.LoggingPostProcessExtension;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import baellerry.utils.DriverProvider;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.*;

@ExtendWith(LoggingPostProcessExtension.class)
public class BaseTest {
    private Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

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
        Selenide.closeWindow();
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
