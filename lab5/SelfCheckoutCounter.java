class SelfCheckoutCounter implements Counter {
    private final int id;
    private final boolean isIdle;
    private final double endTime;

    SelfCheckoutCounter(int id) {
        this(id, true, 0);
    }

    SelfCheckoutCounter(int id, boolean isIdle, double endTime) {
        this.id = id;
        this.isIdle = isIdle;
        this.endTime = endTime;
    }

    public int getId() {
        return this.id;
    }

    public boolean isIdle() {
        return this.isIdle;
    }

    public double getEndTime() {
        return this.endTime;
    }

    public Counter serveCustomer(double endTime) {
        return new SelfCheckoutCounter(id, false, endTime);
    }

    public Counter doneServing() {
        return new SelfCheckoutCounter(id, true, endTime);
    }

    @Override
    public String toString() {
        return String.format("self-check %d", getId());
    }
}
