class Route {
    private final String origin;
    private final String destination;
    private final String distance;
    
    // Answer q1(a) below. Do not remove this comment.
    Route(String origin, String destination, String distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
        this.distance = "";
    }

    // Answer q1(b) below. Do not remove this comment.
    String getDistance() {
        return distance;
    }

    // Answer q1(c) below. Do not remove this comment.
    @Override
    public String toString() {
        return String.format("%s --> %s", origin, destination);
    }

    // Answer q2(d) below. Do not remove this comment.
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Route r) {
            return origin.toLowerCase().equals(r.origin.toLowerCase()) &&
                destination.toLowerCase().equals(r.destination.toLowerCase());
        }
        return false;
    }
} // end of class Route. Do not remove this comment.
