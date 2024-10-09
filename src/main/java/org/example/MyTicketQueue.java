package org.example;

import java.util.Arrays;

public class MyTicketQueue implements TicketManager {

    private Ticket[] queue;

    public MyTicketQueue() {
        this.queue = new Ticket[0];
    }

    @Override
    public void add(Ticket ticket) {
        if (ticket == null) throw new NullPointerException();

        queue = Arrays.copyOf(queue, queue.length + 1);
        int pos = queue.length - 1;

        if (queue.length == 1) {
            queue[pos] = ticket;
            return;
        }

        while (pos > 0) {
            int parentPos = pos - 1;
            var parent = queue[parentPos];
            if (parent.compareTo(ticket) >= 0)
                break;
            queue[pos] = parent;
            pos = parentPos;

        }
        queue[pos] = ticket;
    }

    @Override
    public Ticket next() {
        if (queue.length == 0)
            return null; //пустая очередь

        var next = queue[queue.length - 1];
        queue = Arrays.copyOf(queue, queue.length - 1);
        return next;
    }
}
