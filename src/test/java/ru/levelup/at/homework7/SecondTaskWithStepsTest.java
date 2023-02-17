package ru.levelup.at.homework7;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты второго упражнения д.з.3 по Selenium")
@Feature("Пользовательская папка Тест")
public class SecondTaskWithStepsTest extends CommonSeleniumTest {
    //Класс, выполняющий тест для задания 2
    @Test
    @DisplayName("Создание и отправка письма")
    @Story("Создание письма")
    @Story("Отправка созданного письма")
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

    @Test
    @DisplayName("Создание и отправка письма + падение теста")
    @Story("Создание письма")
    @Story("Отправка созданного письма")
    void sendNewMessageToTestUserFailed() {

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
        //шаг для падения теста
        assertionStep.checkLetterAddressSubjectText(MAIL_LOGIN, MESSAGE_SUBJECT1, MESSAGE_BODY);

        //8.Выйти из учётной записи
        actionStep.logOut();
    }
}
