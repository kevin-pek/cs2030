class Shop {
    private final ImList<Event> events;
    private final int numCustomersServed;
    private final int numCustomersLeft;

    Shop(int numServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        int numCustomersServed = 0;
        int numCustomersLeft = 0;
        // initialise servers in the shop
        ImList<Server> servers = new ImList<Server>();
        for (int i = 0; i < numServers; i++) {
            servers = new ImList<Server>().addAll(servers).add(new Server(i + 1));
        }

        // create an event for each customer
        ImList<Event> events = new ImList<Event>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer newCustomer = new Customer(i + 1, arrivalTimes.get(i), serviceTimes.get(i));
            events = new ImList<Event>().addAll(events)
                    .add(new ArrivalEvent(arrivalTimes.get(i), newCustomer));

            int availServer = getAvailableServer(servers, arrivalTimes.get(i));
            if (availServer == -1) {
                numCustomersLeft++;
                events = new ImList<Event>().addAll(events)
                        .add(new LeaveEvent(arrivalTimes.get(i), newCustomer));
            } else {
                numCustomersServed++;
                servers = assignServer(servers, arrivalTimes.get(i),
                        arrivalTimes.get(i) + serviceTimes.get(i));
                events = new ImList<Event>().addAll(events)
                        .add(new ServeEvent(arrivalTimes.get(i), 
                                    newCustomer, servers.get(availServer)));
            }
        }
        this.events = events;
        this.numCustomersLeft = numCustomersLeft;
        this.numCustomersServed = numCustomersServed;
    }

    private int getAvailableServer(ImList<Server> servers, double time) {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).isAvailable(time)) {
                return i;
            }
        }
        return -1;
    }

    private ImList<Server> assignServer(ImList<Server> servers, double startTime, double endTime) {
        boolean isServeDone = false;
        ImList<Server> newServers = new ImList<Server>();
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            if (server.isAvailable(startTime) && !isServeDone) {
                newServers = new ImList<Server>().addAll(newServers)
                        .add(server.serveCustomer(endTime));
                isServeDone = true;
            } else {
                newServers = new ImList<Server>().addAll(newServers).add(servers.get(i));
            }
        }
        return newServers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < events.size(); i++) {
            sb.append(events.get(i).toString() + "\n");
        }
        sb.append(String.format("[%d %d]", numCustomersServed, numCustomersLeft));
        return sb.toString();
    }
}
