package baellerry.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static baellerry.common.pages.HeaderMenu.*;

public class LoginTests extends BaseTest {

    @Test
    @Description("User should be able to login with valid credentials. Profile menu should be available for logged in user")
    public void logInWithValidCredentials() {
        String email = "depayev278@forfity.com";
        String password = "plokijuh1";
        logIn(email, password);
        validateProfileMenu();
    }

    @Test
    @Description("Validation message should be displayed after attempt to log in with invalid credentials")
    public void logInWithInvalidCredentials() {
        String validationMessage = "Неправильно заполнены поля E-Mail и/или пароль!";
        String email = "test";
        String password = "test";
        logIn(email, password);
        validateAlertToast(validationMessage);
    }

    @Test
    @Description("Logged in User should be able to log out.")
    public void validateLogOut() {
        String email = "depayev278@forfity.com";
        String password = "plokijuh1";
        logIn(email, password);
        logOut();
    }
}
