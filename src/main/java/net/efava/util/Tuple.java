package net.efava.util;

/**
 * jdk7-commons
 * Created by Lucky on 1/16/2015.
 */
public class Tuple<T1, T2> {
    public final T1 _1;
    public final T2 _2;

    public Tuple(T1 t1, T2 t2) {
        this._1 = t1;
        this._2 = t2;
    }
}
