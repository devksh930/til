public class Literal {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 1;

        System.out.println("a 해쉬코드"+System.identityHashCode(a));
        System.out.println("b 해쉬코드"+System.identityHashCode(b));
        System.out.println("c 해쉬코드"+System.identityHashCode(c));

    }
}
