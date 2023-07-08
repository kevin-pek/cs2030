/open A.java
CompletableFuture<A> foo(A a) {
return CompletableFuture.<A>supplyAsync(() -> a.incr()) .thenApply(x -> x.decr());
}
CompletableFuture<A> a = foo(new A())
System.out.println("testing");
System.out.println(a.join())

CompletableFuture<A> foo(A a) {
return CompletableFuture.<A>supplyAsync(() -> a.incr().decr());
}
CompletableFuture<A> a = foo(new A())
System.out.println("testing");
System.out.println(a.join())

CompletableFuture<A> foo(A a) {
    return CompletableFuture.<A>supplyAsync(() -> a.incr())
        .thenApplyAsync(x -> x.decr());
}
CompletableFuture<A> a = foo(new A())
System.out.println("testing");
System.out.println(a.join())

CompletableFuture<A> bar(A a) {
    return CompletableFuture.<A>supplyAsync(() -> a.incr());
}
CompletableFuture<A> b = foo(new A()).thenCompose(x -> bar(x))
System.out.println(b.join())

CompletableFuture<A> baz(A a, int x) {
    if (x == 0) {
        return CompletableFuture.completedFuture(new A(0));
    } else {
        return CompletableFuture.<A>supplyAsync(() -> a.incr().decr());
    }
}
CompletableFuture<A> c = baz(new A(), 1)
System.out.println(c.join())

CompletableFuture<Void> all = CompletableFuture.<Void>allOf(
    foo(new A()),
    bar(new A()),
    baz(new A(), 1))
all.join()
System.out.println("done!")

CompletableFuture<A> exc = CompletableFuture .<A>supplyAsync(() -> new A().decr().decr()) .handle((result, exception) -> {
if (result == null) { System.out.println("ERROR PROCESS 1: " + exception); return new A();
              } else {
                  return result;
} });
CompletableFuture<A> exc = CompletableFuture .<A>supplyAsync(() -> new A().decr().decr()).
    exceptionally((exception) -> {
        System.out.println("ERROR PROCESS 2: " + exception); return new A();
    });
// calling exc.join() runs both of the previous functions for some reason
System.out.println(exc.join());

/exit
