class Test {
    static void print(Object o) {
        System.out.println(o.toString());
    }

    public static void main(String[] args) {
        Pager p1 = new Pager("pager1");
        print(p1);
        Term t1 = p1;
        System.out.println("--------level 1-------");
        print(t1);
        print(t1 instanceof Pager);
        print(t1 instanceof Term);
        print(t1.equals(p1));
        print(p1.equals(t1));
        print(p1.equals(new Pager("pager1")));
        print(t1.equals(new Pager("pager1")));
        print(p1.equals("pager1"));
        System.out.println("--------level 2-------");
        Transmitter r1 = new Transmitter("transmit1");
        print(t1);
        print(p1.snd(r1));
        print(p1.snd(r1).equals(r1));
        Host h1 = r1;
        print(h1);
        print(p1.snd(r1).equals(h1));
        print(r1.equals(h1));
        print(h1.equals(p1));
        System.out.println("--------level 3-------");
        print(p1.snd(r1).rcv());
        print(p1.snd(r1).rcv().equals(p1));
        print(p1.snd(r1).equals(r1));
        System.out.println("--------level 4-------");
        print(p1.snd(r1).rcv().ack());
        print(p1.snd(r1).rcv().equals(p1));
        print(p1.snd(r1).rcv().ack().equals(r1));
        print(p1.snd(r1).rcv().ack().equals(p1.snd(r1)));
        System.out.println("--------level 5-------");
        print(p1.snd(r1));
        print(p1.snd(r1).rcv().ack());
        Pager p2 = new Pager("pager2");
        print(p2);
        h1 = p2.snd(p1.snd(r1).rcv().ack()).rcv().ack();
        print(h1);
        Host h2 = p2.snd(r1).rcv().ack();
        print(h2);
        h1.broadcast();
        h2.broadcast();
        p1.snd(r1).broadcast();
        p1.snd(r1).rcv().ack().broadcast();
        p2.snd(p1.snd(r1).rcv().ack()).broadcast();

    }
}
