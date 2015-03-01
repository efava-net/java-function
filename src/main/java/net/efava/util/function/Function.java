package net.efava.util.function;

/**
 * jdk7-commons
 * Created by Lucky on 1/16/2015.
 */
abstract public class Function<T, R> {
    abstract public R apply(T x);

    private final R call(T x) {
        return this.apply(x);
    }

    public final <U> Function<T, U> andThen(final Function<? super R, ? extends U> g) {
        final Function<T, R> f = this;
        return new Function<T, U>() {
            @Override
            public U apply(T x) {
                return g.apply(f.apply(x));
            }
        };
    }

    public final <S> Function<S, R> compose(final Function<? super S, ? extends T> g) {
        final Function<T, R> f = this;
        return new Function<S, R>() {
            @Override
            public R apply(S x) {
                return f.apply(g.apply(x));
            }
        };
    }
}
