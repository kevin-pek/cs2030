class Customer {
    private final double arrivalTime;
    private final double serveTime;
    private final int id;

    Customer(int id, double arrivalTime, double serveTime) {
        this.arrivalTime = arrivalTime;
        this.serveTime = serveTime;
        this.id = id;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServeTime() {
        return this.serveTime;
    }

    @Override
    public String toString() {
        return String.format("%d", id);
    }
}
