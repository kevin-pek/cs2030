import java.util.Optional;

class Fraction extends AbstractNum<Frac> {
    private Fraction(AbstractNum<Frac> n) {
        super(n.opt);
    }

    private static Fraction invalid() {
        return new Fraction(new AbstractNum<Frac>(Optional.empty()));
    }

    static Fraction of(int num, int den) {
        Num n = Num.of(num);
        Num d = Num.of(den);
        if (!valid.test(num) || !valid.test(den)) {
            return invalid();
        } else if (d.equals(Num.zero())) {
            return invalid();
        }
        return new Fraction(new AbstractNum<Frac>(
                    Frac.of(n, d)));
    }

    Fraction add(Fraction frac) {
        if (isValid() && frac.isValid()) {
            Optional<Num> n1 = opt.map(x -> x.first())
                    .flatMap(x -> frac.opt.map(y -> y.second().mul(x)));
            Optional<Num> n2 = opt.map(x -> x.second())
                    .flatMap(x -> frac.opt.map(y -> y.first().mul(x)));
            Optional<Num> numer = n1.flatMap(x -> n2.map(y -> x.add(y)));
            Optional<Num> denom = opt.map(x -> x.second())
                    .flatMap(x -> frac.opt.map(y -> y.second().mul(x)));
            Optional<Frac> fr = numer
                    .flatMap(x -> denom.map(y -> Frac.of(x, y)));
            return new Fraction(new AbstractNum<Frac>(fr));
        }
        return invalid();
    }

    Fraction sub(Fraction frac) {
        if (isValid() && frac.isValid()) {
            Optional<Num> n1 = opt.map(x -> x.first())
                    .flatMap(x -> frac.opt.map(y -> y.second().mul(x)));
            Optional<Num> n2 = opt.map(x -> x.second())
                    .flatMap(x -> frac.opt.map(y -> y.first().mul(x)));
            Optional<Num> numer = n1
                    .flatMap(x -> n2.map(y -> x.sub(y)));
            Optional<Num> denom = opt.map(x -> x.second())
                    .flatMap(x -> frac.opt.map(y -> y.second().mul(x)));
            Optional<Frac> fr = numer
                    .flatMap(x -> denom.map(y -> Frac.of(x, y))
                            .filter(y -> y.first().isValid()));
            return new Fraction(new AbstractNum<Frac>(fr));
        }
        return invalid();
    }

    Fraction mul(Fraction frac) {
        if (isValid() && frac.isValid()) {
            Optional<Num> numer = opt.map(x -> x.first())
                    .flatMap(x -> frac.opt.map(y -> y.first().mul(x)));
            Optional<Num> denom = opt.map(x -> x.second())
                    .flatMap(x -> frac.opt.map(y -> y.second().mul(x)));
            Optional<Frac> fr = numer
                    .flatMap(x -> denom.map(y -> Frac.of(x, y)));
            return new Fraction(new AbstractNum<Frac>(fr));
        }
        return invalid();
    }
}
