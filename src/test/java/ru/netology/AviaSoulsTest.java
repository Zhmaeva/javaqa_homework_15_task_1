package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.manager.AviaSouls;
import ru.netology.manager.TicketTimeComparator;

public class AviaSoulsTest {

    Ticket ticket1 = new Ticket("Москва", "Дубай", 28_000, 15, 20);
    Ticket ticket2 = new Ticket("Стамбул", "Санкт-Петербург", 19_082, 10, 14);
    Ticket ticket3 = new Ticket("Ташкент", "Абу-Даби", 10_988, 6, 14);
    Ticket ticket4 = new Ticket("Денпасар", "Куала-Лумпур", 7_089, 13, 17);
    Ticket ticket5 = new Ticket("Москва", "Дубай", 27_999, 15, 21);
    Ticket ticket6 = new Ticket("Санкт-Петербург", "Стамбул", 19_082, 10, 15);


    @Test
    public void shouldCompareToAbovePrice() {
        int expected = 1;
        int actual = ticket1.compareTo(ticket5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareToUnderPrice() {
        int expected = -1;
        int actual = ticket4.compareTo(ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareToEqualPrice() {
        int expected = 0;
        int actual = ticket2.compareTo(ticket6);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsByPriceSorts() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {ticket5, ticket1};
        Ticket[] actual = manager.search("Москва", "Дубай");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchTicketsByPriceSorts() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Куала-Лумпур", "Денпасар");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareByFlightTime() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        int expectedAbove = 1;
        int actualAbove = timeComparator.compare(ticket6, ticket4);
        int expectedUnder = -1;
        int actualUnder = timeComparator.compare(ticket2, ticket3);
        int expectedEqual = 0;
        int actualEqual = timeComparator.compare(ticket1, ticket6);

        Assertions.assertEquals(expectedAbove, actualAbove);
        Assertions.assertEquals(expectedUnder, actualUnder);
        Assertions.assertEquals(expectedEqual, actualEqual);
    }

    @Test
    public void shouldSearchAndSortByComparator() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls manager = new AviaSouls();

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {ticket1, ticket5};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Дубай", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchAndSortByComparator() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls manager = new AviaSouls();

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("Дубай","Москва", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
