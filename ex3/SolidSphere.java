class SolidSphere extends Sphere implements Solid {
    private final double density;

    SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    public double mass() {
        SolidImpl solid = new SolidImpl(this, density);
        return solid.mass();
    }

    @Override
    public String toString() {
        return String.format("solid-%s with a mass of %.2f", super.toString(), mass());
    }
}
