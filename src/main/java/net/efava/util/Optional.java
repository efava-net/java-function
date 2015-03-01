package net.efava.util;

import net.efava.util.function.Function;
import net.efava.util.function.Predicate;
import net.efava.util.function.comsumer.Consumer;
import net.efava.util.function.supplier.RefSupplier;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * java-function
 * Created by Lucky on 3/1/2015.
 */
public class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<Object>();

    private final T value;

    private Optional() {
        this.value = null;
    }

    public static <T> Optional<T> empty() {
        return (Optional<T>) Optional.EMPTY;
    }

    private Optional(T t) {
        this.value = Objects.requireNonNull(t);
    }

    public static <T> Optional<T> of(T t) {
        return new Optional<T>(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        if (t == null) {
            return Optional.empty();
        } else {
            return new Optional<T>(t);
        }
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public boolean isPresent() {
        return this.value == null;
    }

    public void ifPresent(Consumer<T> consumer) {
        if (value != null) {
            consumer.apply(value);
        }
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent()) {
            return this;
        } else {
            if (predicate.test(this.value)) {
                return this;
            } else {
                return Optional.empty();
            }
        }
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> f) {
        Objects.requireNonNull(f);
        if (!isPresent()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(f.apply(this.value));
        }
    }

    public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<U>> f) {
        Objects.requireNonNull(f);
        if (!isPresent()) {
            return Optional.empty();
        } else {
            return Objects.requireNonNull(f.apply(this.value));
        }
    }

    public T orElse(T other) {
        return this.value != null ? this.value : other;
    }

    public T orElse(RefSupplier<? extends T> f) {
        return this.value != null ? this.value : f.get();
    }

    public <X extends Throwable> T orElseThrow(RefSupplier<? extends X> ef) throws X {
        if (this.value != null) {
            return this.value;
        } else {
            throw ef.get();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Optional)) {
            return false;
        }

        Optional<?> other = (Optional<?>) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value != null
                ? String.format("Optional[%s]", value)
                : "Optional.empty";
    }
}
