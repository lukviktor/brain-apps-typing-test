package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import pure_selenide.ConvertText;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Thread.sleep;

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
    //private final SelenideElement btnCompleteTest = $x("//button[contains(text(), 'Завершить тест')]");

    // Ваш результат
    //private final SelenideElement result = $x("//div[contains(text(),'Ваш результат')]");

    // Скорость набора слов
    //private final SelenideElement speedTypingWords = $x("//span[contains(text(),'Скорость набора слов')]");

    // Слов в минуту
    //private final SelenideElement wordsPerMinute = $x("//div[contains(text(),'Слов в минуту')]");


    private final SelenideElement body = $x("//body");


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
            body.sendKeys(currentWord);
            sleep(500); // пауза между вводом каждого слова

            // Проверяем, что появилось окно "Смените раскладку клавиатуры"
            if (warningHolderOops.exists()) {
                // Если окно есть, то кликаем на кнопку "ОК" для изменения раскладки
                warningHolderOops.click();
            }

            currentWord = getCurrentWord(); // Обновляем значение currentWord
        }
    }

    @Owner("Viktor Lukashev")
    @Step("Печатаем данные в поле ввода")
    public void printKeyboardR() {
        String currentWord = getCurrentWord();
        char current = currentWord.charAt(0);
        while (currentWord != null && !currentWord.isEmpty()) {
            currentWord = String.valueOf(current);
            body.sendKeys(currentWord);
            // Проверяем, что появилось окно "Смените раскладку клавиатуры"
            if (warningHolderOops.exists()) {
                // Если окно есть, то кликаем на кнопку "ОК" для изменения раскладки
                warningHolderOops.click();
                Random r = new Random();
                String eng = "abcdefghijklmnopqrstuvwxyz";

                String sum = eng + eng.toUpperCase();
                char c = sum.charAt(r.nextInt(sum.length()));
                body.sendKeys(String.valueOf(c));
            }
            currentWord = getCurrentWord(); // Обновляем значение currentWord
        }
    }

    @Owner("Viktor Lukashev")
    @Step("Получаем текст который на экране для последующего ввода")
    public Character getCurrentWordChar() {
        return kbTextContainer.getText().charAt(0);
    }

    @Step
    public char randChar() {
        Random r = new Random();
        String xz = "абвгдежзиклмнопрстуфхцчюя".toUpperCase();
        return xz.charAt(r.nextInt(xz.length()));
    }

    @Owner("Viktor Lukashev")
    @Step("Печатаем данные в поле ввода")
    public void printKeyboardСhar() {
        Character currentWord = getCurrentWordChar();
        while (currentWord != null && !currentWord.toString().isEmpty()) {
            char simnol = randChar();
            if (currentWord.equals('С')) {
                body.sendKeys(String.format("%c", 'C'));
                System.out.println(simnol);
            }
            if (currentWord.equals(currentWord)) {
                body.sendKeys(String.valueOf(currentWord));
            }
            if (warningHolderOops.exists()) {
                warningHolderOops.click();
                body.sendKeys(String.format("%c", currentWord));
                warningHolderOops.click();
            }
            currentWord = getCurrentWordChar();
        }
    }

    @Owner("Viktor Lukashev")
    @Step("Печатаем данные в поле ввода")
    public void printKeyboardСharConvert() {
        Character currentWord = getCurrentWordChar();
        String current = ConvertText.convertChar(currentWord);
        while (currentWord != null && !currentWord.toString().isEmpty()) {

            body.sendKeys(current);
            // Проверяем, что появилось окно "Смените раскладку клавиатуры"
            if (warningHolderOops.exists()) {
                // Если окно есть, то кликаем на кнопку "ОК" для изменения раскладки
                warningHolderOops.click();
            }
            currentWord = getCurrentWordChar(); // Обновляем значение currentWord
        }
    }


    @Owner("Viktor Lukashev")
    @Step("Печатаем данные в поле ввода")
    public void printConvKeyboard() throws InterruptedException {
        String currentWord = getCurrentWord();
        while (currentWord != null && !currentWord.isEmpty()) {
            body.sendKeys(currentWord);

            System.out.println(currentWord);
            sleep(50); // пауза между вводом каждого слова

            // Проверяем, что появилось окно "Смените раскладку клавиатуры"
            if (warningHolderOops.exists()) {
                // Если окно есть, то кликаем на кнопку "ОК" для изменения раскладки
                warningHolderOops.click();
                body.sendKeys(ConvertText.convertToEnglish(currentWord));

                if (warningHolderOops.exists()) {
                    warningHolderOops.click();
                    Character currentNewWord = getCurrentWordChar();
                    String current = ConvertText.convertChar(currentNewWord);
                    body.sendKeys(current);

                    if (warningHolderOops.exists()) {
                        warningHolderOops.click();
                    }
                }
            }
            currentWord = getCurrentWord(); // Обновляем значение currentWord
        }
    }
}