package ru.levelup.at.homework7.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ActionStep extends BaseStep {
    public ActionStep(WebDriver driver) {
        super(driver);
    }

    @Step("Авторизоваться в почтовом сервисе Mail.ru, учетная запись {login}")
    public void loginToMail(String login, String password) {
        loginAndRegistrationPage.open();
        loginAndRegistrationPage.clickLogInButton();
        loginAndRegistrationPage.sendLoginToLoginFrameField(login);
        loginAndRegistrationPage.sendPasswordToPasswordFrameField(password);
    }

    @Step("Нажать кнопку Создать письмо, заполнить адресата, тему и текст письма")
    public void createLetter(String address, String subject, String letterText) {
        /* if (mainMailPage.isPopUp()) {
           mainMailPage.closePopUp();
        }*/
        mainMailPage.clickCreateMailButton();
        mainMailPage.sendLoginToAddressField(address);
        mainMailPage.sendSubjectToSubjectMailField(subject);
        mainMailPage.sendMessageToBodyMailField(letterText);
    }

    @Step("Нажать кнопку Удалить письмо")
    public void deleteLetter() {
        mainMailPage.clickDeleteMailButton();
    }

    @Step("Нажать кнопку Сохранить письмо и закрыть окно создания письма")
    public void saveLetter() {
        mainMailPage.clickSaveMailButton();
        mainMailPage.clickCloseMailButton();
    }

    @Step("Нажать кнопку Отправить письмо и закрыть окно отправки письма")
    public void sendLetter() {
        mainMailPage.sendLetterButton();
        mainMailPage.closeSentLetter();
    }

    @Step("Выйти из учетной записи")
    public void logOut() {
        mainMailPage.clickProfileButton();
        mainMailPage.clickLogOutButton();
    }
}
