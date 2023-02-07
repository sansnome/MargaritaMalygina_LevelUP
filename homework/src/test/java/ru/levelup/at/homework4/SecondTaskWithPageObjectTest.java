package ru.levelup.at.homework4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SecondTaskWithPageObjectTest extends CommonSeleniumTest {
    //Класс, выполняющий тест для задания 2
    @Test
    void sendNewMessageToTestUser() {
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

        //3. Создать новое письмо (заполнить адресата, тему письма и тело)
        if (mainMailPage.isPopUp()) {
            mainMailPage.closePopUp();
        }
        mainMailPage.clickCreateMailButton();
        mainMailPage.sendLoginToAddressField(MAIL_LOGIN);
        mainMailPage.sendSubjectToSubjectMailField(TEST_MESSAGE_SUBJECT);
        mainMailPage.sendMessageToBodyMailField(MESSAGE_BODY);

        //4. Отправить письмо
        mainMailPage.sendLetterButton();
        mainMailPage.closeSentLetter();

        //5. Verify, что письмо появилось в папке отправленные
        mainMailPage.clickMenuSentButton();
        assertTrue(mainMailPage.findLetterByTestSubjectInSent());

        //6. Verify, что письмо появилось в папке "Тест"
        mainMailPage.clickMenuTestButton();
        assertTrue(mainMailPage.findLetterByTestSubject());

        //7.Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        mainMailPage.clickLastLetterInList();
        String addressFromLetter = mainMailPage.getAddressFromLetter();
        assertThat(addressFromLetter).isEqualTo(MAIL_LOGIN);
        String subjectFromLetter = mainMailPage.getSubjectFromLetter();
        assertThat(subjectFromLetter).isEqualTo(TEST_MESSAGE_SUBJECT);
        String textFromLetter = mainMailPage.getLetterText();
        assertThat(textFromLetter).isEqualTo(MESSAGE_BODY);

        //8.Выйти из учётной записи
        mainMailPage.clickProfileButton();
        mainMailPage.clickLogOutButton();
    }

}
