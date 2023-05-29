package pure_selenide;

import com.ibm.icu.text.Transliterator;
import org.junit.jupiter.api.Test;

public class ConvertText {

    private static final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";


    @Test
    public void trTest() {
        String st = "Привет мир";
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        String result = toLatinTrans.transliterate(st);
        System.out.println(result);

        System.out.println(convertChar('Л'));
        System.out.println(convertChar('K'));
    }
    public static String convertChar(char ch) {
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return toLatinTrans.transliterate(String.valueOf(ch));
    }


    public static String convertToEnglish(String str) {
        String english = "";
        String[] russianLetters = {"й","ц","у","к","е","н","г","ш","щ","з","х","ъ","ф","ы","в","а","п",
                "р","о","л","д","ж","э","я","ч","с","м","и","т","ь","б","ю","ё", "Й","Ц","У","К","Е",
                "Н","Г","Ш","Щ","З","Х","Ъ","Ф","Ы","В","А","П","Р","О","Л","Д","Ж","Э","Я","Ч","С",
                "М","И","Т","Ь","Б","Ю","Ё"};
        String[] englishLetters = {"q","w","e","r","t","y","u","i","o","p","[","]","a","s","d","f",
                "g","h","j","k","l",";","'","z","x","c","v","b","n","m",",",".","`","Q","W","E","R",
                "T","Y","U","I","O","P","{","}","A","S","D","F","G","H","J","K","L",":","\"","Z",
                "X","C","V","B","N","M","<",">","~"};
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int index = -1;
            for (int j = 0; j < russianLetters.length; j++) {
                if (russianLetters[j].charAt(0) == ch) {
                    index = j;
                    break;
                }
            }
            if (index >= 0) {
                english += englishLetters[index];
            } else {
                english += ch;
            }
        }
        return english;
    }
}
