class SelfCheckoutServer implements Server {
    private final ImList<Counter> counters;
    private final int lastQueryId; // tracks id of last counter queried for printing
    private final int qmax;
    private final int numAvail;
    private final PQ<Pair<Integer, Double>> ongoing; // pair of id and time for ongoing services

    SelfCheckoutServer(int id, int numCounters, int qmax) {
        ImList<Counter> counters = new ImList<Counter>();
        for (int i = 0; i < numCounters; i++) {
            counters = counters.add(new SelfCheckoutCounter(id + i));
        }
        this.lastQueryId = id;
        this.counters = counters;
        this.qmax = qmax;
        this.numAvail = qmax;
        this.ongoing = new PQ<Pair<Integer, Double>>(
                (p1, p2) -> p1.second() - p2.second() < 0 ? -1 : 1);
    }

    SelfCheckoutServer(int id, int numAvail, int qmax, ImList<Counter> counters,
            PQ<Pair<Integer, Double>> ongoing) {
        this.counters = new ImList<Counter>().addAll(counters);
        this.qmax = qmax;
        this.numAvail = numAvail;
        this.lastQueryId = id;
        this.ongoing = ongoing;
    }

    public int getId() {
        return this.lastQueryId;
    }

    /**
     * Update lastQuery when getServer is called in Shop class. Used to keep track
     * of last queried counter for serveCustomer.
     */
    public Server get(int id) {
        return new SelfCheckoutServer(id, numAvail, qmax, counters, ongoing);
    }

    /**
     * Get earliest end time of all ongoing services at self checkout counters.
     */
    public double getEndTime() {
        if (ongoing.isEmpty()) {
            return 0.0;
        }
        return ongoing.poll().first().second();
    }

    /**
     * Update counter of given id with given server.
     */
    public Server updateCounter(int id, Server server) {
        for (int i = 0; i < counters.size(); i++) {
            if (counters.get(i).getId() == id) {
                return new SelfCheckoutServer(id, numAvail, qmax,
                        counters.set(i, server), ongoing);
            }
        }
        return this;
    }

    /**
     * Serve a new customer at the first available counter, with service ending 
     * at provided time. Called by ServeEvent.
     */
    public Server serveCustomer(double endTime) {
        int id = this.lastQueryId;
        ImList<Counter> counters = new ImList<Counter>().addAll(this.counters);
        for (int i = 0; i < counters.size(); i++) {
            if (counters.get(i).isIdle()) {
                counters = counters.set(i, counters.get(i).serveCustomer(endTime));
                id = counters.get(i).getId();
                // if there is a queue, remove 1 customer from queue
                if (numAvail < qmax) {
                    return new SelfCheckoutServer(id, numAvail + 1, qmax, counters,
                            ongoing.add(new Pair<Integer, Double>(id, endTime)));
                } else {
                    return new SelfCheckoutServer(id, numAvail, qmax, counters,
                            ongoing.add(new Pair<Integer, Double>(id, endTime)));
                }
            }
        }
        return this;
    }

    /**
     * Add customer to queue, assuming there is an available spot. Called by WaitEvent.
     */
    public Server queueCustomer(Customer customer) {
        int id = this.counters.get(0).getId();
        return new SelfCheckoutServer(id, numAvail - 1, qmax, counters, ongoing);
    }

    /**
     * Updates counter that ends service the earliest to be idle. Called by ServeDoneEvent.
     * @return Server SelfCheckoutServer with updated counters
     */
    public Server doneServing() {
        ImList<Counter> counters = new ImList<Counter>().addAll(this.counters);
        Pair<Pair<Integer, Double>, PQ<Pair<Integer, Double>>> p = ongoing.poll();
        int id = p.first().first();
        for (int i = 0; i < counters.size(); i++) {
            if (counters.get(i).getId() == id) {
                counters = counters.set(i, counters.get(i).doneServing());
                break;
            }
        }
        return new SelfCheckoutServer(id, numAvail, qmax, counters, ongoing);
    }

    /**
     * Removes the ongoing service that ends the earliest. Called by WaitDoneEvent.
     * @return Server SelfCheckoutServer with updated ongoing service queue and
     *        id of next available counter id for next customer.
     */
    public Server moveQueue() {
        return new SelfCheckoutServer(getIdleServerId(), numAvail, qmax, counters,
                ongoing.poll().second());
    }

    /**
     * Returns whether there is an available spot in the queue.
     */
    public boolean isAvailable() {
        return this.numAvail > 0;
    }

    /**
     * Returns whether there is an idle self checkout counter.
     */
    public boolean isIdle() {
        for (int i = 0; i < counters.size(); i++) {
            if (counters.get(i).isIdle()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get id of self checkout counter that is not serving anyone.
     * Otherwise, return -1.
     */
    public int getIdleServerId() {
        for (int i = 0; i < counters.size(); i++) {
            Counter counter = counters.get(i);
            if (counter.isIdle()) {
                return counter.getId();
            }
        }
        return -1;
    }

    public boolean hasQueue() {
        return this.numAvail < this.qmax;
    }

    @Override
    public String toString() {
        return String.format("self-check %d", getId());
    }

    public double getRestTime() {
        return 0.0;
    }

    public Server startRest(double time) {
        return this;
    }

    public Server endRest() {
        return this;
    }
}
