/***
 * Завдання виконано для хакатону "Hack for Good" 2017
 * @author: Lytvyn Tetiana
 * @data: 07.10.2017
 *
 * Основна програма, яка, використовуючи методи класу Converter, демонструє основні випадки перетворення чисел розглянуті в test та
 * надає користувачу можливість введення своїх чисел для конвертування.
 * Також в даній частині виконано обробку основних випадків неправильного вводу даних користувачем.
 */

package Lytvyn.Tetiana.Converter;

import java.io.*;
import java.util.regex.*;

public class Program {

    public static void main(String[] args) throws IOException {

        String number = "([0-9]+)";
        String letter = "([IVXLCDM]+)";
        String errorline = "(.*)(I{4,}|V{4,}|X{4,}|L{4,}|C{4,}|D{4,}|M{4,})(.*)";

        //Виведення тестових прикладів
        Converter.test();

        //Введення значень з клавіатури
        System.out.println("Введіть римське чи абарське число, яке необхідно конвертувати: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String n = reader.readLine();

        while (n == null || n.length() == 0 || n.equals("0") || (!checkWithRegexp(n, number) && !checkWithRegexp(n, letter))) {
            System.out.println("Значення задано невірно. Спробуйте знову:");
            n = reader.readLine();
        }

        if (checkWithRegexp(n, letter)) {
            if (checkWithRegexp(n, errorline))
                throw new NumberFormatException("Значення задано невірно.");
            System.out.println(Converter.romanToArabic(n));
        }
        else {
            int arabic_n = Integer.parseInt(n);
            if (arabic_n < 1 || arabic_n >= 4000)
                throw new NumberFormatException("Значення повинно належати інтервалу (0;4000).");
            System.out.println(Converter.arabicToRoman(arabic_n));
        }
    }

    /***
     * Пееревірка на валідність введення із використанням регулярних виразів.
     * @param text
     * @param template
     * @return
     */
    private static boolean checkWithRegexp(final String text, final String template) {
        Pattern pattern = null;
        try {
            pattern = Pattern.compile(template);
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }
        if (pattern == null) {
            return false;
        }
        final Matcher regexp = pattern.matcher(text);
        return regexp.matches();
    }



}
