package com.jojiapp.modrenjavainactiontest;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Apple {

    private int weight;
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }
}
