import java.util.concurrent.CompletableFuture;

class A {
    private final int x;

    A() {
        this(0);
    }

    A(int x) {
        this.x = x;
    }

    void sleep() {
        System.out.println(Thread.currentThread().getName() + " " + x);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("interrupted"); }
    }

    A incr() {
        sleep();
        return new A(this.x + 1);
    }

    A decr() {
        sleep();
        if (x < 0) {
            throw new IllegalStateException();
        }
        return new A(this.x - 1);
    }

    void test() {
        CompletableFuture<A> exc = CompletableFuture
            .<A>supplyAsync(() -> new A().decr().decr())
            .exceptionally((exception) -> {
                System.out.println("ERROR PROCESS 2: " + exception); return new A();
            });
        exc = CompletableFuture.<A>supplyAsync(() -> new A().decr().decr())
            .handle((result, exception) -> {
                if (result == null) {
                    System.out.println("ERROR PROCESS 1: " + exception);
                    return new A();
                } else {
                    return result;
                }
            });
        exc.join();
    }

    public String toString() {
        return "" + x;
    }
}
