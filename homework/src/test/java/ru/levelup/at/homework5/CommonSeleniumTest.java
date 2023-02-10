package ru.levelup.at.homework5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.levelup.at.homework5.steps.ActionStep;
import ru.levelup.at.homework5.steps.AssertionStep;

public class CommonSeleniumTest {

    protected WebDriver driver;

    protected ActionStep actionStep;
    protected AssertionStep assertionStep;

    private static final PropertiesReader getUserInfo = new PropertiesReader();
    protected static final String MAIL_LOGIN = getUserInfo.getUserInfoByKey("user_login");
    protected static final String MAIL_PASSWORD = getUserInfo.getUserInfoByKey("user_password");

    protected static final String MESSAGE_SUBJECT1 = "Задание 1";
    protected static final String MESSAGE_SUBJECT3 = "Задание 3";
    protected static final String TEST_MESSAGE_SUBJECT = "Тест";
    protected static final String MESSAGE_BODY = "Blood-bathing a day keeps doctors away!";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actionStep = new ActionStep(driver);
        assertionStep = new AssertionStep(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
