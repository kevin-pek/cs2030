class Case {
    private final Person person;
    private final Protocol protocol;
    private final ImList<Test> tests;

    Case(Person p, PCR t) {
        this.person = p;
        this.tests = new ImList<Test>().add(t);
        this.protocol = new P1().next(p, t, 0);
    }

    Case(Person person, ImList<Test> t, Protocol p) {
        this.person = person;
        this.tests = t;
        this.protocol = p;
    }

    Case(Case c, Test t) {
        this.person = c.person;
        this.tests = c.tests.add(t);
        this.protocol = c.protocol.next(c.person, t, c.tests.size());
    }

    public Case test(Test t) {
        if (t.isValid()) {
            return new Case(person, tests.add(t), protocol.next(person, t, tests.size()));
        }
        return this;
    }

    public Protocol getCurrentProtocol() {
        return protocol;
    }

    public ImList<Test> getTestHistory() {
        return tests;
    }

    public ImList<Case> getLineage() {
        return new ImList<Case>().add(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", person, getTestHistory(), getCurrentProtocol());
    }
}
