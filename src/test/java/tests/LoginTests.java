package tests;


import business.pages.HeaderMenu;
import org.junit.jupiter.api.Test;

import static business.pages.HeaderMenu.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests extends BaseTest {

    @Test
    public void logInWithValidCredentials() {
        open("https://baellerry.ua/");
        HeaderMenu page = page(HeaderMenu.class);
        logIn("depayev278@forfity.com", "plokijuh1");
        logOut();
    }

    @Test()
    public void logInWithInvalidCredentials() {
        open("https://baellerry.ua/");
        logIn("test", "test");
        validateAlertToast("Неправильно заполнены поля E-Mail и/или пароль!");
    }

    @Test
    public void loginAndValidateProfileMenu() {
        open("https://baellerry.ua/");
        logIn("depayev278@forfity.com", "plokijuh1");
        validateProfileMenu();
    }

    @Test
    public void validateLogOut() {
        open("https://baellerry.ua/");
        logIn("depayev278@forfity.com", "plokijuh1");
        logOut();
    }
}
