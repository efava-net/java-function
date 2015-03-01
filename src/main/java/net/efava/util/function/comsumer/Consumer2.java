package net.efava.util.function.comsumer;

import com.sf2.commons.utils.Tuple;
import com.sf2.commons.utils.function.Function;

/**
 * jdk7-commons
 * Created by Lucky on 1/16/2015.
 */
abstract public class Consumer2<T1, T2> {
    abstract void apply(T1 t1, T2 t2);

    public Function<T1, Consumer<T2>> curried() {
        final Consumer2<T1, T2> f = this;
        return new Function<T1, Consumer<T2>>() {
            @Override
            public Consumer<T2> apply(final T1 x) {
                return new Consumer<T2>() {
                    @Override
                    public void apply(final T2 y) {
                        f.apply(x, y);
                    }
                };
            }
        };
    }

    public Consumer<Tuple<T1, T2>> tupled() {
        final Consumer2<T1, T2> f = this;
        return new Consumer<Tuple<T1, T2>>() {
            @Override
            public void apply(Tuple<T1, T2> x) {
                f.apply(x._1, x._2);
            }
        };
    }
}
