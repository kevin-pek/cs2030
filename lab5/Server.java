interface Server extends Counter {
    Server get(int id);

    boolean isAvailable();

    int getIdleServerId();

    double getRestTime();

    Server startRest(double time);

    Server serveCustomer(double endTime);

    Server queueCustomer(Customer customer);

    Server doneServing();

    Server endRest();

    boolean hasQueue();

    Server moveQueue();
}
