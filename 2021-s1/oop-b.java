//**** OO Design (b)
//**** Write your answer below

abstract class P {
    private P other;

    void set(P other) {
        this.other = other;
    }

    P get() {
        return this.other;
    }
}

class A extends P {
}

class B extends P{
}
