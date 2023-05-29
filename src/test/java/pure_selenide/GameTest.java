package pure_selenide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.StartPage;

public class GameTest extends BaseSelenide {
    @Test
    @DisplayName(value = "Играем в набор текста")
    public void gameTest() throws InterruptedException {
        StartPage startPage = new StartPage();
        startPage.goTestPage();
        startPage.printConvKeyboard();
    }


}

