package ru.levelup.at.homework3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ThirdTaskTest extends CommonSeleniumTest {
    //Класс, выполняющий тест для задания 2

    @Test
    void sendNewMessageAndDelete() {
        //1. Войти в почту
        driver.get(MAIL_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .cssSelector("[class='resplash-btn resplash-btn_primary resplash-btn_mailbox-big svelte-1y37831']")))
            .click();
        WebElement signIn = driver.findElement(By.cssSelector("iframe[class='ag-popup__frame__layout__iframe']"));
        driver.switchTo().frame(signIn);

        var loginBox = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.cssSelector("input[name='username']")));
        loginBox.sendKeys(CommonSeleniumTest.MAIL_LOGIN);
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

        //2.1 Проверка кол-ва писем во входящих
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_INCOMING)))
            .click();
        wait.until(ExpectedConditions.titleContains(TITLE_INCOMING));
        final int incomeMessage = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.cssSelector(LETTER_LIST))).size();

        //2.2 Проверка кол-ва писем в Корзине
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_BIN)))
            .click();
        wait.until(ExpectedConditions.titleContains(TITLE_BIN));
        final int deleteMessage = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.cssSelector(LETTER_LIST))).size();

        //3. Создать новое письмо (заполнить адресата, тему письма и тело)
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.compose-button")))
            .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//div[@data-type='to']//input"))).sendKeys(MAIL_LOGIN);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//*[@name='Subject']"))).sendKeys(MESSAGE_SUBJECT3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//*[@role='textbox']"))).sendKeys(MESSAGE_BODY);

        //4. Отправить письмо
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector("button[data-test-id='send'"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector(".button2_close"))).click();

        //5. Verify, что письмо появилось в папке входящие
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_INCOMING)))
            .click();
        wait.until(ExpectedConditions.titleContains(TITLE_INCOMING));

        final int incomeMessageAll = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.cssSelector(LETTER_LIST))).size();
        assertThat(incomeMessageAll).isEqualTo(incomeMessage + 1);

        //6.Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        wait.until(ExpectedConditions.elementToBeClickable(By
            .cssSelector(LETTER_LIST + ":nth-of-type(1)"))).click();

        var letterAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector(".letter__recipients > .letter-contact"))).getAttribute("title");
        assertThat(letterAddress).isEqualTo(MAIL_LOGIN);

        var letterSubject = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                                    .cssSelector(".thread__subject-line > .thread-subject")))
                                .getText();
        assertThat(letterSubject).isEqualTo(MESSAGE_SUBJECT3);

        var letterText = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector(".js-helper > div >div>div>:first-child"))).getText();
        assertThat(letterText).isEqualTo(MESSAGE_BODY);

        //7. Удалить письмо
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector(".button2_delete"))).click();

        //8. Verify, что письмо появилось в папке Корзина
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_BIN)))
            .click();
        wait.until(ExpectedConditions.titleContains(TITLE_BIN));

        final int deleteMessageAll = wait.until(ExpectedConditions
            .presenceOfAllElementsLocatedBy(By.cssSelector(LETTER_LIST))).size();
        assertThat(deleteMessageAll).isEqualTo(deleteMessage + 1);

        //9.Выйти из учётной записи
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector(PROFILE_BUTTON))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .cssSelector("[data-testid='whiteline-account-exit']"))).click();
    }
}
