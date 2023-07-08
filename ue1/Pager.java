class Pager extends Term {
    Pager(String id) {
        super(id);
    }

    public SendHost snd(Host h) {
        return new SendTransmitter(h, this);
    }
}
