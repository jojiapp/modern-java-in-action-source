package com.jojiapp.modrenjavainactiontest;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

public class Chap2 {
    int number = 10;

    private List<Apple> getApples() {
        return List.of(
                new Apple(10, Color.RED),
                new Apple(20, Color.RED),
                new Apple(30, Color.RED),
                new Apple(40, Color.RED),
                new Apple(50, Color.RED),
                new Apple(10, Color.GREEN),
                new Apple(20, Color.GREEN),
                new Apple(30, Color.GREEN),
                new Apple(40, Color.GREEN)
        );
    }

    @Test
    void 녹색_사과_필터링_기본() throws Exception {
        List<Apple> apples = filterGreenApples(getApples());

        assertThat(apples.size()).isEqualTo(4);
    }

    private List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getColor().isGreen()) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    void 색깔로_필터링() throws Exception {
        List<Apple> apples = filterColorApples(getApples(), Color.RED);

        assertThat(apples.size()).isEqualTo(5);
    }

    private List<Apple> filterColorApples(List<Apple> apples, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getColor().match(color)) {
                result.add(apple);
            }
        }
        return result;
    }


    private List<Apple> filter(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    void 필터링_익명클래스() throws Exception {
        List<Apple> apples1 = filter(getApples(), apple -> apple.getColor().isGreen());
        List<Apple> apples2 = filter(getApples(), apple -> apple.getWeight() >= 30);

        assertThat(apples1.size()).isEqualTo(4);
        assertThat(apples2.size()).isEqualTo(5);
    }

    @Test
    void 정렬() throws Exception {
        // given
        List<Integer> integers = new ArrayList<>(List.of(9, 2, 3, 5, 6, 1, 7, 2));
        // when
        integers.sort((o1, o2) -> o1.compareTo(o2));

        // then
        System.out.println(integers);

    }

    @Test
    void 로컬변수_테스트() throws Exception {
        Predicate<Integer> integerPredicate = predicateLocalVar();
        integerPredicate.test(10);
    }

    Predicate<Integer> predicateLocalVar() {
        int number = 10;
        return (n) -> (n + number) % 2 == 0;
    }


}
