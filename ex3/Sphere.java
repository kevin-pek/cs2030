class Sphere implements Shape3D {
    private final double radius;
    private static final int POWER = 3;
    private static final double FOUR = 4;
    private static final double THREE = 3;

    Sphere(double radius) {
        this.radius = radius;
    }

    public double volume() {
        return Math.PI * Math.pow(radius, POWER) * FOUR / THREE;
    }

    @Override
    public String toString() {
        return String.format("sphere [%.2f]", radius);
    }
}
