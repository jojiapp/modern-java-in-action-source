package com.jojiapp.modrenjavainactiontest;

public enum Color {
    RED,
    GREEN;

    public boolean isGreen() {
        return this == GREEN;
    }

    public boolean isRed() {
        return this == RED;
    }

    public boolean match(Color color) {
        return this == color;
    }
}


