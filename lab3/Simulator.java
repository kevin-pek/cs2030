import java.util.function.Supplier;

class Simulator {
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTimes;
    private final int numServers;
    private final int qmax;

    Simulator(int numServers, int qmax, ImList<Double> arrivalTimes,
            Supplier<Double> serviceTimes) {
        this.numServers = numServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        this.qmax = qmax;
    }

    public String simulate() {
        Shop shop = new Shop(this.numServers, this.qmax);
        // create an event for each customer
        PQ<Event> events = new PQ<Event>(new EventComp());
        for (int i = 0; i < arrivalTimes.size(); i++) {
            double arrTime = arrivalTimes.get(i);
            Customer newCustomer = new Customer(i + 1, arrTime, serviceTimes);
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
            } else if (event.toString().equals("")) {
                continue;
            }
            log = String.format("%s\n%s", log, event.toString());
        }
        log = String.format("%s\n[%.3f %d %d]", log, shop.getAvgWaitTime(),
                shop.getNumServed(), shop.getNumLeft());
        return log;
    }
}
