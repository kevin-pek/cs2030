class Cruise {
    private static final int HOUR = 60;
    private final String id;
    private final int arrivalTime;
    private final int serviceTime;
    private final int numLoadersReq;

    Cruise(String id, int arr, int num, int ser) {
        this.id = id;
        this.arrivalTime = arr;
        this.serviceTime = ser;
        this.numLoadersReq = num;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    public int getArrivalTime() {
        return this.arrivalTime % 100 + (this.arrivalTime / 100) * HOUR;
    }

    public int getNumOfLoadersRequired() {
        return this.numLoadersReq;
    }

    @Override
    public String toString() {
        return this.id + String.format("@%04d", this.arrivalTime);
    }
}
