class SolidImpl implements Solid {
    private final Shape3D shape;
    private final double density;

    SolidImpl(Shape3D shape, double density) {
        this.shape = shape;
        this.density = density;
    }

    public double volume() {
        return shape.volume();
    }

    public double mass() {
        return density * volume();
    }

    @Override
    public String toString() {
        return "SolidImpl";
    }
}
