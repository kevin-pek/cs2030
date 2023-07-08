class Shop {
    private final ImList<Server> servers;
    private final int numServed;
    private final int numLeft;

    Shop(int numServers) {
        ImList<Server> servers = new ImList<Server>();
        for (int i = 0; i < numServers; i++) {
            servers = new ImList<Server>()
                .addAll(servers)
                .add(new Server(i + 1));
        }
        this.servers = servers;
        this.numLeft = 0;
        this.numServed = 0;
    }

    Shop(int numServed, int numLeft, ImList<Server> servers) {
        this.numServed = numServed;
        this.numLeft = numLeft;
        this.servers = new ImList<Server>().addAll(servers);
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

    public Shop customerLeft() {
        return new Shop(getNumServed(), getNumLeft() + 1, this.servers);
    }

    public Shop customerServed() {
        return new Shop(getNumServed() + 1, getNumLeft(), this.servers);
    }

    public Pair<Integer, Shop> assignServer(Customer customer) {
        double endTime = customer.getArrivalTime() + customer.getServeTime();
        boolean isServeDone = false;
        ImList<Server> newServers = new ImList<Server>();
        int serverIdx = -1;
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            if (server.isAvailable(customer.getArrivalTime()) && !isServeDone) {
                newServers = new ImList<Server>().addAll(newServers)
                    .add(server.serveCustomer(endTime));
                isServeDone = true;
                serverIdx = i;
            } else {
                newServers = new ImList<Server>().addAll(newServers).add(server);
            }
        }
        Shop newShop = new Shop(this.numServed, this.numLeft, newServers);
        return new Pair<>(serverIdx, newShop);
    }

    @Override
    public String toString() {
        return servers.toString();
    }
}
