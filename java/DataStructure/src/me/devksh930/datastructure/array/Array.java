package me.devksh930.datastructure.array;

import java.util.Arrays;
import java.util.Random;

public class Array {
    public static void main(String[] args) {
//        reversArray();
//        maxMin();
//        randomPrime();
//        arraysShallowCopy();
//        arraysDeepCopy();
        arraysCopyMethod();
    }


    public static void reversArray() {

        int[] students1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("변경전: " + Arrays.toString(students1));

        int[] students2 = new int[students1.length];
        for (int i = 0; i < students1.length; i++) {
            students2[(students1.length - 1) - i] = students1[i];
        }
        System.out.println("변경후: " + Arrays.toString(students2));
    }

    public static void maxMin() {
        Random random = new Random();
        final int ARRAY_LENGTH = 100;

        int[] arr = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            arr[i] = random.nextInt(100) + 1;
        }
        System.out.println("랜덤으로 생성된 배열 : " + Arrays.toString(arr));

        int min = arr[0];
        int max = 0;

        for (int k : arr) {
            if (min > k) {
                min = k;
            } else if (max < k) {
                max = k;
            }
        }
        System.out.println("최대값 : " + max + "\n 최소값 : " + min);
    }

    public static void randomPrime() {
        Random random = new Random();
        final int ARRAY_LENGTH = 10;

        int[] arr = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            boolean isPrimeNumber = true;
            int randomValue = random.nextInt(ARRAY_LENGTH);

            if (randomValue == 1) {
                continue;
            }
            if (randomValue == 2) {
                arr[i] = randomValue;
                continue;
            }

            for (int j = 2; j < randomValue; j++) {
                if (randomValue % j == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }

            if (isPrimeNumber) {
                arr[i] = randomValue;
            }
        }
        System.out.print("[배열에 저장된 소수]");
        for (int k : arr) {
            System.out.print(k+ " ");
        }
    }

    public static void arraysShallowCopy(){
        //배열의 얕은복사
        String[] folderA = {"myComputer", "myDocument"};
        String[] folderB = folderA;

        System.out.println("[사본 배열의 값 변경전] 원본 배열 : " + Arrays.toString(folderA));

        folderB[0] = "newFolder";
        System.out.println("[사본 배열의 값 변경 후 ] 원본배열 " + Arrays.toString(folderA));
        System.out.println("[사본 배열의 값 변경 후 ] 사본배열 " + Arrays.toString(folderB));
    }
    public static void  arraysDeepCopy(){
        String[] folderA = {"myComputer", "myDocument"};
        String[] folderB = new String[folderA.length];

        System.out.println("[사본 배열의 값 변경전] 원본 배열 : " + Arrays.toString(folderA));

        for (int i = 0; i < folderB.length; i++) {
            folderB[i] = folderA[i];
        }
        System.out.println("[사본 배열의 값] : "+Arrays.toString(folderB));

        folderB[0] = "newFolder";
        System.out.println("[사본 배열의 값 변경 후 ] 원본배열 " + Arrays.toString(folderA));
        System.out.println("[사본 배열의 값 변경 후 ] 사본배열 " + Arrays.toString(folderB));
    }
    public static void arraysCopyMethod(){
        String[] folderA = {"myComputer", "myDocument","recycleBin"};

        String[] folderB = folderA.clone();
        folderB[0] = "newFolder";
        System.out.println("clone() 사용");
        System.out.println("원본배열 : " + Arrays.toString(folderA));
        System.out.println("사본배열 : " + Arrays.toString(folderB));
        System.out.println();

        String[] folderC = new String[folderA.length];
        System.arraycopy(folderA, 0, folderC, 0, 2);

        folderC[0] = "newFolder";
        System.out.println("System.arraycopy() 사용");
        System.out.println("원본배열 : " + Arrays.toString(folderA));
        System.out.println("사본배열 : " + Arrays.toString(folderC));
        System.out.println();

        String[] folderD = Arrays.copyOf(folderA, 1);
        folderD[0] = "newFolder";
        System.out.println("Arrays.copyOf() 사용");
        System.out.println("원본배열 : " + Arrays.toString(folderA));
        System.out.println("사본배열 : " + Arrays.toString(folderD));
        System.out.println();

        String[] folderE = Arrays.copyOfRange(folderA, 0, 1);
        folderD[0] = "newFolder";
        System.out.println("Arrays.copyOfRange() 사용");
        System.out.println("원본배열 : " + Arrays.toString(folderA));
        System.out.println("사본배열 : " + Arrays.toString(folderE));
        System.out.println();



    }
}
