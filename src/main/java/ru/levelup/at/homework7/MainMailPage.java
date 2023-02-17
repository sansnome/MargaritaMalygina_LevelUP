package ru.levelup.at.homework7;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelup.at.homework7.BasePage;

public class MainMailPage extends BasePage {
    //класс с вебэлементами основной страницы почты
    private static final String LETTER_LIST = "a.js-letter-list-item";

    private static final String TITLE_BIN = "Корзина - Почта Mail.ru";
    private static final String TITLE_DRAFT = "Черновики - Почта Mail.ru";
    private static final String TITLE_INCOMING = "Входящие - Почта Mail.ru";
    private static final String TITLE_SENT = "Отправленные - Почта Mail.ru";
    private static final String TITLE_TEST = "Тест - Почта Mail.ru";
    public static final String MESSAGE_SUBJECT1 = "Задание 1";
    public static final String TEST_MESSAGE_SUBJECT = "Тест";
    public static final String MESSAGE_SUBJECT3 = "Задание 3";

    public MainMailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "img.ph-avatar-img")
    private WebElement profileButton;

    @FindBy(css = "[href='/trash/?']")
    private WebElement menuBinButton;

    @FindBy(css = "[href='/drafts/?']")
    private WebElement menuDraftButton;

    @FindBy(css = "[href='/inbox/?']")
    private WebElement menuIncomingButton;

    @FindBy(css = "[href='/sent/?']")
    private WebElement menuSentButton;

    @FindBy(css = "[href='/1/?']")
    private WebElement menuTestButton;

    @FindBy(xpath = "//*[text()= 'Написать письмо']")
    private WebElement createMailButton;

    @FindBy(css = ".button2_delete")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[@data-type='to']//input")
    private WebElement addressMailField;

    @FindBy(xpath = "//*[@name='Subject']")
    private WebElement subjectMailField;

    @FindBy(xpath = "//*[@role='textbox']")
    private WebElement messageBodyField;

    @FindBy(css = "button[data-test-id='save']")
    private WebElement saveMailButton;

    @FindBy(css = "button[data-test-id='send'")
    private WebElement sendMailButton;

    @FindBy(css = "button[title='Закрыть']")
    private WebElement closeMailButton;

    @FindBy(css = ".button2_close")
    private WebElement closeButtonInSentMail;

    @FindBy(xpath = "//div[@data-type='to']//span[contains(@class, 'text')]")
    private WebElement draftLetterAddress;

    @FindBy(css = ".letter__recipients > .letter-contact")
    private WebElement letterAddress;

    @FindBy(xpath = "//div[contains(@class, 'compose-app_window')]//input[@name='Subject']")
    private WebElement draftLetterSubject;

    @FindBy(css = ".thread__subject-line > .thread-subject")
    private WebElement letterSubject;

    @FindBy(xpath = "//*[text()= '" + MESSAGE_SUBJECT1 + "']")
    private WebElement letterWithSubject1;

    @FindBy(xpath = "//*[text()= 'Self: " + MESSAGE_SUBJECT1 + "']")
    private WebElement letterWithSubject1tInSent;

    @FindBy(xpath = "//*[text()= '" + TEST_MESSAGE_SUBJECT + "']")
    private WebElement letterWithTestSubject;

    @FindBy(xpath = "//*[text()= 'Self: " + TEST_MESSAGE_SUBJECT + "']")
    private WebElement letterWithTestSubjectInSent;

    @FindBy(xpath = "//*[text()= '" + MESSAGE_SUBJECT3 + "']")
    private WebElement letterWithSubject3;

    @FindBy(css = "span.octopus__title")
    private WebElement noLettersInDraft;

    @FindBy(css = "div.ph-project-promo-close-icon.svelte-m7oyyo > div")
    private WebElement popUp;

    @FindBy(css = "[role='textbox']>div>div>div> :first-child >div")
    private WebElement textFromDraftLetter;

    @FindBy(css = ".js-helper > div >div>div>:first-child")
    private WebElement textFromLetter;


    @FindBy(css = LETTER_LIST)
    private List<WebElement> listOfMails;

    @FindBy(css = LETTER_LIST + ":nth-of-type(1)")
    private WebElement lastMailInList;

    @FindBy(css = "[data-testid='whiteline-account-exit']")
    private WebElement logOutButton;

    public String getActualUserLogin() {

        return profileButton.getAttribute("alt");
    }

    public void clickCreateMailButton() {
        wait.until(ExpectedConditions.visibilityOf(createMailButton)).click();
    }

    public void closePopUp() {
        wait.until(ExpectedConditions.visibilityOf(popUp)).click();
    }

    public boolean isPopUp() {
        return wait.until(ExpectedConditions.visibilityOf(popUp)).isDisplayed();
    }

    public boolean isDraftEmpty() {
        return wait.until(ExpectedConditions.visibilityOf(noLettersInDraft)).isDisplayed();
    }

    public void clickCloseMailButton() {
        wait.until(ExpectedConditions.visibilityOf(closeMailButton)).click();
    }

    public void clickLogOutButton() {
        wait.until(ExpectedConditions.visibilityOf(logOutButton)).click();
    }

    public void clickMenuBinButton() {
        wait.until(ExpectedConditions.visibilityOf(menuBinButton)).click();
        wait.until(ExpectedConditions.titleContains(TITLE_BIN));
    }

    public void clickMenuDraftButton() {
        wait.until(ExpectedConditions.visibilityOf(menuDraftButton)).click();
        wait.until(ExpectedConditions.titleContains(TITLE_DRAFT));
    }

    public void clickMenuIncomingButton() {
        wait.until(ExpectedConditions.visibilityOf(menuIncomingButton)).click();
        wait.until(ExpectedConditions.titleContains(TITLE_INCOMING));
    }

    public void clickMenuTestButton() {
        wait.until(ExpectedConditions.visibilityOf(menuTestButton)).click();
        wait.until(ExpectedConditions.titleContains(TITLE_TEST));
    }

    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    public void clickMenuSentButton() {
        wait.until(ExpectedConditions.visibilityOf(menuSentButton)).click();
        wait.until(ExpectedConditions.titleContains(TITLE_SENT));
    }

    public void clickSaveMailButton() {
        wait.until(ExpectedConditions.visibilityOf(saveMailButton)).click();
    }

    public void clickDeleteMailButton() {
        wait.until(ExpectedConditions.visibilityOf(deleteButton)).click();
    }

    public void sendLoginToAddressField(String address) {
        wait.until(ExpectedConditions.visibilityOf(addressMailField)).sendKeys(address);
    }

    public void sendSubjectToSubjectMailField(String subject) {
        wait.until(ExpectedConditions.visibilityOf(subjectMailField)).sendKeys(subject);
    }

    public void sendMessageToBodyMailField(String bodyMail) {
        wait.until(ExpectedConditions.visibilityOf(messageBodyField)).sendKeys(bodyMail);
    }


    public void getLastLetter() {
        wait.until(ExpectedConditions.elementToBeClickable(lastMailInList)).click();
    }

    public String getAddressFromDraftLetter() {
        return wait.until(ExpectedConditions.visibilityOf(draftLetterAddress)).getText();
    }

    public String getAddressFromLetter() {
        return wait.until(ExpectedConditions.visibilityOf(letterAddress)).getAttribute("title");
    }

    public String getSubjectFromDraftLetter() {
        return wait.until(ExpectedConditions.visibilityOf(draftLetterSubject)).getAttribute("value");
    }

    public String getSubjectFromLetter() {
        return wait.until(ExpectedConditions.visibilityOf(letterSubject)).getText();
    }

    public String getTextFromDraftLetter() {
        return wait.until(ExpectedConditions.visibilityOf(textFromDraftLetter)).getText();
    }

    public String getLetterText() {
        return wait.until(ExpectedConditions.visibilityOf(textFromLetter)).getText();
    }

    public void sendLetterButton() {
        wait.until(ExpectedConditions.visibilityOf(sendMailButton)).click();
    }

    public void closeSentLetter() {
        wait.until(ExpectedConditions.visibilityOf(closeButtonInSentMail)).click();
    }

    public boolean findLetterBySubject1() {
        try {
            wait.until(ExpectedConditions.visibilityOf(letterWithSubject1));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean findLetterBySubject1InSent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(letterWithSubject1tInSent));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean findLetterBySubject3() {
        try {
            wait.until(ExpectedConditions.visibilityOf(letterWithSubject3));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean findLetterByTestSubjectInSent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(letterWithTestSubjectInSent));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean findLetterByTestSubject() {
        try {
            wait.until(ExpectedConditions.visibilityOf(letterWithTestSubject));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void clickLastLetterInList() {
        wait.until(ExpectedConditions.elementToBeClickable(lastMailInList)).click();
    }

}
