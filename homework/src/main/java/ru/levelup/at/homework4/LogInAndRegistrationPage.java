package ru.levelup.at.homework4;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogInAndRegistrationPage extends BasePage {
    //page object для страницы Войти/Создать почту
    protected static final String MAIL_URL = "https://mail.ru/";

    @FindBy (css = "button[data-testid='enter-mail-primary']")
    private WebElement logInButton;

    @FindBy(css = "iframe[class='ag-popup__frame__layout__iframe']")
    private WebElement logInFrame;

    @FindBy(css = "input[name='username']")
    private WebElement loginField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;

    public LogInAndRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(MAIL_URL);
    }

    public void clickLogInButton() {
        wait.until(ExpectedConditions.visibilityOf(logInButton)).click();
        driver.switchTo().frame(logInFrame);
    }

    private void enterText(String text, WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);

    }

    public void sendLoginToLoginFrameField(String login) {
        enterText(login, loginField);
        wait.until(ExpectedConditions.visibilityOf(loginField)).sendKeys(Keys.ENTER);
    }

    public void sendPasswordToPasswordFrameField(String password) {
        enterText(password, passwordField);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(Keys.ENTER);
    }


}
