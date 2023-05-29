package pure_selenide;

import com.ibm.icu.text.Transliterator;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.stream.IntStream;

public class ConvertText {

    private static final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";

    public char randChar() {
        Random r = new Random();
        String xz = "абвгдежзиклмнопрстуфхцчюя".toUpperCase();
        return xz.charAt(r.nextInt(xz.length()));
    }

    public static String convertString(String str) {
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return toLatinTrans.transliterate(str);
    }

    public static String convertChar(char ch) {
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return toLatinTrans.transliterate(String.valueOf(ch));
    }


    public static String convertToEnglish(String str) {
        String english = "";
        AtomicReferenceArray<String> russianLetters = new AtomicReferenceArray<>(new String[]{"й", "ц", "у", "к", "е", "н", "г", "ш", "щ", "з", "х", "ъ", "ф", "ы", "в", "а", "п",
                "р", "о", "л", "д", "ж", "э", "я", "ч", "с", "м", "и", "т", "ь", "б", "ю", "ё", "Й", "Ц", "У", "К", "Е",
                "Н", "Г", "Ш", "Щ", "З", "Х", "Ъ", "Ф", "Ы", "В", "А", "П", "Р", "О", "Л", "Д", "Ж", "Э", "Я", "Ч", "С",
                "М", "И", "Т", "Ь", "Б", "Ю", "Ё"});
        AtomicReferenceArray<String> englishLetters = new AtomicReferenceArray<>(new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "a", "s", "d", "f",
                "g", "h", "j", "k", "l", ";", "'", "z", "x", "c", "v", "b", "n", "m", ",", ".", "`", "Q", "W", "E", "R",
                "T", "Y", "U", "I", "O", "P", "{", "}", "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"", "Z",
                "X", "C", "V", "B", "N", "M", "<", ">", "~"});
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int index = IntStream.range(0, russianLetters.length())
                    .filter(j -> russianLetters.get(j)
                            .charAt(0) == ch).findFirst()
                    .orElse(-1);
            english += index >= 0 ? englishLetters.get(index) : Character.valueOf(ch);
        }
        return english;
    }
}
