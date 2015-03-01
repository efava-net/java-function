package net.efava.util.function;

import com.sf2.commons.utils.Tuple;

/**
 * jdk7-commons
 * Created by Lucky on 1/16/2015.
 */
abstract public class Function2<T1, T2, R> {

    abstract public R accept(T1 t1, T2 t2);

    public final Function<T1, Function<T2, R>> curried() {
        final Function2<T1, T2, R> f = this;
        return new Function<T1, Function<T2, R>>() {
            @Override
            public Function<T2, R> apply(final T1 x) {
                return new Function<T2, R>() {
                    @Override
                    public R apply(final T2 y) {
                        return f.accept(x, y);
                    }
                };
            }
        };
    }

    public final Function<Tuple<T1, T2>, R> tupled() {
        final Function2<T1, T2, R> f = this;
        return new Function<Tuple<T1, T2>, R>() {
            @Override
            public R apply(Tuple<T1, T2> x) {
                return f.accept(x._1, x._2);
            }
        };
    }
}
