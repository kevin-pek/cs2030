//**** OO Design (c)
//**** Write your answer below

abstract class P {
    private final P other;

    P() {
        this.other = this;
    }

    protected P(P other) {
        this.other = other;
    }

    abstract P set(P other);

    P get() {
        return this.other;
    }
}

class A extends P {
    A() {
        super();
    }

    private A(P other) {
        super(other);
    }

    A set(P other) {
        return new A(other);
    }
}

class B extends P {
    B() {
        super();
    }

    private B(P other) {
        super(other);
    }

    B set(P other) {
        return new B(other);
    }
}
