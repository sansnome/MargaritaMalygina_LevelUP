package ru.levelup.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FirstTaskTest extends CommonSeleniumTest {
    //Класс, выполняющий тест для задания 1

    @Test
    void createAndSaveAndSendMessage() {
        //1. Войти в почту
        driver.get(MAIL_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .cssSelector("[class='resplash-btn resplash-btn_primary resplash-btn_mailbox-big svelte-1y37831']")))
            .click();
        WebElement signIn = driver.findElement(By.cssSelector("iframe[class='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(signIn);

        var loginBox = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.cssSelector("input[name='username']")));
        loginBox.sendKeys(MAIL_LOGIN);
        loginBox.sendKeys(Keys.ENTER);

        var passwordBox = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.cssSelector("input[name='password']")));
        passwordBox.sendKeys(MAIL_PASSWORD);
        passwordBox.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ph-project-promo-close-icon")))
            .click();

        //2.Assert, что вход выполнен успешно
        String enterMailLogin = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.cssSelector(PROFILE_BUTTON))).getAttribute("alt");
        assertThat(enterMailLogin).isEqualTo(MAIL_LOGIN);

        //3.Создать новое письмо (заполнить адресата, тему письма и тело)
        //3.1 Проверка кол-ва писем в отправленных
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_SENT)))
            .click();
        wait.until(ExpectedConditions.titleContains(TITLE_SENT));
        final int sentMessage = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.cssSelector(LETTER_LIST))).size();

        //3.2 Проверка кол-ва писем в черновиках
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_DRAFT)))
            .click();
        wait.until(ExpectedConditions.titleContains(TITLE_DRAFT));
        final int draftMessage = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.cssSelector(LETTER_LIST))).size();

        //3.3 Непосредственно создание нового письма
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.compose-button")))
            .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[@data-type='to']//input"))).sendKeys(MAIL_LOGIN);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//*[@name='Subject']"))).sendKeys(MESSAGE_SUBJECT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//*[@role='textbox']"))).sendKeys(MESSAGE_BODY);

        //4.Сохранить его как черновик
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-test-id='save']")))
            .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Закрыть']")))
            .click();

        //5.Verify, что письмо сохранено в черновиках
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_DRAFT)))
            .click();
        wait.until(ExpectedConditions.titleContains(TITLE_DRAFT));
        final int draftMessageAll = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.cssSelector(LETTER_LIST))).size();
        assertThat(draftMessageAll).isEqualTo(draftMessage + 1);


        //6.Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        wait.until(ExpectedConditions.elementToBeClickable(By
            .cssSelector(LETTER_LIST + ":nth-of-type(1)"))).click();

        var letterAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[@data-type='to']//span[contains(@class, 'text')]"))).getText();
        assertThat(letterAddress).isEqualTo(MAIL_LOGIN);

        var letterSubject = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[contains(@class, 'compose-app_window')]//input[@name='Subject']")))
                                .getAttribute("value");
        assertThat(letterSubject).isEqualTo(MESSAGE_SUBJECT);

        var letterText = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector("[role='textbox']>div>div>div> :first-child >div"))).getText();
        assertThat(letterText).isEqualTo(MESSAGE_BODY);

        //7.Отправить письмо
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector("button[data-test-id='send'"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector(".button2_close"))).click();

        //8.Verify, что письмо исчезло из черновиков
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector(MENU_DRAFT))).click();
        wait.until(ExpectedConditions.titleContains(TITLE_DRAFT));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(LETTER_LIST), draftMessageAll - 1));

        //9.Verify, что письмо появилось в папке отправленные
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_SENT)))
            .click();
        wait.until(ExpectedConditions.titleContains(TITLE_SENT));

        final int sentMessageAll = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.cssSelector(LETTER_LIST))).size();
        assertThat(sentMessageAll).isEqualTo(sentMessage + 1);

        //10.Выйти из учётной записи
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector(PROFILE_BUTTON))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector("[data-testid='whiteline-account-exit']"))).click();
    }
}
