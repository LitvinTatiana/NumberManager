/***
 * Завдання виконано для хакатону "Hack for Good" 2017
 * @author: Lytvyn Tetiana
 * @data: 07.10.2017
 *
 * Клас, який забезпечує конвертування звичайних чисел в записані римськими та записаних в римські - в звичайні.
 * */

package Lytvyn.Tetiana.Converter;

public class Converter {

    private static int letterToNumber(char letter) {

        switch (letter) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return -1;
        }
    }

    /***
     * Метод для конвертування чисел записаних римськими цифрами в звичайні.
     * Вхідний параметр рядок, значення, що повертається - ціле число.
     * @param roman
     * @return arabic
     */
    public static int romanToArabic(String roman) {

        int i = 0;
        int arabic = 0;

        while (i < roman.length()) {

            char letter = roman.charAt(i);
            int number = letterToNumber(letter);

            i++;

            if (i == roman.length()) arabic += number;
            else {
                int nextNumber = letterToNumber(roman.charAt(i));
                if (nextNumber > number && nextNumber <= number * 10) {
                    arabic += (nextNumber - number);
                    i++;
                } else arabic += number;

            }

        }

        return arabic;
    }

    /***
     * Метод для конвертування звичайних чисел в записані римськими цифрами.
     * Вхідний параметр число, значення, що повертається - рядок, який отримаємо із використанням допоміжного методу romanLogic.
     * @param arabic
     * @return romanLogic
     */
    public static String arabicToRoman(int arabic){

        int digits = arabic%10;
        int tens = (arabic%100)/10;
        int hundreds = (arabic%1000)/100;
        int thousands = (arabic%10000)/1000;

        return (romanLogic(thousands, "M","","")+
                romanLogic(hundreds,"C","D","M")+
                romanLogic(tens,"X","L","C")+
                romanLogic(digits,"I","V","X"));
    }

    private static String romanLogic(int i, String ones, String fives, String tens){

        String roman = "";

        if (i == 0) return roman;
        else {
            if ((i >= 4) && (i <= 8))roman += fives;
            if (i == 9) roman += tens;
            if(i%5 < 4){
                while(i%5 > 0){
                    roman += ones;
                    i--;
                }
            }
            if(i%5 == 4) roman = ones + roman;
        }
        return roman;
    }

    /*** Блок стандартних тестів для демонстрації роботи.
     * При зміні значень використовувати цілі числа, які належать проміжку (0;4000) та враховувати правила запису римських чисел.
     */
    private static String[] roman_number = {"VI", "IV", "XIX", "XLIX", "XXXIII", "DLIV", "MLXXIX"};
    private static int[] arabic_number = {6, 4, 19, 49, 33, 554, 1079};

    public static void test(){
        System.out.println("Тестові приклади.\nПеретворення арабських чисел в римські: ");

        for (int i = 1; i < arabic_number.length; i++) {
            System.out.println("" + arabic_number[i] + "  ==  " + arabicToRoman(arabic_number[i]) + " ;");
        }

        System.out.println("Перетворення римських чисел в арабські: ");
        for (int i = 1; i < roman_number.length; i++) {
            System.out.println("" + roman_number[i] + "  ==  " + romanToArabic(roman_number[i]) + " ;");}
        System.out.println("Забезпечена можливість вводу власного чила для конвертування.\n");

    }


}
