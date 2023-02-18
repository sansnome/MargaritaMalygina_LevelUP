package ru.levelup.at.homework7;

import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.levelup.at.homework7.PropertiesReader;
import ru.levelup.at.homework7.context.TestContext;
import ru.levelup.at.homework7.listener.AllureAttachmentCallBack;
import ru.levelup.at.homework7.steps.ActionStep;
import ru.levelup.at.homework7.steps.AssertionStep;


@ExtendWith(AllureAttachmentCallBack.class)
public class CommonSeleniumTest {
    public Properties properties;
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

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=1366,560");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-extenstions");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("force-device-scale-factor=0.75");
        chromeOptions.addArguments("high-dpi-support=0.75");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        actionStep = new ActionStep(driver);
        assertionStep = new AssertionStep(driver);
        TestContext.getInstance().putObject("driver", driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        TestContext.clear();
    }
}
