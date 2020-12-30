public class SuperTest {
    public static class ParentClass {
        void myMethod() {
            System.out.println("ParentClass Method");
        }
    }

    public static class ChildClass extends ParentClass {

        @Override
        void myMethod() {
            System.out.println("method override");
        }
    }

    public static void main(String[] args) {
        ChildClass childClass = new ChildClass();
        childClass.myMethod();
    }
}
