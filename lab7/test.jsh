System.out.println("LEVEL 1");
Log.<Integer>of(5);

Log.<Integer>of(5, "five");

Log<String> hello = Log.<String>of("Hello");

try { Log.<Object>of(hello); }
catch (Exception e) { System.out.println(e); }

try { Log.<Integer>of(null); }
catch (Exception e) { System.out.println(e); }

try { Log.<Integer>of(5, null); }
catch (Exception e) { System.out.println(e); }
System.out.println("LEVEL 2");
Log.<Integer>of(5, "five").map(x -> x + 1);
Log.<Integer>of(5, "five").map(x -> x + 1).map(x -> x * 2);
Log.<String>of("five", "five").map(x -> x.length()).map(x -> x * 2);
System.out.println("LEVEL 3");
Function<Integer, Log<Integer>> f = x -> Log.<Integer>of(x + 1, "add 1");
Function<Integer, Log<Integer>> g = x -> Log.<Integer>of(x, "mul 2").
    map(y -> y * 2);
Log.<Integer>of(5, "five").flatMap(f);
Log.<Integer>of(5, "five").flatMap(f).flatMap(g);
System.out.println("LEVEL 4");
Log<Integer> five = Log.<Integer>of(5);
five.equals(five);
Log.<Integer>of(5).equals(five);
five.equals(5);
Log.<Integer>of(5, "five").equals(five);
Log.<Integer>of(5, "").equals(five);
Function<Log<Integer>,Boolean> idf = x -> x.map(y -> y).equals(x); // functor identity
idf.apply(Log.<Integer>of(5));
Function<Integer, Integer> f = x -> x + 1;
Function<Integer, Integer> g = x -> x * 2;
Log.<Integer>of(5).map(f).map(g).
    equals(Log.<Integer>of(5).map(g.compose(f))); // functor associativity
Log.<Integer>of(5).flatMap(x -> Log.<Integer>of(x)).equals(Log.<Integer>of(5)); // monad right identity
Function<Integer, Log<Integer>> f = x -> Log.<Integer>of(x + 1, "add 1");
Function<Integer,Boolean> idleft = x -> Log.<Integer>of(x).flatMap(f).equals(f.apply(x)); // monad left identity
idleft.apply(5);
Function<Integer, Log<Integer>> g = x -> Log.<Integer>of(x, "mul 2").map(y -> y * 2);
Function<Integer,Boolean> assoc = x -> Log.<Integer>of(x).flatMap(f).flatMap(g).
    equals(f.apply(x).flatMap(g)); // monad associativity
assoc.apply(5);
System.out.println("LEVEL 5");
