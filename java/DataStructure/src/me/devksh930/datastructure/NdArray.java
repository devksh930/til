package me.devksh930.datastructure;

import java.util.Arrays;

public class NdArray {// 다차원배열

    public static void main(String[] args) {
//        multiArray();
        multiplicationTable();
    }


    public static void multiArray() {
    		/*
				 사과    포도    오렌지
			    10000   20000   12000
			    8000    3000    15000
			    20000   15000   38000
			    13000   20000   30000
			    30000   12000   20000
			    35000   30000   25000
			    50000   23000   10000
		 */
        int[][] fruitMarket = new int[][]{
                {10000, 20000, 12000},
                {8000, 3000, 15000},
                {20000, 15000, 38000},
                {13000, 20000, 30000},
                {30000, 12000, 20000},
                {35000, 30000, 25000},
                {50000, 23000, 10000}
        };
        int total = 0;
        int apple = 0;
        int grape = 0;
        int orange = 0;

        for (int i = 0; i < fruitMarket.length; i++) {

            for (int j = 0; j < fruitMarket[i].length; j++) {
                if (j == 0) { //사과
                    apple += fruitMarket[i][j];
                } else if (j == 1) { //포도
                    grape += fruitMarket[i][j];
                } else { //오렌지
                    orange += fruitMarket[i][j];
                    System.out.println(orange);
                }
                total += fruitMarket[i][j];
            }
        }
        System.out.println("총합 : " + total);
        System.out.println("사과 평균  : " + apple / fruitMarket.length);
        System.out.println("포도 평균  : " + grape / fruitMarket.length);
        System.out.println("오렌지 평균  : " + orange / fruitMarket.length);
    }

    private static void multiplicationTable() {
        int[][] arr = new int[8][9];
        for (int i = 0, k = 2; i < arr.length; i++, k++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = k * (j + 1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 9; j++) {
                if (j != 0 && j % 3 == 0) {
                    System.out.println();
                }
                System.out.print((i + 2) + "x" + (j + 1) + "=" + arr[i][j]);
                System.out.print(" ");
            }
            System.out.println("\n");
        }
    }
}
