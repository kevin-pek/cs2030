double epsilon = 1E-15;

boolean circleContainsPoint(Circle c, Point p) {
    return c.getCentre().distanceTo(p) < c.getRadius() + epsilon;
}

int findMaxDiscCoverage(ImList<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();

    for (int i = 0; i < numOfPoints - 1; i++) {
        for (int j = i + 1; j < numOfPoints; j++) {
            Point p = points.get(i);
            Point q = points.get(j);
            double distPQ = p.distanceTo(q);
            if (distPQ < 2.0 + epsilon && distPQ > 0) {
                Circle c = createUnitCircle(p, q);

                int coverage = 0;
                for (Point point : points) {
                    if (circleContainsPoint(c, point)) {
                        coverage = coverage + 1;
                    }
                }
                if (coverage > maxDiscCoverage) {
                    maxDiscCoverage = coverage;
                }
            }
        }
    }
    return maxDiscCoverage;
}

Circle createUnitCircle(Point p, Point q) {
    double angle = p.angleTo(q);
    Point midpoint = p.midPoint(q);
    Point newpoint = midpoint.moveTo(angle + Math.PI / 2, Math.sqrt(1 - Math.pow(p.distanceTo(midpoint), 2)));
    return new Circle(newpoint, 1.0);
}
