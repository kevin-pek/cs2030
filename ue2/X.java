class X {
    static void printLineage(ImList<Case> lineage) {
        boolean first = true;
        for (Case c : lineage) {
            System.out.println((first ? "" : "-> ") + c);
            first = false;
        }
    }

    static void p(Object o) {
        System.out.println(o.toString());
    }

    public static void main(String[] args) {
        System.out.println("--------level 1----------");
        p(new Person("A123", "PP", 8));
        p(new Person("A123", "PP", 8).isVaccinated());
        p(new Person("A123", "PP", 8).isHighRisk());
        p(new Person("B234", "M", 7));
        p(new Person("B234", "M", 7).isVaccinated());
        p(new Person("B234", "M", 7).isHighRisk());
        p(new Person("Z999", "", 1));
        p(new Person("Z999", "", 1).isVaccinated());
        p(new Person("Z999", "", 1).isHighRisk());
        System.out.println("--------level 2----------");
        Test test = new ART("C");
        p(test);
        p(test.isValid());
        p(test.isPositive());
        test = new ART("CT");
        p(test);
        p(test.isValid());
        p(test.isPositive());
        test = new ART("T");
        p(test);
        p(test.isValid());
        p(test.isPositive());
        test = new PCR("omicron");
        p(test);
        p(test.isValid());
        p(test.isPositive());
        test = new PCR("decepticon");
        p(test);
        p(test.isValid());
        p(test.isPositive());
        System.out.println("--------level 3----------");
        // new Case(new Person("A123", "PP", 8), new ART("C")); // ERROR: ART cannot be converted to PCR
        Case c = new Case(new Person("A123", "PP", 8), new PCR("alpha"));
        p(c);
        Protocol p = c.getCurrentProtocol();
        p(p);
        p(p instanceof P1);
        c = c.test(new PCR("beta"));
        p(c);
        c = c.test(new ART("T"));
        p(c);
        c = c.test(new ART("CT"));
        p(c);
        c = c.test(new ART("C"));
        p(c);
        c = c.test(new ART("C"));
        p(c);
        c = c.test(new ART("CT"));
        p(c);
        c = c.test(new ART("C"));
        p(c);
        p(c.getTestHistory());
        p(c.getTestHistory() instanceof ImList);
        System.out.println("--------level 4----------");
        p(new Case(new Person("B234", "M", 7), new PCR("decepticon")));
        c = new Case(new Person("B234", "M", 7), new PCR("delta"));
        p(c);
        p = c.getCurrentProtocol();
        p(p);
        p(p instanceof P2);
        p(c.test(new PCR("zero")));
        c = c.test(new PCR("zero")).test(new ART("C"));
        p(c);
        p(c.test(new ART("C")));
        p(c.test(new ART("C")).test(new ART("C")));
        p(c.test(new PCR("beta")));
        p(c.test(new PCR("beta")).test(new PCR("ok")));
        Test pt = new ART("CT");
        c = new Case(new Person("C345", "MM", 7), new PCR("delta"));
        p(c);
        c = c.test(pt);
        p(c);
        c = c.test(pt);
        p(c);
        c = c.test(pt);
        p(c);
        c = c.test(pt);
        p(c);
        c = c.test(new ART("T"));
        p(c);
        c = c.test(pt);
        p(c);
        c = c.test(pt);
        p(c);
        c = c.test(pt);
        p(c);
        c = c.test(new ART("C"));
        p(c);
        p(c.test(pt));
        p(c.test(pt).test(pt));
        p(c.test(pt).test(pt).test(new ART("C")));
        System.out.println("-------level 5---------");
        c = new Case(new Person("A123", "PP", 8), new PCR("omicron"));
        p(c);
        p(new ContactCase(new Person("B234", "M", 7), new ART("CT"), c));
        Test t = new ART("C");
        p(t);
        Case d = new ContactCase(new Person("B234", "M", 7), t, c);
        p(d);
        p = d.getCurrentProtocol();
        p(p instanceof P3);
        d = d.test(t);
        p(d);
        d = d.test(t);
        p(d);
        d = d.test(t);
        p(d);
        d = d.test(t);
        p(d);
        d = d.test(t);
        p(d);
        d = d.test(t);
        p(d);
        d = d.test(new PCR("delta"));
        p(d);
        System.out.println("-------lineage---------");
        printLineage(c.getLineage());
        printLineage(d.getLineage());
        ContactCase e = new ContactCase(new Person("C345", "", 10), new ART("CT"), d);
        printLineage(e.getLineage());
    }
}
