//**** Streams (b)
//**** Write your answer below

It is unnecessary to define a local variable outside of the stream.
Instead we can use the reduce function to get the sum.
int s = IntStream.range(1,100).boxed().reduce(0, (x, y) -> x + y)
int s = IntStream.range(1,100).sum() // same result
