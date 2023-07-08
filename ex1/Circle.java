class Circle {
    private final Point centre;
    private final double radius;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public Point getCentre() {
        return centre;
    }

    public double getRadius() {
        return radius;
    }

    public boolean contains(Point p) {
        return p.distanceTo(centre) <= radius;
    }

    @Override
    public String toString() {
        return "circle of radius " + radius + " centred at " + centre;
    }
}
