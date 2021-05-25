package me.devksh930.datastructure.array;

public class Capter01 {
    public static void main(String[] args) {

        int min = minValue(5, 9, 2, 13, 1);
        System.out.println("최소값은 " + min);

        int max = maxValue(5, 9, 2, 13, 1);
        System.out.println("최대값은 "+ max);
    }
//최대값
    private static int maxValue(int... data) {
        int max = data[0];
        for (int datum : data) {
            if (max < datum) {
                max = datum;
            }
        }
        return max;
    }
//최소값
    private static int minValue(int... data) {
        int min = data[0];
        for (int datum : data) {
            if (min > datum) {
                min = datum;
            }
        }
        return min;
    }
}
