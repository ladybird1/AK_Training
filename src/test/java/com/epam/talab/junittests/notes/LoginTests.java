package com.epam.talab.junittests.notes;

import com.epam.talab.config.DriverProvider;
import com.epam.talab.page_objects_demos.LoginPageLoginAsUser;
import com.epam.talab.page_objects_demos.MyNotes;
import com.epam.talab.page_objects_demos.WelcomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {
    @Test
    public void loginWithValidCredentials(){
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.navigate();
        welcomePage.clickLoginButton();

        LoginPageLoginAsUser loginPageLoginAsUser = new LoginPageLoginAsUser();
        loginPageLoginAsUser.fillEmailAddress("fortest12@gmail.com");
        loginPageLoginAsUser.fillPassword("fortest12");
        loginPageLoginAsUser.clickLogin();

        MyNotes myNotes = new MyNotes();
        assertTrue(myNotes.isLogoutButtonVisible());

        String currentUrl = DriverProvider.getDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("/notes/app"));


        System.out.println();

    }
    @AfterEach
    public void closeBrowser() {
        DriverProvider.releaseDriver();
    }}
