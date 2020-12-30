public class testAbstract {

    //추상메서드를 가지지 않는 추상클래스
    abstract class Car {
    }

    //추상메서드를 가지는 추상클래스
    abstract class Car_method {
        abstract public void run();
    }

    public class car extends Car_method {

        @Override
        public void run() {
            System.out.println("차가 달린다.");
        }
    }
}
