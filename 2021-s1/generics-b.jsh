//**** Generics (b)
//**** Write your answer below

abstract class A {
    int i;

    A(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return String.format("%d", i);
    }
}

BiPredicate<A, A> pred = (x, y) -> x.i > y.i;

class B extends A {
    B(int i) {
        super (i);
    }
}

List<B> dst = new ArrayList<>(List.of(new B(0), new B(1), new B(2)))
replace(new ArrayList<>(List.of(new B(9), new B(9), new B(9))), dst, pred)
