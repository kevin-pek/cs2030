class Shop {
    private final ImList<Server> servers;
    private final int numServed;
    private final int numLeft;
    private final double totalWaitTime;

    Shop(int numServers, int qmax) {
        ImList<Server> servers = new ImList<Server>();
        for (int i = 0; i < numServers; i++) {
            servers = new ImList<Server>()
                    .addAll(servers)
                    .add(new Server(i + 1, qmax));
        }
        this.servers = servers;
        this.numLeft = 0;
        this.numServed = 0;
        this.totalWaitTime = 0;
    }

    Shop(int numServed, int numLeft, double totalWaitTime, ImList<Server> servers) {
        this.numServed = numServed;
        this.numLeft = numLeft;
        this.servers = new ImList<Server>().addAll(servers);
        this.totalWaitTime = totalWaitTime;
    }

    public Server getServer(int idx) {
        return this.servers.get(idx);
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
        return new Shop(getNumServed(), getNumLeft() + 1, totalWaitTime, this.servers);
    }

    public Shop customerServed() {
        return new Shop(getNumServed() + 1, getNumLeft(), totalWaitTime, this.servers);
    }

    public Shop addWaitTime(double waitTime) {
        return new Shop(getNumServed(), getNumLeft(), totalWaitTime + waitTime, this.servers);
    }

    /**
     * Assigns customer to the server at the given index.
     * 
     * @return index of the available server.
     */
    public Shop assignServer(int id, double endTime) {
        ImList<Server> newServers = new ImList<Server>();
        for (int i = 0; i < servers.size(); i++) {
            Server server = getServer(i);
            if (server.getId() == id) {
                server = server.serveCustomer(endTime);
                newServers = new ImList<Server>().addAll(newServers).add(server);
            } else {
                newServers = new ImList<Server>().addAll(newServers).add(server);
            }
        }
        return new Shop(this.numServed, this.numLeft, this.totalWaitTime, newServers);
    }

    public Shop queueCustomer(int id, Customer customer) {
        ImList<Server> newServers = new ImList<Server>();
        for (int i = 0; i < servers.size(); i++) {
            Server server = getServer(i);
            if (server.getId() == id) {
                server = server.queueCustomer(customer);
                newServers = new ImList<Server>().addAll(newServers).add(server);
            } else {
                newServers = new ImList<Server>().addAll(newServers).add(server);
            }
        }
        return new Shop(this.numServed, this.numLeft, this.totalWaitTime, newServers);
    }


    public Shop doneServing(int id) {
        ImList<Server> newServers = new ImList<Server>();
        for (int i = 0; i < servers.size(); i++) {
            Server server = getServer(i);
            if (server.getId() == id) {
                server = server.doneServing();
                newServers = new ImList<Server>().addAll(newServers).add(server);
            } else {
                newServers = new ImList<Server>().addAll(newServers).add(server);
            }
        }
        return new Shop(this.numServed, this.numLeft, this.totalWaitTime, newServers);
    }

    /**
     * Check if there is an idle server available.
     */
    public int getIdleServer() {
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            if (server.isIdle()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Check if there is a server with an available queue.
     */
    public int getAvailServer() {
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            if (server.isAvailable()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return servers.toString();
    }
}
