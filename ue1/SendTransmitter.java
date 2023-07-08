class SendTransmitter extends Host implements SendHost {
    private final Term term;

    SendTransmitter(Host h, Term t) {
        super(h, t.toString() + " >--snd--> " + h.getId());
        this.term = t;
    }

    public ReceiveTerm rcv() {
        return new ReceivePager(this, term);
    }
}
