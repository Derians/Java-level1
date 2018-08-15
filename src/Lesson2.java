import java.util.Arrays;

/**
 * Java level 1, lesson 2
 *
 * 1. Set an integer array consisting of elements 0 and 1,
 * using a loop and the condition to replace 0 by 1, 1 by 0;
 *
 * 2. Set an empty integer array of size 8. Use a loop to fill it with
 * values 0 3 6 9 12 15 18 21;
 *
 * 3. Set the array [1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1] to go through it
 * with a cycle, and numbers less than 6 multiplied by 2;
 *
 * 4. Create a square two-dimensional integer array
 * (the number of rows and columns is the same), and use the cycle to fill its
 * diagonal elements with ones;
 *
 * 5. Set a one-dimensional array and find the minimum
 * and maximum elements in it;
 *
 * 6. The method takes a one-dimensional integer array and checks whether the
 * right and left parts of the array are equal
 *
 * 7. Write a method that receives a one-dimensional array
 * and a number n (it can be positive or negative),
 * the method must shift all the elements of the array to n positions.
 * To complicate the task, you can not use auxiliary arrays.
 *
 * @author Chaykin Ivan
 * @version 15.08.2018
 */

public class Lesson2 {

    public static void main(String[] args) {

        System.out.println("Задание 1.");

        replaceValue(new int[]{1, 0, 0, 1, 0, 0, 1});

        System.out.println(" ");
        System.out.println("Задание 2.");

        fillArray();

        System.out.println(" ");
        System.out.println("Задание 3.");

        multipliValues();

        System.out.println(" ");
        System.out.println("Задание 4.");

        multiArray();

        System.out.println(" ");
        System.out.println("Задание 5.");

        minMaxArray();

        System.out.println(" ");
        System.out.println("Задание 6.");

        // [6, || 1, 2, 3] = true
        System.out.println(checkBalance(new int[]{6, 1, 2, 3}));
        // [17, 32, 49, 20, 49] = false
        System.out.println(checkBalance(new int[]{17, 32, 49, 20, 49}));
        // [3, 4, 5, 12, || 1, 23] = true
        System.out.println(checkBalance(new int[]{3, 4, 5, 12, 1, 23}));
        // [10, 3, 11, 8, 13] = false
        System.out.println(checkBalance(new int[]{10, 3, 11, 8, 13}));
        // false
        System.out.println(checkBalance(new int[]{10}));
        // false
        System.out.println(checkBalance(new int[0]));


        System.out.println(" ");
        System.out.println("Задание 7.");

        assetArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2 );
        System.out.println(" ");
        assetArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, -2 );

    }

    //    Метод заменяющий значенеия в массиве 1 на 0 и 0 на 1
    private static void replaceValue(int arr[]) {

        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > 0) {

                arr[i] = 0;

            } else {

                arr[i] = 1;

            }

        }

        System.out.println(Arrays.toString(arr));

    }

    /**
     * Метод создаёт целочисленный массив размером 8 и заполняет его в цикле
     * значениями [0, 3, 6, 9, 12, 15, 18, 21]
     */
    private static void fillArray() {

        int arr[] = new int[8];

        for (int i = 0, z = 0; i < arr.length; i++, z += 3) {

            arr[i] = z;

        }

        System.out.println(Arrays.toString(arr));

    }

    //    Метод проходит по массиву в цикле и все значения меньше 6 умножает на 2
    private static void multipliValues() {

        int arr[] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] < 6) {

                arr[i] = arr[i] * 2;

            }

        }

        System.out.println(Arrays.toString(arr));


    }

    //    Метод заполняющий многомерный квадратный массив 1 по диагонали.
    private static void multiArray() {

        int arr[][] = new int[10][10];

        for (int i = 0, g = 0, j = 9; i < arr.length; i++, g++, j--) {

            arr[i][g] = 1;
            arr[i][j] = 1;

        }

        for (int[] anArr : arr) {
            for (int anAnArr : anArr) {
                System.out.print(anAnArr + "\t");
            }
            System.out.println();
        }

    }

    /**
     * Метод создаёт массив и заполняет его случайным числами, в цикле
     * вычисляется минимальное и максивальное значение его элементов
     */
    private static void minMaxArray() {

        int arr[] = new int[10];

        for (int i = 0; i < arr.length; i++) {

            arr[i] = (int) (Math.random() * 500);

        }

        int min = arr[0];
        int max = arr[0];

        System.out.println(Arrays.toString(arr));

        for (int value : arr) {

            if (value > max) {

                max = value;

            } else if (value < min) {

                min = value;

            }

        }

        System.out.println("Минимальное значение = " + min);
        System.out.println("Максимальное значение = " + max);

    }

    /**
     * Метод принимает одномерный целочисленный массив проверяет равны ли правая
     * и левая часть массива
     */
    private static boolean checkBalance(int arr[]) {

        if (arr != null && arr.length >= 2) {

            int leftNum = 0;
            int rightNum = 0;
            int i = 0, z = arr.length - 1;

            while (i <= z) {

                if (leftNum == 0 && rightNum == 0) {

                    leftNum = arr[i];
                    rightNum = arr[z];
                    z--;
                    i++;

                } else if (leftNum > rightNum) {

                    rightNum += arr[z];
                    z--;

                } else if (leftNum <= rightNum) {

                    leftNum += arr[i];
                    i++;

                }

            }

            return leftNum == rightNum;

        } else {

            return false;

        }

    }

    /**
     * Метод принимает одномерный целочисленный массив проверяет равны ли правая
     * и левая часть массива
     */
    private static void assetArray(int arr[], int asset) {

        System.out.println(Arrays.toString(arr));

        if (asset > 0) {

            for (int i = 0; i < asset; i++) {
                int tmp = arr[0];
                for (int j = 0; j < arr.length - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[arr.length - 1] = tmp;
            }

        } else {

            asset = (0 - asset);
            for (int i = arr.length; i > asset; i--) {
                int tmp = arr[0];
                for (int j = 0; j < arr.length - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[arr.length - 1] = tmp;
            }

        }

        System.out.println(Arrays.toString(arr));

    }

}
