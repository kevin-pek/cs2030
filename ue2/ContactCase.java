class ContactCase extends Case {
    private final Case contact;

    ContactCase(Person p, Test t, Case c) {
        super(p, new ImList<Test>().add(t), new P3().next(p, t, 0));
        this.contact = c;
    }

    ContactCase(ContactCase c, Test t) {
        super(c, t);
        this.contact = c.contact;
    }

    public Case test(Test t) {
        if (t.isValid()) {
            return new ContactCase(this, t);
        }
        return this;
    }

    @Override
    public ImList<Case> getLineage() {
        return new ImList<Case>().addAll(contact.getLineage()).add(this);
    }
}
