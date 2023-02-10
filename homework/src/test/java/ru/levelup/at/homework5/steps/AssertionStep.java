package ru.levelup.at.homework5.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;

public class AssertionStep extends BaseStep {

    public AssertionStep(WebDriver driver) {
        super(driver);
    }

    public void authorizationAsUserFromProperty(String login) {
        mainMailPage.clickProfileButton();
        assertThat(mainMailPage.getActualUserLogin()).isEqualTo(login);
    }

    public void checkDraftLetterAddressSubjectText(String address, String subject, String letterText) {
        mainMailPage.clickLastLetterInList();
        String addressFromLetter = mainMailPage.getAddressFromDraftLetter();
        assertThat(addressFromLetter).isEqualTo(address);
        String subjectFromLetter = mainMailPage.getSubjectFromDraftLetter();
        assertThat(subjectFromLetter).isEqualTo(subject);
        String textFromLetter = mainMailPage.getTextFromDraftLetter();
        assertThat(textFromLetter).isEqualTo(letterText);
    }

    public void checkLetterAddressSubjectText(String address, String subject, String letterText) {
        mainMailPage.clickLastLetterInList();
        String addressFromLetter = mainMailPage.getAddressFromLetter();
        assertThat(addressFromLetter).isEqualTo(address);
        String subjectFromLetter = mainMailPage.getSubjectFromLetter();
        assertThat(subjectFromLetter).isEqualTo(subject);
        String textFromLetter = mainMailPage.getLetterText();
        assertThat(textFromLetter).isEqualTo(letterText);
    }

    public void checkLetterInBin() {
        mainMailPage.clickMenuBinButton();
        assertTrue(mainMailPage.findLetterBySubject3());
    }

    public void checkLetterInIncoming() {
        mainMailPage.clickMenuIncomingButton();
        assertTrue(mainMailPage.findLetterBySubject3());
    }

    public void checkLetterInSent() {
        mainMailPage.clickMenuSentButton();
        assertTrue(mainMailPage.findLetterBySubject1InSent());
    }

    public void checkTestLetterInSent() {
        mainMailPage.clickMenuSentButton();
        assertTrue(mainMailPage.findLetterByTestSubjectInSent());
    }

    public void checkLetterInTest() {
        mainMailPage.clickMenuTestButton();
        assertTrue(mainMailPage.findLetterByTestSubject());
    }

    public void checkLetterNotInDraft() {
        mainMailPage.clickMenuDraftButton();
        //проверка на текст нет писем
        assertTrue(mainMailPage.isDraftEmpty());
    }

    public void checkLetterIsSaved() {
        mainMailPage.clickMenuDraftButton();
        assertTrue(mainMailPage.findLetterBySubject1());
    }

}
