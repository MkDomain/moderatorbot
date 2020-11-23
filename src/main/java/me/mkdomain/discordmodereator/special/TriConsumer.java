package me.mkdomain.discordmodereator.special;

import java.util.Objects;

public interface TriConsumer<A, S, D> {

    void accept(A a, S s, D d);

    default TriConsumer<A, S, D> andThen(TriConsumer<A, S, D> after) {
        Objects.requireNonNull(after);

        return (a, s, d) -> {
            accept(a, s, d);
            after.accept(a, s, d);
        };
    }
}
