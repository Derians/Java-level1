import java.util.Random;
import java.util.Scanner;

/**
 * Java level 1, lesson 3
 *
 * 1. Wrote a program that guesses a random number from 0 to 9,
 * and the user is given 3 attempts to guess this number.
 * At each attempt, the computer reports more than the number
 * specified by the user than the one conceived, or less.
 *
 * 2. Wrote a program that selects a random word from
 * the array and gives the user an unlimited number of
 * attempts to guess the word. At each attempt,
 * the computer informs whether there are matching letters or not.
 *
 * @author Chaykin Ivan
 * @version 19.08.2018
 */

public class Lesson3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Меню:");
            System.out.println("Введите 1 для игры в \"Угадай число\"");
            System.out.println("Введите 2 для игры в \"Угадай слово\"");
            System.out.println("Введите 0 для выхода из программы");
            choice = scanner.nextInt();
            if (choice == 0){
                break;
            } else {
                switch (choice) {
                    case 1:
                        do {
                            gameGuessNumber();
                            System.out.println("Хотите сыграть ещё раз? " +
                                    "Тогда нажмите 1 или нажмите " +
                                    "0 что-бы выбрать другую игру");
                        } while (scanner.nextInt() != 0);
                        break;
                    case 2:
                        do {
                            gameGuessWord();
                            System.out.println("Хотите сыграть ещё раз? " +
                                    "Тогда нажмите 1 или нажмите " +
                                    "0 что-бы выбрать другую игру");
                        } while (scanner.nextInt() != 0);
                        break;
                }
            }
        }
    }
    /**
    * Метод запускает процесс игры "Угадай число"
    * Внутри метода создаётся случайное целое число от 0 до 9
    * и в цикле даётся 3 попытки угадать его.
    */
    private static void gameGuessNumber() {

        int number = new Random().nextInt(10);
        System.out.println("Угадайте число от 0 до 9");
        Scanner scanner = new Scanner(System.in);
        int answer;

        for (int i = 3; i >= 1;) {

            answer = scanner.nextInt();

            if (answer > 9) {
                System.out.println("Вы ввели чсло больше 9, " +
                        "введите число от 0 до 9");
            } else if (checkNumber(number, answer) == 0) {
                System.out.println("Вы угадали число!");
                break;
            }else if (checkNumber(number, answer) == 1) {
                System.out.println("Загаданное число меньше вашего, " +
                        "попробуйте сново. Количество попыток " + (i-1));
                i--;
            } else if (checkNumber(number, answer) == 2) {
                System.out.println("Загаданное число больше вашего, " +
                        "попробуйте сново.  Количество попыток " + (i-1));
                i--;
            }
        }
    }

    /**
     * Метод принемает два целых числа и проверяет
     * и проверят answer больше, меньше либо равен number
     *
     * @param number - загаданное число
     * @param answer - ответ пользователя
     * @return 1 - number < answer, 2 - number > answer, 0 - number = answer
     */
    private static int checkNumber(int number, int answer) {
        if (number < answer) {
            return 1;
        } else if (number > answer) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Метод запускает процесс игры "Угадай слово"
     * Внутри метода создаётся массив слов
     * в начале игры выбирается случайное слово из этого массива
     * ползователю даётся неограниченное количество попыток и
     * в виде подсказок вывоится строка содержащая совподающие символы
     */
    private static void gameGuessWord() {

        String[] words = {"apple", "orange", "lemon", "banana",
                "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};

        int randomNumber = new Random().nextInt(words.length);
        System.out.println("Угадайте слово!");
        Scanner scanner = new Scanner(System.in);
        String answer;
        String result;

        while (true){
            answer = scanner.nextLine();
            result = checkString(words[randomNumber], answer);

            if(words[randomNumber].equals(result)) {
                System.out.println("Вы угадали слово " + result);
                break;
            } else {
                System.out.println("Вы не угодали слово...");
                System.out.println("Возможно эта подсказка " +
                        "вам поможет - " + result);
            }
        }
    }

    /**
     * Метод принимает две строки (слов) и проверяет
     * совподают ли они, если слова не совподают
     * формируется новая строка (15 символов)
     * в которой все не соответсвующие сиволы заменяются на симво #
     *
     * @param word - случайное слово из массива
     * @param answer - слово введённое пользователем
     * @return answer если строки совпадают, result - составная строка
     */
    private static String checkString(String word, String answer) {

        if(word.equals(answer)) {
            return answer;
        } else {
            String result ="";
            for (int i = 0, z = 0; i <= 15; i++, z++) {

                if (z < word.length() && z < answer.length()) {
                    if (word.charAt(z) == answer.charAt(z)) {
                        result += word.charAt(z);
                    } else {
                        result += "#";
                    }
                } else {
                    result += "#";
                }
            }
            return result;
        }

    }

}
