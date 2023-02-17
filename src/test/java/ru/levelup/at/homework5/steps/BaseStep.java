package ru.levelup.at.homework5.steps;

import org.openqa.selenium.WebDriver;
import ru.levelup.at.homework5.LoginAndRegistrationPage;
import ru.levelup.at.homework5.MainMailPage;

public class BaseStep {

    protected final WebDriver driver;

    protected LoginAndRegistrationPage loginAndRegistrationPage;
    protected MainMailPage mainMailPage;

    protected BaseStep(WebDriver driver) {
        this.driver = driver;
        loginAndRegistrationPage = new LoginAndRegistrationPage(driver);
        mainMailPage = new MainMailPage(driver);
    }
}
