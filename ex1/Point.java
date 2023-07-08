class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private final double getX() {
        return this.x;
    }

    private final double getY() {
        return this.y;
    }

    public double distanceTo(Point q) {
        double dx = getX() - q.getX();
        double dy = getY() - q.getY();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public Point moveTo(double angle, double distance) {
        return new Point(getX() + distance * Math.cos(angle), getY() + distance * Math.sin(angle));
    }

    public double angleTo(Point p) {
        return Math.atan2(p.getY() - getY(), p.getX() - getX());
    }

    public Point midPoint(Point p) {
        return new Point((p.getX() + getX()) / 2, (p.getY() + getY()) / 2);
    }

    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", getX(), getY());
    }
}
