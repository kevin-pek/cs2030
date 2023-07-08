class Simulator {
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes;
    private final int numServers;

    Simulator(int numServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        this.numServers = numServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
    }

    public String simulate() {
        Shop shop = new Shop(this.numServers);
        // create an event for each customer
        PQ<Event> events = new PQ<Event>(new EventComp());
        for (int i = 0; i < arrivalTimes.size(); i++) {
            double arrTime = arrivalTimes.get(i);
            double serveTime = serviceTimes.get(i);
            Customer newCustomer = new Customer(i + 1, arrTime, serveTime);
            events = events
                .add(new ArrivalEvent(newCustomer));
        }

        String log = "";
        while (!events.isEmpty()) {
            Pair<Event, PQ<Event>> pair = events.poll();
            Event event = pair.first();
            events = pair.second();
            Pair<ImList<Event>, Shop> execResultPair = event.execute(shop);
            for (Event e : execResultPair.first()) {
                events = events.add(e);
            }
            shop = execResultPair.second();
            if (log.equals("")) {
                log = event.toString();
                continue;
            }
            log = String.format("%s\n%s", log, event.toString());
        }
        log = String.format("%s\n[%d %d]", log, shop.getNumServed(), shop.getNumLeft());
        return log;
    }
}
