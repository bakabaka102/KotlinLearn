package design_patterns.creational_patterns.prototype_pattern;

public class CircleJava extends ShapeJava {

    public int radius;

    public CircleJava() {
    }

    public CircleJava(CircleJava target) {
        super(target);
        if (target != null) {
            this.radius = target.radius;
        }
    }

    @Override
    public ShapeJava clone() {
        return new CircleJava(this);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CircleJava shape2) || !super.equals(other)) {
            return false;
        }
        return shape2.radius == radius;
    }
    /*@Override
    public ShapeJava clone() {
        return new CircleJava();
    }*/
}
