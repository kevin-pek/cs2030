abstract class Service {
    private final int feePerKm;
    private final int addFee;

    Service(int feePerKm, int addFee) {
        this.feePerKm = feePerKm;
        this.addFee = addFee;
    }

    public int computeFare(int dist, int nPax, int time) {
        return this.addFee + this.feePerKm * dist;
    }
}
