package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class StartPage {

    // кнопка выбора раскладки клавиатуры
    private final SelenideElement btnKeyboardLayouts = $x("//div[@id='test-start']");

    // кнопка начать тест
    private final SelenideElement btnStartTest = $x("//button[@id='startButton']");

    // Поле с текстом для ввода
    private final SelenideElement kbTextContainer = $x("//div[@class='kbTextContainer']");


    // Пожалуйста, смените раскладку клавиатуры
    private final SelenideElement warningHolderOops = $x("//button[@class='swal2-confirm swal2-styled']");

    // Кнопка завершить тест
    private final SelenideElement btnCompleteTest = $x("//button[contains(text(), 'Завершить тест')]");

    // Ваш результат
    private final SelenideElement result = $x("//div[contains(text(),'Ваш результат')]");

    // Скорость набора слов
    private final SelenideElement speedTypingWords = $x("//span[contains(text(),'Скорость набора слов')]");

    // Слов в минуту
    private final SelenideElement wordsPerMinute = $x("//div[contains(text(),'Слов в минуту')]");


    @Owner("Viktor Lukashev")
    @Step("Переходим на страницу теста")
    public void goTestPage() {
        btnKeyboardLayouts.click();
        btnStartTest.should(Condition.appear);
        btnStartTest.click();
        kbTextContainer.should(Condition.appear, Duration.ofDays(5000));
    }

    @Owner("Viktor Lukashev")
    @Step("Получаем текст который на экране для последующего ввода")
    public String getCurrentWord() {
        return kbTextContainer.getText();
    }

    @Owner("Viktor Lukashev")
    @Step("Печатаем данные в поле ввода")
    public void printKeyboard() throws InterruptedException {
        String currentWord = getCurrentWord();
        while (currentWord != null && !currentWord.isEmpty()) {
            $x("//body").sendKeys(currentWord);
            Thread.sleep(500); // пауза между вводом каждого слова

            // Проверяем, что появилось окно "Смените раскладку клавиатуры"
            if (warningHolderOops.exists()) {
                // Если окно есть, то кликаем на кнопку "ОК" для изменения раскладки
                warningHolderOops.click();
            }

            currentWord = getCurrentWord(); // Обновляем значение currentWord
        }

        // Когда все слова были введены, завершаем тест и проверяем результаты
        btnCompleteTest.click();
        System.out.println(result.getText());
        result.shouldBe(Condition.visible);
        System.out.println(speedTypingWords.getText());
        speedTypingWords.shouldBe(Condition.visible);
        wordsPerMinute.shouldBe(Condition.visible);
        List<SelenideElement> rows = Selenide.$$x("//tr[contains(@class,'genV')]");
        for (SelenideElement row : rows) {
            String text = row.$$("td").get(0).text();
            if (!text.matches("\\d+")) {
                Assertions.fail("Текст в первой колонке не является числом: " + text);
            }
        }
    }
}