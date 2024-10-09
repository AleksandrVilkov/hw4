package org.example;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Можно изменять по своему усмотрению, не нарушая правила приоритезации очереди
 */
public class Ticket implements Comparable<Ticket> {

    private static int idSeq;

    /**
     * Автогенерация id
     */
    int id = ++idSeq;

    /**
     * Приоритеты типов:
     * 1) pension
     * 2) любые другие
     */
    String type;

    /**
     * Приоритет для ранней регистрации
     */
    OffsetDateTime registerTime = OffsetDateTime.now();

    public Ticket(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Ticket o) {
        if (!"pension".equals(this.type) && "pension".equals(o.type)) return 1;

        if ("pension".equals(this.type) && !"pension".equals(o.type)) return -1;

        return Long.compare(
                this.registerTime.toLocalDateTime().toEpochSecond(ZoneOffset.UTC),
                o.registerTime.toLocalDateTime().toEpochSecond(ZoneOffset.UTC)
        );
    }
}
