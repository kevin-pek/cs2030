class Request {
    private final int dist;
    private final int passengers;
    private final int time;

    Request(int dist, int passengers, int time) {
        this.dist = dist;
        this.passengers = passengers;
        this.time = time;
    }

    public int computeFare(Service service) {
        return service.computeFare(dist, passengers, time);
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", dist, passengers, time);
    }
}
