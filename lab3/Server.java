class Server {
    private final int qmax;
    private final int id;
    private final int numAvail;
    private final boolean isIdle;
    private final double endTime;

    Server(int id, int qmax) {
        this(id, qmax, qmax, true, 0);
    }

    Server(int id, int qmax, int avail, boolean isIdle, double endTime) {
        this.id = id;
        this.qmax = qmax;
        this.numAvail = avail;
        this.isIdle = isIdle;
        this.endTime = endTime;
    }

    public int getId() {
        return this.id;
    }

    public boolean isAvailable() {
        return this.numAvail > 0;
    }

    public boolean isIdle() {
        return this.isIdle;
    }

    public boolean hasQueue() {
        return this.numAvail < this.qmax;
    }

    public double getEndTime() {
        return this.endTime;
    }

    /**
     * Serve a new customer, or queue them depending on state of server.
     */
    public Server serveCustomer(double endTime) {
        if (isIdle()) {
            return new Server(id, qmax, numAvail, false, endTime);
        }
        return new Server(id, qmax, numAvail + 1, false, endTime);
    }

    public Server queueCustomer(Customer customer) {
        return new Server(id, qmax, numAvail - 1, false, endTime);
    }

    // called on DoneEvent, make server idle if no customers in queue
    public Server doneServing() {
        return new Server(id, qmax, numAvail, numAvail == qmax, endTime);
    }

    @Override
    public String toString() {
        return String.format("%d", this.id);
    }
}
