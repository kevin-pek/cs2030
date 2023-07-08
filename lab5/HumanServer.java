import java.util.function.Supplier;

class HumanServer implements Server {
    private final int qmax;
    private final int id;
    private final int numAvail;
    private final boolean isIdle;
    private final double endTime;
    private final Supplier<Double> restTimes;

    HumanServer(int id, int qmax, Supplier<Double> restTimes) {
        this(id, qmax, qmax, true, 0, restTimes);
    }

    HumanServer(int id, int qmax, int avail, boolean isIdle, double endTime,
            Supplier<Double> restTimes) {
        this.id = id;
        this.qmax = qmax;
        this.numAvail = avail;
        this.isIdle = isIdle;
        this.endTime = endTime;
        this.restTimes = restTimes;
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

    public double getEndTime() {
        return this.endTime;
    }

    public double getRestTime() {
        return this.restTimes.get();
    }

    public Server startRest(double time) {
        return new HumanServer(id, qmax, numAvail, false, time + getEndTime(), restTimes);
    }

    public Server endRest() {
        return new HumanServer(id, qmax, numAvail, numAvail == qmax, endTime, restTimes);
    }

    /**
     * Serve a new customer, or queue them depending on state of server.
     */
    public Server serveCustomer(double endTime) {
        if (isIdle()) {
            return new HumanServer(id, qmax, numAvail, false, endTime, restTimes);
        }
        return new HumanServer(id, qmax, numAvail + 1, false, endTime, restTimes);
    }

    public Server queueCustomer(Customer customer) {
        return new HumanServer(id, qmax, numAvail - 1, false, endTime, restTimes);
    }

    // called on DoneEvent, make server idle if no customers in queue
    public Server doneServing() {
        return new HumanServer(id, qmax, numAvail, numAvail == qmax, endTime, restTimes);
    }

    @Override
    public String toString() {
        return String.format("%d", this.id);
    }

    public int getIdleServerId() {
        if (isIdle()) {
            return this.id;
        }
        return -1;
    }

    public Server get(int id) {
        return this;
    }

    public boolean hasQueue() {
        return this.numAvail < this.qmax;
    }

    public Server moveQueue() {
        return this;
    }
}
