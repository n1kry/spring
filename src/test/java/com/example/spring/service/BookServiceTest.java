package com.example.spring.service;

import com.example.spring.dao.AuthorDao;
import com.example.spring.dao.BookDao;
import com.example.spring.entity.Author;
import com.example.spring.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookDao bookDao;

    @Mock
    private AuthorDao authorDao;

    @InjectMocks
    private BookService bookService;

    @Test
    public void findAll_ShouldReturnAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book 1"));
        books.add(new Book(2, "Book 2"));

        when(bookDao.findAll()).thenReturn(books);

        List<Book> result = bookService.findAll();

        assertEquals(books, result);
    }

    @Test
    public void findById_ExistingId_ShouldReturnBook() {
        int bookId = 1;
        Book book = new Book(bookId, "Book 1");

        when(bookDao.findById(bookId)).thenReturn(Optional.of(book));

        Book result = bookService.findById(bookId);

        assertEquals(book, result);
    }

    @Test
    public void findById_NonExistingId_ShouldReturnNull() {
        int bookId = 1;

        when(bookDao.findById(bookId)).thenReturn(Optional.empty());

        Book result = bookService.findById(bookId);

        assertNull(result);
    }

    @Test
    public void saveBook_ShouldSaveBook() {
        Book book = new Book();
        book.setName("Book 1");

        bookService.save(book);

        verify(bookDao).save(book);
    }

    @Test
    public void updateBook_ShouldUpdateBook() {
        int bookId = 1;
        Book existingBook = new Book(bookId, "Book 1");
        existingBook.setAuthor(new ArrayList<>());

        Book updatedBook = new Book(bookId, "Updated Book");
        updatedBook.getAuthor().add(new Author(1, "John Doe"));

        when(bookDao.findById(bookId)).thenReturn(Optional.of(existingBook));

        bookService.update(updatedBook);

        verify(bookDao).save(existingBook);
        assertEquals("Updated Book", existingBook.getName());
        assertEquals(1, existingBook.getAuthor().size());
        assertEquals("John Doe", existingBook.getAuthor().get(0).getName());
    }

    @Test
    public void deleteBook_ShouldRemoveBookAndAuthors() {
        int bookId = 1;
        Book book = new Book(bookId, "Book 1");

        Author author1 = new Author(1, "John Doe");
        book.getAuthor().add(author1);
        author1.getBooks().add(book);

        Author author2 = new Author(2, "Jane Smith");
        book.getAuthor().add(author2);
        author2.getBooks().add(book);

        when(bookDao.findById(bookId)).thenReturn(Optional.of(book));

        bookService.delete(book);

        verify(authorDao).saveAll(book.getAuthor());
        verify(bookDao).delete(book);
    }
}