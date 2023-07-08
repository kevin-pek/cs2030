//**** OO Design (a)
//**** Write your answer below
class A {
    private A other;
    void set(A other) {
        this.other = other;
    }
    A get() {
        return this.other;
    }
}
A a1 = new A();
A a2 = new A();
a1.set(a2);
a2.set(a1);

System.out.println(a1.get() == a1)
System.out.println(a2.get() == a2)
System.out.println(a2.get() == a1)
System.out.println(a1.get() == a2)
/exit
