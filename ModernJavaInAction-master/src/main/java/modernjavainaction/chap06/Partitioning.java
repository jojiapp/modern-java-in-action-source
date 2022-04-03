package modernjavainaction.chap06;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static modernjavainaction.chap06.Dish.menu;

public class Partitioning {

    public static void main(String... args) {
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());

        System.out.println("==");
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
                .collect(
                        partitioningBy(
                                Dish::isVegetarian
                        )
                );

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                .collect(
                        partitioningBy(
                                Dish::isVegetarian,
                                groupingBy(Dish::getType)
                        )
                );
        System.out.println(vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(
                        partitioningBy(
                                Dish::isVegetarian,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get
                                )
                        )
                );
        System.out.println(mostCaloricPartitionedByVegetarian);

        int n = 10000;
        Map<Boolean, List<Integer>> partitionPrimes = IntStream.rangeClosed(2, n)
                .boxed()
                .collect(partitioningBy(Partitioning::isPrime));
        System.out.println(partitionPrimes);
    }

    public static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(candidate))
                .noneMatch(n -> candidate % n == 0);
    }

    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Object mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }

}
