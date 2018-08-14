/**
 * Java level 1, lesson 1
 *
 * 1. Created the variables of all the passed data types, and initialized their values
 * 2. Wrote a method that calculates the expression a * (b + (c / d)) and returns the result
 * 3. Wrote a method that calculates whether a number is in the range of 10 to 20
 * 4. Wrote a method that defines a negative number or a positive
 * 5. Wrote a method that returns true if the number is negative
 * 6. Wrote a method that outputs a greeting
 * 7. Wrote a method that determines whether a year is a leap year
 *
 * @author Chaykin Ivan
 * @version 11.08.2018
 */

public class Lesson1 {

    public static void main(String[] args) {

        dataTypes();

        System.out.println(calculatingMethod(5,8,12,62));

        if (range(15, 9)) {
            System.out.println("Число поподает в диапазон от 10 до 20");
        } else {
            System.out.println("Число не поподает в диапазон от 10 до 20");
        }

        checkInteger(-1215151);

        if (checkIntegerV2(-1)) {
            System.out.println("Число отрицательное");
        } else {
            System.out.println("Число положительное");
        }

        hello("Иван");

        leapYear(2001);
        leapYear(2008);
    }

    //  Метод инициирует переменные и присваивает им значение
    private static void dataTypes () {

//      ​​8-бит от -128 до 127
        byte max_byte = 127;

//      16-бит -32768 до 32767
        short max_short = 32767;

//      ​​32-бита от -2147483648 до 2147483647
        int max_int = 2147483647;

//      ​​64-бита от -9223372036854775808 до 9223372036854775807
        long max_long = 922337203;

//      32-бита от 1.4E-45 до 3.4028235E38
        float max_float = 2.356f;

//      64-бита от 4.9E-324 до 1.7976931348623157E308
        double max_double = 1.7976931348623157E308;

//      ​​Принимает два значения: true и false
        boolean boolean_type = true;

//      16-бит от '\u0000' до '\uffff' или от 0 до 65,535
        char character = 'A';

        System.out.println("Создано 8 переменных (max_byte, max_short, max_int, max_long, max_float, max_double, boolean_type, character), им присвоены значения соответствующие их типам");

    }

    //  Метод выполняет вычисления
    private static int calculatingMethod(int a, int b, int c, int d) {

        return a * (b + (c / d));
    }

    //  Метод проверяет входит ли сумма двух чисел в диапазон от 10 до 20
    private static boolean range (int a, int b) {

//      Вычисляем сумму двух чисел
        int sum = a + b;

//      Условие проверяющее вхождение в диапазот от 10 до 20
        if (sum >= 10 && sum <= 20) {

            return true;

        }

        return false;
    }

    //  Метод проверяет отрицательное число или положительное и выводит результат в консоль
    private static void checkInteger (int a) {

        if (a >= 0) {

            System.out.println("Число "+ a +" положительное");

        } else {

            System.out.println("Число "+ a +" отрицательное");

        }

    }

    //  Метод проверяет отрицательное число или положительное
    private static boolean checkIntegerV2 (int a) {

        if (a >= 0) {

            return false;

        } else {

            return true;

        }

    }

    //  Метод выводит приветствие
    private static void hello (String name) {

        System.out.println("Привет, "+ name +"!");

    }

    //  Метод проверяет високосный год или нет
    private static void leapYear (int year) {

//      проверяем кратен ли год четырём, проверяем если год не сотый, но учитываем четырехсотый
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {

            System.out.println("Год "+ year +" високосный");

        } else {

            System.out.println("Год "+ year +" не високосный");

        }

    }
}