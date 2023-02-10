package ru.levelup.at.homework5;

import org.junit.jupiter.api.Test;

public class ThirdTaskWithStepsTest extends CommonSeleniumTest {
    //Класс, выполняющий тест для задания 3
    @Test
    void sendNewMessageAndDelete() {
        //1. Войти в почту
        actionStep.loginToMail(MAIL_LOGIN, MAIL_PASSWORD);

        //2.Assert, что вход выполнен успешно
        assertionStep.authorizationAsUserFromProperty(MAIL_LOGIN);

        //3. Создать новое письмо (заполнить адресата, тему письма и тело)
        actionStep.createLetter(MAIL_LOGIN, MESSAGE_SUBJECT3, MESSAGE_BODY);

        //4. Отправить письмо
        actionStep.sendLetter();

        //5. Verify, что письмо появилось в папке входящие
        assertionStep.checkLetterInIncoming();

        //6.Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        assertionStep.checkLetterAddressSubjectText(MAIL_LOGIN, MESSAGE_SUBJECT3, MESSAGE_BODY);

        //7. Удалить письмо
        actionStep.deleteLetter();

        //8. Verify, что письмо появилось в папке Корзина
        assertionStep.checkLetterInBin();

        //9.Выйти из учётной записи
        actionStep.logOut();
    }
}
