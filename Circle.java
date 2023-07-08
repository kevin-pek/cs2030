class Circle {
    private final int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Circle c) {
        return this.radius == c.radius;
    }

    @Override
    public String toString() {
        return "Circle with radius " + this.radius;
    }
}
