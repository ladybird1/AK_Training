package com.epam.talab.junittests.notes;

import com.epam.talab.config.DriverProvider;
import com.epam.talab.config.ProjectProperties;
import com.epam.talab.page_objects_demos.MyNotesPage;
import com.epam.talab.page_objects_demos.NotesLoginPage;
import com.epam.talab.page_objects_demos.WelcomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends NotesBaseTest {
    @Test
    public void loginWithValidCredentials(){
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.navigate();
        welcomePage.clickLoginButton();

        NotesLoginPage notesLoginPage = new NotesLoginPage();
        notesLoginPage.login(ProjectProperties.getValue("valid_email"),
                ProjectProperties.getValue("valid_password"));

        MyNotesPage myNotes = new MyNotesPage();
        assertTrue(myNotes.isLogoutButtonVisible());

        String currentUrl = DriverProvider.getDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("/notes/app"));

    }
    @Test
    @DisplayName("Login with invalid password fails")
    public void loginWithInvalidPassword(){
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.navigate();
        welcomePage.clickLoginButton();

        NotesLoginPage notesLoginPage = new NotesLoginPage();
        notesLoginPage.login(ProjectProperties.getValue("valid_email"),
                ProjectProperties.getValue("invalid_password"));
        assertTrue(notesLoginPage.isErrorMessageVisible(), "Incorrect email address or password message is not visible");

        String currentUrl = DriverProvider.getDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("/app/login"));
    }

    @Test
    public void loginWithInvalidEmailAndPassword(){
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.navigate();
        welcomePage.clickLoginButton();

        NotesLoginPage notesLoginPage = new NotesLoginPage();
        notesLoginPage.login(ProjectProperties.getValue("invalid_email"),
                ProjectProperties.getValue("invalid_password"));
        assertTrue(notesLoginPage.isErrorMessageVisible(), "Incorrect email address or password message is not visible");
    }

    @Test
    @DisplayName("Login form with empty fields")
    public void loginFormWithEmptyFields(){
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.navigate();
        welcomePage.clickLoginButton();

        NotesLoginPage notesLoginPage = new NotesLoginPage();
        notesLoginPage.clickLogin();
        assertTrue(notesLoginPage.isEmailErrorMessageVisible(), "Email validation message is not visible");
        assertTrue(notesLoginPage.isPasswordErrorMessageVisible(), "Email validation message is not visible");
    }

    @ParameterizedTest
    @DisplayName("Log in with an invalid email format")
    @ValueSource(strings = {"test@", "test.com"})
    public void loginWithInvalidEmail(String input){
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.navigate();
        welcomePage.clickLoginButton();

        NotesLoginPage notesLoginPage = new NotesLoginPage();
        notesLoginPage.fillEmailAddress(input);
        notesLoginPage.fillPassword(ProjectProperties.getValue("valid_password"));
        notesLoginPage.clickLogin();

        assertTrue(notesLoginPage.isEmailInvalidHintVisible(), "Email invalid validation message is not visible");

    }

}
