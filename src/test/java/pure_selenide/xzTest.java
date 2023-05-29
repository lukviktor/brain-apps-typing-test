package pure_selenide;

import org.junit.jupiter.api.Test;



public class xzTest {
    @Test
    public void xzTest() {
        String input = "Привет как твои дела?";
        String result = ConvertText.convertToEnglish(input);
        System.out.println(result);
    }


}