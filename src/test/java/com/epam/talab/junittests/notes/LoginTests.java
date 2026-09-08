package com.epam.talab.junittests.notes;

import com.epam.talab.config.DriverProvider;
import com.epam.talab.config.ProjectProperties;
import com.epam.talab.page_objects_demos.NotesLoginPage;
import com.epam.talab.page_objects_demos.MyNotesPage;
import com.epam.talab.page_objects_demos.WelcomePage;
import org.junit.jupiter.api.Test;

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
}
