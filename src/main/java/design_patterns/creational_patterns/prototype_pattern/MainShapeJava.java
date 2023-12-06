package design_patterns.creational_patterns.prototype_pattern;

import java.util.ArrayList;
import java.util.List;

public class MainShapeJava {
    public static void main(String[] args) {
        List<ShapeJava> shapes = new ArrayList<>();
        List<ShapeJava> shapesCopy = new ArrayList<>();

        CircleJava circle = new CircleJava();
        circle.x = 10;
        circle.y = 20;
        circle.radius = 15;
        circle.color = "red";
        shapes.add(circle);

        CircleJava anotherCircle = (CircleJava) circle.clone();
        shapes.add(anotherCircle);

        RectangleJava rectangle = new RectangleJava();
        rectangle.width = 10;
        rectangle.height = 20;
        rectangle.color = "blue";
        shapes.add(rectangle);

        cloneAndCompare(shapes, shapesCopy);
    }

    private static void cloneAndCompare(List<ShapeJava> shapes, List<ShapeJava> shapesCopy) {
        for (ShapeJava shape : shapes) {
            shapesCopy.add(shape.clone());
        }

        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) != shapesCopy.get(i)) {
                System.out.println(i + ": Shapes are different objects (yay!)");
                if (shapes.get(i).equals(shapesCopy.get(i))) {
                    System.out.println(i + ": And they are identical (yay!)");
                } else {
                    System.out.println(i + ": But they are not identical (booo!)");
                }
            } else {
                System.out.println(i + ": Shape objects are the same (booo!)");
            }
        }
    }

}
