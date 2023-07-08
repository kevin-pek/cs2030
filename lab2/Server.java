class Server {
    private final double endTime;
    private final int id;

    Server(int id) {
        this(id, 0);
    }

    Server(int id, double endTime) {
        this.id = id;
        this.endTime = endTime;
    }

    public boolean isAvailable(double currentTime) {
        if (currentTime < this.endTime) {
            return false;
        } else {
            return true;
        }
    }

    public Server serveCustomer(double endTime) {
        return new Server(this.id, endTime);
    }

    @Override
    public String toString() {
        return String.format("%d", this.id);
    }
}
