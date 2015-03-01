package net.efava.util.function.comsumer;

/**
 * jdk7-commons
 * Created by Lucky on 1/16/2015.
 */
abstract public class Consumer<T> {

    abstract public void apply(T x);

    public final Consumer<T> compose(final Consumer<? super T> g) {
        final Consumer<T> f = this;
        return new Consumer<T>() {
            @Override
            public void apply(T x) {
                f.apply(x);
                g.apply(x);
            }
        };
    }

    public final Consumer<T> andThen(final Consumer<? super T> g) {
        final Consumer<T> f = this;
        return new Consumer<T>() {
            @Override
            public void apply(T x) {
                g.apply(x);
                f.apply(x);
            }
        };
    }
}
