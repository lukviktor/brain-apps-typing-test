package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import pure_selenide.ConvertText;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Thread.sleep;

public class StartPage {

    // кнопка выбора раскладки клавиатуры
    private final SelenideElement btnKeyboardLayouts = $x("//div[@id='test-start']");

    // кнопка начать тест
    private final SelenideElement btnStartTest = $x("//button[@id='startButton']");

    // Поле с текстом для ввода
    private final SelenideElement kbTextContainer = $x("//div[@class='kbTextContainer']");


    // Пожалуйста, смените раскладку клавиатуры 'Oops'
    private final SelenideElement warningHolderOops = $x("//button[@class='swal2-confirm swal2-styled']");

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
    @Step("Закрываем окно предупреждения Oops")
    public void warningHolderOops() {
        warningHolderOops.click();
    }

    @Owner("Viktor Lukashev")
    @Step("Получаем текст который на экране для последующего ввода")
    public Character getCurrentWordChar() {
        return kbTextContainer.getText().charAt(0);
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
                warningHolderOops();
                String newKeys = ConvertText.convertToEnglish(currentWord);
                body.sendKeys(newKeys);
                System.out.println(newKeys);

                if (warningHolderOops.exists()) {
                    warningHolderOops();
                    Character currentNewWord = getCurrentWordChar();
                    String current = ConvertText.convertChar(currentNewWord);
                    body.sendKeys(current);
                    System.out.println(current);

                    if (warningHolderOops.exists()) {
                        warningHolderOops();
                    }
                }
            }
            currentWord = getCurrentWord(); // Обновляем значение currentWord
            //Для остановки
            System.out.println("Не удалось");
            break;
        }
    }
}