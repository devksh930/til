package me.devksh930.동적파라미터화;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.devksh930.동적파라미터화.Main.Color.RED;

public class Main {
    public static void main(String... args) throws IOException {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, RED));

        List<Apple> apples = filterGreenApples(inventory);
        String s = apples.toString();
        System.out.println(s);

        List<Apple> applesByColor = filterApplesByColor(inventory, RED);
        System.out.println(applesByColor.toString());

        List<Apple> applesByWeight = filterApplesByWeight(inventory, 150);
        System.out.println(applesByWeight.toString());

        List<Apple> colorfilter = filter(inventory, new Apple.AppleColorPredicate());
        System.out.println(colorfilter.toString());

        List<Apple> weightfilter = filter(inventory, new Apple.AppleWeightPredicate());
        System.out.println(weightfilter.toString());
//        퀴즈
        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());

        List<Apple> filter = filter(inventory, new Apple.ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return RED.equals(apple.getColor());
            }
        });
//        람다 표현식사용
        List<Apple> lamdafilter = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        System.out.println("lamada");
        System.out.println(lamdafilter);
        System.out.println("\n\n\n\n\n\n");
        System.out.println("리스트형식으로 추상화 Apple");
        List<Apple> filterlamda1 = filterlamda(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        System.out.println(filterlamda1);
        System.out.println("리스트형식으로 추상화 다른값");
        List<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        List<Integer> filterlamda = filterlamda(numbers, (Integer i) -> i % 2 == 0);
        System.out.println(filterlamda.toString());

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.GREEN) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, java.awt.Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filter(List<Apple> inventory, Apple.ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    //    dbdusgks prettyPrintApple 메서드 구현하기 퀴즈
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter p) {
        for (Apple apple : inventory) {
            String output = p.accept(apple);
            System.out.println(output);
        }
    }


    public interface AppleFormatter {
        String accept(Apple apple);
    }

    static class AppleFancyFormatter implements AppleFormatter {
        @Override
        public String accept(Apple apple) {
            String format = apple.getWeight() > 150 ? "heavy" : "light";
            return "A " + format + " " + apple.getColor() + " apple";

        }
    }


    static class AppleSimpleFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            return "An apple of " + apple.getWeight() + "g";
        }
    }


    enum Color {RED, GREEN}

    public static class Apple {

        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }

        interface ApplePredicate { //알고리즘 패밀리
            boolean test(Apple apple);
        }

        static class AppleWeightPredicate implements ApplePredicate { //전략

            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 150;
            }
        }

        static class AppleColorPredicate implements ApplePredicate { //전략

            @Override
            public boolean test(Apple apple) {
                return apple.getColor() == Color.GREEN;
            }
        }
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filterlamda(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}

