package ru.levelup.at.homework5.steps;

import org.openqa.selenium.WebDriver;

public class ActionStep extends BaseStep {

    public ActionStep(WebDriver driver) {
        super(driver);
    }

    public void loginToMail(String login, String password) {
        loginAndRegistrationPage.open();
        loginAndRegistrationPage.clickLogInButton();
        loginAndRegistrationPage.sendLoginToLoginFrameField(login);
        loginAndRegistrationPage.sendPasswordToPasswordFrameField(password);
    }


    public void createLetter(String address, String subject, String letterText) {
        if (mainMailPage.isPopUp()) {
            mainMailPage.closePopUp();
        }
        mainMailPage.clickCreateMailButton();
        mainMailPage.sendLoginToAddressField(address);
        mainMailPage.sendSubjectToSubjectMailField(subject);
        mainMailPage.sendMessageToBodyMailField(letterText);
    }

    public void deleteLetter() {
        mainMailPage.clickDeleteMailButton();
    }

    public void saveLetter() {
        mainMailPage.clickSaveMailButton();
        mainMailPage.clickCloseMailButton();
    }

    public void sendLetter() {
        mainMailPage.sendLetterButton();
        mainMailPage.closeSentLetter();
    }

    public void logOut() {
        mainMailPage.clickProfileButton();
        mainMailPage.clickLogOutButton();
    }
}
