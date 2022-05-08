package com.jojiapp.modrenjavainactiontest;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class Chap3 {


    @Test
    void C() throws Exception {
        // given
        integrate(C::f, 10, 20);

        // when

        // then
    }

    private double integrate(Function<Double, Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;

    }

}

class C {
    public static double f(double a) {
        return a + 10;
    }
}
