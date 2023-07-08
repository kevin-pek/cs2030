//**** OO Design (d)
//**** Write your answer below

class Main {
    public static void main() {
        A a = new A();
        A a1 = new A();
        B b1 = new B();
        B b2 = new B();
        b2.set(a);
        b1.set(b2);
        a1.set(b1);
        a.set(a1);
        System.out.println(a); // a references an instance of type A
        System.out.println(a.get());
        System.out.println(a.get().get());
        System.out.println(a.get().get().get());
        System.out.println(a.get().get().get().get());
        System.out.println(a.get().get().get().get().get());
    }
}

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
