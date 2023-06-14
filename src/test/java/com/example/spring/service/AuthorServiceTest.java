package com.example.spring.service;

import com.example.spring.dao.AuthorDao;
import com.example.spring.dao.BookDao;
import com.example.spring.entity.Author;
import com.example.spring.entity.Book;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorDao authorDao;

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void saveAuthor_ShouldSaveAuthor() {
        int authorId = 1;
        Author author = new Author();
        author.setName("John Doe");

        when(authorDao.save(author)).thenReturn(new Author(authorId, author.getName()));

        Author savedAuthor = authorService.save(author);

        verify(authorDao).save(author);
        assertNotNull(savedAuthor.getId());
    }

    @Test
    public void updateAuthor_ShouldUpdateAuthor() {
        Author existingAuthor = new Author();
        existingAuthor.setId(1);
        existingAuthor.setName("John Doe");

        Author updatedAuthor = new Author();
        updatedAuthor.setId(1);
        updatedAuthor.setName("Jane Smith");

        when(authorDao.findById(updatedAuthor.getId())).thenReturn(Optional.of(existingAuthor));

        authorService.update(updatedAuthor);

        verify(authorDao).save(existingAuthor);
        assertEquals("Jane Smith", existingAuthor.getName());
    }

    @Test
    public void deleteAuthor_ShouldRemoveAuthorAndBooks() {
        Author author = new Author();
        author.setId(1);
        author.setName("John Doe");

        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Book 1");
        book1.getAuthor().add(author);
        author.getBooks().add(book1);

        Book book2 = new Book();
        book2.setId(2);
        book2.setName("Book 2");
        book2.getAuthor().add(author);
        author.getBooks().add(book2);

        when(authorDao.findById(author.getId())).thenReturn(Optional.of(author));

        authorService.delete(author);

        verify(bookDao).saveAll(author.getBooks());
        verify(authorDao).delete(author);
    }

    @Test
    public void getAllAuthors_ShouldReturnAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "John Doe"));
        authors.add(new Author(2, "Jane Smith"));

        when(authorDao.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAllAuthors();

        assertEquals(authors, result);
    }

    @Test
    public void findAuthorsByBookName_ShouldReturnAuthorsForBookName() {
        String bookName = "Book 1";
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "John Doe"));
        authors.add(new Author(2, "Jane Smith"));

        when(authorDao.findByBooksName(bookName)).thenReturn(authors);

        List<Author> result = authorService.findAuthorsByBookName(bookName);

        assertEquals(authors, result);
    }

    @Test
    public void getAuthorsWithBooks_ShouldReturnMapOfAuthorsWithBooks() {
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author(1, "John Doe");
        Book book1 = new Book(1, "Book 1");
        author1.getBooks().add(book1);
        book1.getAuthor().add(author1);
        authors.add(author1);

        Author author2 = new Author(2, "Jane Smith");
        Book book2 = new Book(2, "Book 2");
        author2.getBooks().add(book2);
        book2.getAuthor().add(author2);
        authors.add(author2);

        when(authorDao.findAll()).thenReturn(authors);

        Map<Author, List<Book>> result = authorService.getAuthorsWithBooks();

        assertEquals(2, result.size());
        assertEquals(Collections.singletonList(book1), result.get(author1));
        assertEquals(Collections.singletonList(book2), result.get(author2));
    }
}