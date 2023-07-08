abstract class Term {
    private final String id;
    private final String log;

    Term(String id) {
        this.id = id;
        this.log = id;
    }

    Term(String id, String log) {
        this.id = id;
        this.log = log;
    }

    protected String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Term t) {
            return t.getId() == id;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s", log);
    }
}
