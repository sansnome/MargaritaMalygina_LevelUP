package ru.levelup.at.homework3;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonSeleniumTest {
    //Класс для общих тестов с почтой
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final String MAIL_LOGIN = "batori.erzhebet@list.ru";
    protected static final String MAIL_PASSWORD = "sEri@LkiLLer1560";
    protected static final String MAIL_URL = "https://mail.ru/";
    protected static final String MESSAGE_SUBJECT = "Задание 1";
    protected static final String MESSAGE_SUBJECT3 = "Задание 3";
    protected static final String TEST_MESSAGE_SUBJECT = "Тест";
    protected static final String MESSAGE_BODY = "Blood-bathing a day keeps doctors away!";

    protected static final String MENU_BIN = "[href='/trash/?']";
    protected static final String MENU_DRAFT = "[href='/drafts/?']";
    protected static final String MENU_INCOMING = "[href='/inbox/?']";
    protected static final String MENU_SENT = "[href='/sent/?']";
    protected static final String MENU_TEST = "[href='/1/?']";

    protected static final String LETTER_LIST = "a.js-letter-list-item";

    protected static final String TITLE_BIN = "Корзина - Почта Mail.ru";
    protected static final String TITLE_DRAFT = "Черновики - Почта Mail.ru";
    protected static final String TITLE_INCOMING = "Входящие - Почта Mail.ru";
    protected static final String TITLE_SENT = "Отправленные - Почта Mail.ru";
    protected static final String TITLE_TEST = "Тест - Почта Mail.ru";
    protected static final String PROFILE_BUTTON = "img.ph-avatar-img";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofMillis(20000));

    }

    @AfterEach
    void tearDown() {

        driver.quit();
    }
}
