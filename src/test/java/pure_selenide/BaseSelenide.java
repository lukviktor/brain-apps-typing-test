package pure_selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data_file.Data;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseSelenide {
    @BeforeEach
    @Step("Инициализация selenide с настройками")
    public void init() {
        WebDriverManager.chromedriver().setup();
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Selenide.open(Data.URL_GAME);
    }

    @AfterEach
    @Step("Выполнение метода после каждого закрытия тестов")
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
