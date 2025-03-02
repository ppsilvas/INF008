package br.edu.ifba.inf008.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BooksStatusTest {
    @Test
    public void testGettersAndSetters(){
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        BooksStatus booksStatus = new BooksStatus(book, true);

        assertEquals(book, booksStatus.getBook(),"Books should be equal.");
        assertTrue(booksStatus.getIsLoaned(),"Book should be loaned");

        booksStatus.setIsLoaned(false);
        
        assertFalse(booksStatus.getIsLoaned(),"Book should not be loaned");
    }
}
