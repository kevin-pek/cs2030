import java.util.Optional;

class Num extends AbstractNum<Integer> {
    private Num(AbstractNum<Integer> n) {
        super(n.opt);
    }

    public Num(Optional<Integer> o) {
        super(o);
    }

    private static Num invalid() {
        return new Num(new AbstractNum<Integer>(Optional.empty()));
    }

    static Num of(int i) {
        AbstractNum<Integer> n = new AbstractNum<Integer>(i);
        return valid.test(i) ? new Num(n) :
                invalid();
    }

    static Num one() {
        return Num.zero().succ();
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    Num succ() {
        return new Num(opt.map(s));
    }

    Num add(Num n) {
        if (n.isValid() && isValid()) {
            Num result = new Num(opt);
            for (Num i = Num.zero(); !i.equals(n); i = i.succ()) {
                result = result.succ();
            }
            return result;
        }
        return invalid();
    }

    Num sub(Num num) {
        if (num.isValid() && isValid()) {
            Num result = new Num(num.opt.map(n)).add(this);
            return new Num(new AbstractNum<Integer>(result.opt.filter(valid)));
        }
        return invalid();
    }

    Num mul(Num n) {
        if (n.isValid() && isValid()) {
            Num result = Num.zero();
            for (Num i = Num.zero(); !i.equals(n); i = i.succ()) {
                result = result.add(this);
            }
            return result;
        }
        return invalid();
    }
}
