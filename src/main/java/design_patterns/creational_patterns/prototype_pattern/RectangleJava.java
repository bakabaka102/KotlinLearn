package design_patterns.creational_patterns.prototype_pattern;

public class RectangleJava extends ShapeJava{

    public int width;
    public int height;

    public RectangleJava() {
    }

    public RectangleJava(RectangleJava target) {
        super(target);
        if (target != null) {
            this.width = target.width;
            this.height = target.height;
        }
    }

    @Override
    public ShapeJava clone() {
        return new RectangleJava(this);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RectangleJava shape2) || !super.equals(other)) return false;
        return shape2.width == width && shape2.height == height;
    }
    /*@Override
    public ShapeJava clone() {
        return new RectangleJava();
    }*/
}
