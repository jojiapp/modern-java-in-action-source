package modernjavainaction.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Sorting {

    public static void main(String... args) {

        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 90000000; i++) {
            apples.add(new Apple(i, Color.GREEN));
        }

        long startList = System.currentTimeMillis();
        List<Apple> listApples = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getWeight() > 90000000) {
                listApples.add(apple);
            }
        }
        System.out.println("list time: " + (System.currentTimeMillis() - startList));

        long startStream = System.currentTimeMillis();
        List<Apple> streamApples = apples.stream().filter(apple -> apple.getWeight() > 90000000).collect(Collectors.toList());
        System.out.println("stream time: " + (System.currentTimeMillis() - startStream));

        apples.stream()
                .filter(apple -> {
                    System.out.println("filter");
                    return apple.getWeight() > 1;
                })
                .map(apple -> {
                    System.out.println("map");
                    return apple.getColor();
                })
                .limit(3)
                .collect(Collectors.toList());


        // 1
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        ));

        // [Apple{color=GREEN, weight=80}, Apple{color=RED, weight=120}, Apple{color=GREEN, weight=155}]
        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(30, Color.GREEN));

        // 2
        // [Apple{color=GREEN, weight=30}, Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        inventory.sort(Comparator.comparingInt(Apple::getWeight));
        System.out.println(inventory);

        Function<Integer, Integer> f = (x -> x + 1);
        Function<Integer, Integer> g = (x -> x * 2);
        Function<Integer, Integer> h = f.compose(g);
        h.apply(1);


        // reshuffling things a little
        inventory.set(1, new Apple(20, Color.RED));

        // 3
        // [Apple{color=RED, weight=20}, Apple{color=GREEN, weight=30}, Apple{color=GREEN, weight=155}]
        inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(10, Color.RED));

        // 4
        // [Apple{color=RED, weight=10}, Apple{color=RED, weight=20}, Apple{color=GREEN, weight=155}]
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);
    }

    static class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight() - a2.getWeight();
        }

    }

}
