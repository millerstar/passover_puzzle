package com.jigsaw.impl;

import java.util.Objects;

public class Shape {
    private int sideLeft, sideRight, sideTop, sideBottom;

    public Shape(int sideLeft, int sideRight, int sideTop, int sideBottom) {
        this.sideLeft = sideLeft;
        this.sideRight = sideRight;
        this.sideTop = sideTop;
        this.sideBottom = sideBottom;
    }

    public int getSideLeft() {
        return sideLeft;
    }

    public void setSideLeft(int sideLeft) {
        this.sideLeft = sideLeft;
    }

    public int getSideRight() {
        return sideRight;
    }

    public void setSideRight(int sideRight) {
        this.sideRight = sideRight;
    }

    public int getSideTop() {
        return sideTop;
    }

    public void setSideTop(int sideTop) {
        this.sideTop = sideTop;
    }

    public int getSideBottom() {
        return sideBottom;
    }

    public void setSideBottom(int sideBottom) {
        this.sideBottom = sideBottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape)) return false;
        Shape shape = (Shape) o;
        return getSideLeft() == shape.getSideLeft() &&
                getSideRight() == shape.getSideRight() &&
                getSideTop() == shape.getSideTop() &&
                getSideBottom() == shape.getSideBottom();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSideLeft(), getSideRight(), getSideTop(), getSideBottom());
    }

    @Override
    public String toString() {
        return "Shape{" +
                "L=" + sideLeft +
                ", R=" + sideRight +
                ", T=" + sideTop +
                ", B=" + sideBottom +
                '}';
    }
}
