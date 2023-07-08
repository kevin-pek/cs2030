// Answer 5a below. Do not remove this comment.
List<String> urls = List.of("abc.xyz", "cde.qpr", "xyz.abc")
int processUrl(String url) {
    System.out.println("Fetching from " + url);
    try {
        Thread.sleep(1000);
    } catch (InterruptedException ex) {}
    return url.length();
}

Supplier<Integer> s = () -> urls.stream().
        map(x -> processUrl(x)).
        reduce(0, (x,y) -> x + y);

System.out.println(s.get())

// Answer 5b below. Do not remove this comment.
CompletableFuture<Integer> cf = urls.stream().
        map(x -> CompletableFuture.supplyAsync(() -> processUrl(x))).
        reduce(CompletableFuture.completedFuture(0), (x, y) ->
                x.thenCombine(y, (a, b) -> a + b))

System.out.println(cf.join())
