import java.util.function.Supplier;

class Shop {
    private final Supplier<Double> restTimes;
    private final ImList<Server> servers;
    private final int numHumanServers;
    private final int numServed;
    private final int numLeft;
    private final double totalWaitTime;

    Shop(int numServers, int numSelfChecks, Supplier<Double> restTimes, int qmax) {
        ImList<Server> servers = new ImList<Server>();
        for (int i = 0; i < numServers; i++) {
            servers = servers.add(new HumanServer(i + 1, qmax, restTimes));
        }
        // self checkout counters are treated as 1 server with multiple counters
        if (numSelfChecks > 0) {
            servers = servers.add(new SelfCheckoutServer(numServers + 1, numSelfChecks, qmax));
        }
        this.servers = servers;
        this.restTimes = restTimes;
        this.numLeft = 0;
        this.numServed = 0;
        this.totalWaitTime = 0;
        this.numHumanServers = numServers;
    }

    Shop(int numServed, int numLeft, double totalWaitTime, int numHumanServers,
            Supplier<Double> restTimes, ImList<Server> servers) {
        this.numServed = numServed;
        this.numLeft = numLeft;
        this.restTimes = restTimes;
        this.servers = servers;
        this.totalWaitTime = totalWaitTime;
        this.numHumanServers = numHumanServers;
    }

    public int getNumServed() {
        return this.numServed;
    }

    public int getNumLeft() {
        return this.numLeft;
    }

    public double getAvgWaitTime() {
        if (getNumServed() == 0) {
            return 0;
        }
        return this.totalWaitTime / this.numServed;
    }

    public Shop customerLeft() {
        return new Shop(getNumServed(), getNumLeft() + 1, this.totalWaitTime,
                this.numHumanServers, this.restTimes, this.servers);
    }

    public Shop customerServed() {
        return new Shop(getNumServed() + 1, getNumLeft(), this.totalWaitTime,
                this.numHumanServers, this.restTimes, this.servers);
    }

    public Shop addWaitTime(double waitTime) {
        return new Shop(getNumServed(), getNumLeft(), totalWaitTime + waitTime,
                this.numHumanServers, this.restTimes, this.servers);
    }

    public Server getServer(int id) {
        if (id <= this.numHumanServers) {
            return this.servers.get(id - 1);
        }
        return this.servers.get(this.numHumanServers).get(id);
    }

    public Shop updateServer(int id, Server server) {
        if (id > this.numHumanServers) {
            return new Shop(this.numServed, this.numLeft, this.totalWaitTime,
                    this.numHumanServers, this.restTimes,
                    this.servers.set(this.numHumanServers, server));
        }
        return new Shop(this.numServed, this.numLeft, this.totalWaitTime,
                this.numHumanServers, this.restTimes, this.servers.set(id - 1, server));
    }

    public int getIdleServerId() {
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            if (server.isIdle()) {
                if (i < this.numHumanServers) {
                    return server.getId();
                } else {
                    return server.getIdleServerId();
                }
            }
        }
        return -1;
    }

    public int getAvailServerId() {
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            if (server.isAvailable()) {
                if (i < this.numHumanServers) {
                    return server.getId();
                } else {
                    return this.numHumanServers + 1;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return servers.toString();
    }
}
