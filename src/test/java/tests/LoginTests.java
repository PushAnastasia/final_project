package tests;


import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static business.pages.HeaderMenu.*;

public class LoginTests extends BaseTest {

    @Description("User should be able to login with valid credentials. Profile menu should be available for logged in user")
    @Test
    public void logInWithValidCredentials() {
        String email = "depayev278@forfity.com";
        String password = "plokijuh1";
        logIn(email, password);
        validateProfileMenu();
    }

    @Description("Validation message should be displayed after attempt to log in with invalid credentials")
    @Test
    public void logInWithInvalidCredentials() {
        String validationMessage = "Неправильно заполнены поля E-Mail и/или пароль!";
        logIn("test", "test");
        validateAlertToast(validationMessage);
    }

    @Description("Logged in User should be able to log out.")
    @Test
    public void validateLogOut() {
        String email = "depayev278@forfity.com";
        String password = "plokijuh1";
        logIn(email, password);
        logOut();
    }
}
