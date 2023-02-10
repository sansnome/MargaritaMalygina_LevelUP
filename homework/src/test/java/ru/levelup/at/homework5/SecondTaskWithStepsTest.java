package ru.levelup.at.homework5;

import org.junit.jupiter.api.Test;

public class SecondTaskWithStepsTest extends CommonSeleniumTest {
    //Класс, выполняющий тест для задания 2
    @Test
    void sendNewMessageToTestUser() {

        //1. Войти в почту
        actionStep.loginToMail(MAIL_LOGIN, MAIL_PASSWORD);

        //2.Assert, что вход выполнен успешно
        assertionStep.authorizationAsUserFromProperty(MAIL_LOGIN);

        //3. Создать новое письмо (заполнить адресата, тему письма и тело)
        actionStep.createLetter(MAIL_LOGIN, TEST_MESSAGE_SUBJECT, MESSAGE_BODY);

        //4. Отправить письмо
        actionStep.sendLetter();

        //5. Verify, что письмо появилось в папке отправленные
        assertionStep.checkTestLetterInSent();

        //6. Verify, что письмо появилось в папке "Тест"
        assertionStep.checkLetterInTest();

        //7.Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        assertionStep.checkLetterAddressSubjectText(MAIL_LOGIN, TEST_MESSAGE_SUBJECT, MESSAGE_BODY);

        //8.Выйти из учётной записи
        actionStep.logOut();
    }
}
