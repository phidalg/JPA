package com.utn.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@EqualsAndHashCode(
        onlyExplicitlyIncluded = true
)
public abstract class Base {

    private static final AtomicLong COUNTER = new AtomicLong(100_000L);

    @EqualsAndHashCode.Include
    private final Long id = COUNTER.incrementAndGet();
    private boolean eliminado;
    private final LocalDateTime createdAt = LocalDateTime.now();

}
