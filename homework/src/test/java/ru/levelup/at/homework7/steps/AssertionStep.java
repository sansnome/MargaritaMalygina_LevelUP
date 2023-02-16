package ru.levelup.at.homework7.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AssertionStep extends BaseStep {
    public AssertionStep(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка успешной авторизации пользователем {login}")
    public void authorizationAsUserFromProperty(String login) {
        mainMailPage.clickProfileButton();
        assertThat(mainMailPage.getActualUserLogin()).isEqualTo(login);
    }

    @Step("Проверка корректности введенных данных об адресате, теме и тексте в письме-черновике")
    public void checkDraftLetterAddressSubjectText(String address, String subject, String letterText) {
        mainMailPage.clickLastLetterInList();
        String addressFromLetter = mainMailPage.getAddressFromDraftLetter();
        assertThat(addressFromLetter).isEqualTo(address);
        String subjectFromLetter = mainMailPage.getSubjectFromDraftLetter();
        assertThat(subjectFromLetter).isEqualTo(subject);
        String textFromLetter = mainMailPage.getTextFromDraftLetter();
        assertThat(textFromLetter).isEqualTo(letterText);
    }

    @Step("Проверка корректности введенных данных об адресате, теме и тексте в письме")
    public void checkLetterAddressSubjectText(String address, String subject, String letterText) {
        mainMailPage.clickLastLetterInList();
        String addressFromLetter = mainMailPage.getAddressFromLetter();
        assertThat(addressFromLetter).isEqualTo(address);
        String subjectFromLetter = mainMailPage.getSubjectFromLetter();
        assertThat(subjectFromLetter).isEqualTo(subject);
        String textFromLetter = mainMailPage.getLetterText();
        assertThat(textFromLetter).isEqualTo(letterText);
    }

    @Step("Проверить, что письмо попало в Корзину")
    public void checkLetterInBin() {
        mainMailPage.clickMenuBinButton();
        assertTrue(mainMailPage.findLetterBySubject3());
    }

    @Step("Проверить, что письмо попало в папку Входящие")
    public void checkLetterInIncoming() {
        mainMailPage.clickMenuIncomingButton();
        assertTrue(mainMailPage.findLetterBySubject3());
    }

    @Step("Проверить, что письмо попало в Отправленные")
    public void checkLetterInSent() {
        mainMailPage.clickMenuSentButton();
        assertTrue(mainMailPage.findLetterBySubject1InSent());
    }

    @Step("Проверить, что письмо с темой Тест попало в Отправленные")
    public void checkTestLetterInSent() {
        mainMailPage.clickMenuSentButton();
        assertTrue(mainMailPage.findLetterByTestSubjectInSent());
    }

    @Step("Проверить, что письмо попало в папку с названием Тест")
    public void checkLetterInTest() {
        mainMailPage.clickMenuTestButton();
        assertTrue(mainMailPage.findLetterByTestSubject());
    }

    @Step("Проверить, что в Черновиках нет писем")
    public void checkLetterNotInDraft() {
        mainMailPage.clickMenuDraftButton();
        //проверка на текст нет писем
        assertTrue(mainMailPage.isDraftEmpty());
    }

    @Step("Проверить, что письмо сохранено в Черновиках")
    public void checkLetterIsSaved() {
        mainMailPage.clickMenuDraftButton();
        assertTrue(mainMailPage.findLetterBySubject1());
    }
}
