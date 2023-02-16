package ru.levelup.at.homework7;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты первого упражнения д.з.3 по Selenium")
@Feature("Письмо в Черновиках")
public class FirstTaskWithStepsTest extends CommonSeleniumTest {
    //Класс, выполняющий тест для задания 1
    @Test
    @DisplayName("Создание, сохранение письма в черновиках и отправка письма из черновиков")
    @Story("Создание письма")
    @Story("Сохранение черновика письма")
    @Story("Отправка письма из черновиков")
    void createAndSaveAndSendMessage() {

        //1. Войти в почту
        actionStep.loginToMail(MAIL_LOGIN, MAIL_PASSWORD);

        //2.Assert, что вход выполнен успешно
        assertionStep.authorizationAsUserFromProperty(MAIL_LOGIN);

        //3. Создать новое письмо (заполнить адресата, тему письма и тело)
        actionStep.createLetter(MAIL_LOGIN, MESSAGE_SUBJECT1, MESSAGE_BODY);

        //4.Сохранить его как черновик
        actionStep.saveLetter();

        //5.Verify, что письмо сохранено в черновиках
        assertionStep.checkLetterIsSaved();

        //6.Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        assertionStep.checkDraftLetterAddressSubjectText(MAIL_LOGIN, MESSAGE_SUBJECT1, MESSAGE_BODY);

        //7.Отправить письмо
        actionStep.sendLetter();

        //8.Verify, что письмо исчезло из черновиков
        assertionStep.checkLetterNotInDraft();

        //9.Verify, что письмо появилось в папке отправленные
        assertionStep.checkLetterInSent();

        //10.Выйти из учётной записи
        actionStep.logOut();
    }
}
