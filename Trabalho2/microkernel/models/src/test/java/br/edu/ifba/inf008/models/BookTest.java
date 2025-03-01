package br.edu.ifba.inf008.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testGettersAndSetters(){
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        book.setAvailable(false);

        assertFalse(book.getIsAvailable(),"Book should not be available.");
        assertEquals("Pequeno Principe",book.getTitle());
        assertEquals("Antoine de Saint-Exupéry",book.getAuthor());
        assertEquals(2015, book.getReleaseYear());
        assertEquals("Fantasia",book.getGenre());
    }
}