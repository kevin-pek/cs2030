class Cuboid implements Shape3D {
    private final double height;
    private final double length;
    private final double width;

    Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double volume() {
        return length * height * width;
    }

    @Override
    public String toString() {
        return String.format("cuboid [%.2f x %.2f x %.2f]",
                height, width, length);
    }
}
