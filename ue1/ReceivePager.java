class ReceivePager extends Term implements ReceiveTerm {
    private final Host host;
    
    ReceivePager(Host h, Term t) {
        super(t.getId(), h.toString() + " >--rcv--> " + t.getId());
        this.host = h;
    }

    public Host ack() {
        return new Transmitter(host, this);
    }
}
