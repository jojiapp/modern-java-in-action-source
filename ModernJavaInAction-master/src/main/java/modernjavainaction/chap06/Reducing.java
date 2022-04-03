package modernjavainaction.chap06;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static modernjavainaction.chap06.Dish.menu;

public class Reducing {

  public static void main(String... args) {
    System.out.println("Total calories in menu: " + calculateTotalCalories());
    System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
    System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
    System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());

    System.out.println("===");
    IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
    System.out.println(menuStatistics);
    String collect = menu.stream().map(Dish::toString).collect(joining(", ", "{", "}"));
    System.out.println(collect);

    Integer totalCalories = menu.stream().collect(reducing(
                    0, Dish::getCalories, (i, j) -> i + j
            )
    );
    System.out.println(totalCalories);

    Optional<Dish> mostCalorieDish = menu.stream().collect(reducing(
                    (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2
            )
    );

    List<Integer> reduce = Stream.of(1, 2, 3, 4, 5)
            .reduce(
                    new ArrayList<>(),
                    (List<Integer> l, Integer e) -> {
                      l.add(e);
                      return l;
                    },
                    (List<Integer> l1, List<Integer> l2) -> {
                      l1.addAll(l2);
                      return l1;
                    }
            );

    System.out.println(reduce);

    Integer totalCalories2 = menu.stream()
            .collect(reducing(
                            0,
                            Dish::getCalories,
                            Integer::sum
                    )
            );

    Optional<Integer> totalCalories3 = menu.stream()
            .map(Dish::getCalories)
            .reduce(Integer::sum);

    int totalCalories4 = menu.stream()
            .mapToInt(Dish::getCalories)
            .sum();
  }

  private static int calculateTotalCalories() {
    return menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
  }

  private static int calculateTotalCaloriesWithMethodReference() {
    return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
  }

  private static int calculateTotalCaloriesWithoutCollectors() {
    return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
  }

  private static int calculateTotalCaloriesUsingSum() {
    return menu.stream().mapToInt(Dish::getCalories).sum();
  }

}
