class SolidCuboid extends Cuboid implements Solid {
    private final double density;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
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
