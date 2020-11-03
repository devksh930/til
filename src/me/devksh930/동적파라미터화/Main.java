package me.devksh930.동적파라미터화;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        List<Apple> apples = filterGreenApples(inventory);
        String s = apples.toString();
        System.out.println(s);

        List<Apple> applesByColor = filterApplesByColor(inventory, Color.RED);
        System.out.println(applesByColor.toString());

        List<Apple> applesByWeight = filterApplesByWeight(inventory, 150);
        System.out.println(applesByWeight.toString());

        List<Apple> colorfilter = filter(inventory, new Apple.AppleColorPredicate());
        System.out.println(colorfilter.toString());

        List<Apple> weightfilter = filter(inventory, new Apple.AppleWeightPredicate());
        System.out.println(weightfilter.toString());
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
}

