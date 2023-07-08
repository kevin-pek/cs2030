class Transmitter extends Host {
    Transmitter(String id) {
        super(id);
    }

    Transmitter(Host h, Term t) {
        super(h.getId(), t.toString() + " >--ack--> " + h.getId(), h.connect(t));
    }
}
