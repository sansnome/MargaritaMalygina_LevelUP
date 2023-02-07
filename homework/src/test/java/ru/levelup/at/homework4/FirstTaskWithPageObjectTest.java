package ru.levelup.at.homework4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FirstTaskWithPageObjectTest extends CommonSeleniumTest {
    //Класс, выполняющий тест для задания 1 с  page object

    @Test
    void createAndSaveAndSendMessage() {
        //1. Войти в почту
        LogInAndRegistrationPage logInAndRegistrationPage = new LogInAndRegistrationPage(driver);
        logInAndRegistrationPage.open();
        logInAndRegistrationPage.clickLogInButton();

        logInAndRegistrationPage.sendLoginToLoginFrameField(MAIL_LOGIN);
        logInAndRegistrationPage.sendPasswordToPasswordFrameField(MAIL_PASSWORD);

        MainMailPage mainMailPage = new MainMailPage(driver);

        //2.Assert, что вход выполнен успешно
        mainMailPage.clickProfileButton();
        assertThat(mainMailPage.getActualUserLogin()).isEqualTo(MAIL_LOGIN);

        //3 Создать новое письмо (заполнить адресата, тему письма и тело)
        if (mainMailPage.isPopUp()) {
            mainMailPage.closePopUp();
        }
        mainMailPage.clickCreateMailButton();
        mainMailPage.sendLoginToAddressField(MAIL_LOGIN);
        mainMailPage.sendSubjectToSubjectMailField(MESSAGE_SUBJECT);
        mainMailPage.sendMessageToBodyMailField(MESSAGE_BODY);

        //4.Сохранить его как черновик
        mainMailPage.clickSaveMailButton();
        mainMailPage.clickCloseMailButton();

        //5.Verify, что письмо сохранено в черновиках
        mainMailPage.clickMenuDraftButton();
        assertTrue(mainMailPage.findLetterBySubject1());

        //6.Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        mainMailPage.clickLastLetterInList();
        String addressFromLetter = mainMailPage.getAddressFromDraftLetter();
        assertThat(addressFromLetter).isEqualTo(MAIL_LOGIN);
        String subjectFromLetter = mainMailPage.getSubjectFromDraftLetter();
        assertThat(subjectFromLetter).isEqualTo(MESSAGE_SUBJECT);
        String textFromLetter = mainMailPage.getTextFromDraftLetter();
        assertThat(textFromLetter).isEqualTo(MESSAGE_BODY);

        //7.Отправить письмо
        mainMailPage.sendLetterButton();
        mainMailPage.closeSentLetter();

        //8.Verify, что письмо исчезло из черновиков
        mainMailPage.clickMenuDraftButton();
        //проверка на текст нет писем
        assertTrue(mainMailPage.isDraftEmpty());

        //9.Verify, что письмо появилось в папке отправленные
        mainMailPage.clickMenuSentButton();
        assertTrue(mainMailPage.findLetterBySubject1InSent());

        //10.Выйти из учётной записи
        mainMailPage.clickProfileButton();
        mainMailPage.clickLogOutButton();

    }

}
