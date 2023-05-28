package pure_selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseSelenide {
    private static final String URL_GAME = "https://brainapps.ru/typing-test/start";

    @BeforeEach
    @Owner("Viktor Lukashev")
    @Step("Инициализация selenide с настройками")
    public void init() {
        WebDriverManager.chromedriver().setup();
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Selenide.open(URL_GAME);
    }

    @AfterEach
    @Owner("Viktor Lukashev")
    @Step("Выполнение метода после каждого закрытия тестов")
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
