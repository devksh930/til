package me.devksh930.datastructure.array;

import java.util.Arrays;
import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
//        Practice02_1();
//        Practice02_2();
//        Practice02_3();
//        Practice02_4();
        Practice02_5();
    }


    public static void Practice02_1() { // 5개의 양수를 입력받은 배열의 합
        int[] arr = new int[5];
        int sum = 0;

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("숫자만 입력하세요");
            }
            arr[i] = scanner.nextInt();
            sum += arr[i];
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(sum);

    }

    public static void Practice02_2() { // 배열의 최댓값 최소값
        int[] arr = {10, 11, 2, 5, 3, 3, 24, 15, 6, 9};
        int min = arr[0];
        int max = 0;

        for (int i : arr) {
            if (min > i) {
                min = i;
            } else if (max < i) {
                max = i;
            }
        }
        System.out.println("최대값 : " + max);
        System.out.println("최소값 : " + min);
    }

    public static void Practice02_3() { //순서가 없는 두 배열에서 서로 같은 요소를 포함하는지 판별하는 메서드
        int[] arr1 = {1, 3, 2};
        int[] arr2 = {2, 3, 1};

        boolean check = false;
        for (int i = 0; i < arr1.length; i++) {
            for (int j : arr2) {
                if (arr1[i] == j) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("다른배열");
                break;
            } else if (i + 1 == arr1.length) {
                System.out.println("같은배열");
            }
        }
    }

    public static void Practice02_4() { //중복된 요소를 제거한 새배열을 반환
        int[] arr = {5, 10, 9, 27, 2, 8, 10, 4, 27, 1};

        int[] result = new int[10];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j] && i != j) {
                    arr[i] = -1;
                    arr[j] = -1;
                    break;
                }

            }
            if (arr[i] != -1) {
                result[index++] = arr[i];
            }
        }
        System.out.println("중복된 요소 삭제 : " + Arrays.toString(result));

    }

    public static void Practice02_5() { //사용자로부터 소문자 알파벳을 입력받아 대문자로 변경하는 코드
        String[][] alphabets = {
                {"A", "a"}, {"B", "b"}, {"C", "c"},
                {"D", "d"}, {"E", "e"}, {"F", "f"},
                {"G", "g"}, {"H", "h"}, {"I", "i"},
                {"J", "j"}, {"K", "k"}, {"L", "l"},
                {"M", "m"}, {"N", "n"}, {"O", "o"},
                {"P", "p"}, {"Q", "q"}, {"R", "r"},
                {"S", "s"}, {"T", "t"}, {"U", "u"},
                {"V", "v"}, {"W", "w"}, {"X", "x"},
                {"Y", "y"}, {"Z", "z"}
        };
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        if (a.length() > 1) {
            System.out.println("한글자만 입력하세요");
            scanner.next();
        }
        for (int i = 0; i < alphabets.length; i++) {
            if (alphabets[i][1].equals(a)) {
                System.out.println(alphabets[i][0]);
            }else if(alphabets[i][0].equals(a)){
                System.out.println("대문자를 입력했습니다");
            }
        }
    }
}
