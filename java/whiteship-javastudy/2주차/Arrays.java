import java.util.ArrayList;
import java.util.HashMap;

public class Arrays {
    public static void main(String[] args) {
        String[] names;
        names = new String[100];
        int[] arrayA = new int[4];
        int[] arrayB = arrayA;
        System.out.println(arrayA.hashCode());
        System.out.println(arrayB.hashCode());

        int[][] multi = {
                {1, 2, 3},
                {5, 6, 7},
                {8, 9, 10}
        };
        System.out.println(multi.length);
        //가변배열
        int[][] dynamic = {
                {1, 2},
                {1, 2, 3, 4},
                {1}
        };
        System.out.println(dynamic[0].length);
        System.out.println(dynamic[1].length);
        System.out.println(dynamic[2].length);
        var a = "이타입은?";
        var b = new ArrayList<>();
        var c = new HashMap<>();
        var d = 1;
        var e = 1.2;
        Typetester t = new Typetester();
        System.out.println(a.getClass().getName());
        System.out.println(b.getClass().getName());
        System.out.println(c.getClass().getName());
        t.printType(d);
        t.printType(e);
    }


}
