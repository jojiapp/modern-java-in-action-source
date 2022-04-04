package modernjavainaction.chap06;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static modernjavainaction.chap06.Dish.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

  @Override
  public Supplier<List<T>> supplier() {
    return ArrayList::new;
  }

  @Override
  public BiConsumer<List<T>, T> accumulator() {
    return List::add;
  }

  @Override
  public Function<List<T>, List<T>> finisher() {
    return Function.identity();
  }

  @Override
  public BinaryOperator<List<T>> combiner() {
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
  }

}

class Foo {
  public static void main(String[] args) {
    List<Dish> dishes1 = menu.stream().collect(new ToListCollector<>());
    System.out.println(dishes1);

    List<Dish> dishes2 = menu.stream().collect(Collectors.toList());
    System.out.println(dishes2);

    ArrayList<Object> dishes3 = menu.stream().collect(
            ArrayList::new,
            List::add,
            List::addAll
    );
  }
}