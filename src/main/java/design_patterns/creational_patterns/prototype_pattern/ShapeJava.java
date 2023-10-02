package design_patterns.creational_patterns.prototype_pattern;

import java.util.Objects;

public abstract class ShapeJava {

    public int x;
    public int y;
    public String color;

    public ShapeJava() {
    }

    public ShapeJava(ShapeJava shapeJava) {
        if (shapeJava != null) {
            this.x = shapeJava.x;
            this.y = shapeJava.y;
            this.color = shapeJava.color;
        }
    }

    public abstract ShapeJava clone();

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ShapeJava shape2)) {
            return false;
        }
        return shape2.x == x && shape2.y == y && Objects.equals(shape2.color, color);
    }
}

