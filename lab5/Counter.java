interface Counter {
    int getId();

    boolean isIdle();

    Counter serveCustomer(double endTime);

    Counter doneServing();

    double getEndTime();
}
