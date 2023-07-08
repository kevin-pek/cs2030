abstract class Host implements IHost {
    private final ImList<Term> connected;
    private final String id;
    private final String log;

    Host(String id) {
        this.id = id;
        this.log = id;
        this.connected = new ImList<Term>();
    }

    Host(String id, String log, ImList<Term> connected) {
        this.id = id;
        this.log = log;
        this.connected = connected;
    }

    Host(Host h, String log) {
        this.id = h.getId();
        this.log = log;
        this.connected = h.connected;
    }

    protected String getId() {
        return id;
    }

    public void broadcast() {
        for (Term t : connected) {
            System.out.printf("%s:beep\n", t.getId());
        }
    }

    public ImList<Term> connect(Term t) {
        return this.connected.add(t);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Host h) {
            return h.getId() == id;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s", log);
    }
}
