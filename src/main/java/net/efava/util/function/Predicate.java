package net.efava.util.function;

/**
 * jdk7-commons
 * Created by Lucky on 1/16/2015.
 */
abstract public class Predicate<T> {
    abstract public boolean test(T t);

    public Predicate<T> and(final Predicate<? super T> g) {
        final Predicate<T> f = this;
        return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return f.test(t) && g.test(t);
            }
        };
    }

    public Predicate<T> or(final Predicate<? super T> g) {
        final Predicate<T> f = this;
        return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return f.test(t) || g.test(t);
            }
        };
    }

    public Predicate<T> negate() {
        final Predicate<T> f = this;
        return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return !f.test(t);
            }
        };
    }

    static <T> Predicate<T> isEqual(final Object targetRef) {
        if (targetRef == null) {
            return new Predicate<T>() {
                @Override
                public boolean test(T t) {
                    return t == null;
                }
            };
        } else {
            return new Predicate<T>() {
                @Override
                public boolean test(T t) {
                    return targetRef.equals(t);
                }
            };
        }
    }
}
