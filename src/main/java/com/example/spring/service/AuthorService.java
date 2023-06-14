package com.example.spring.service;

import com.example.spring.dao.AuthorDao;
import com.example.spring.dao.BookDao;
import com.example.spring.entity.Author;
import com.example.spring.entity.Book;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorDao authorDao;

    private final BookDao bookDao;

    public Author save(Author author) {
         return authorDao.save(author);
    }

    public void update(Author author) {
        Author currentAuthor = authorDao.findById(author.getId()).orElse(null);
        if (currentAuthor != null) {
            if(author.getName() != null && !author.getName().equals("")) {
                currentAuthor.setName(author.getName());
            }
            authorDao.save(currentAuthor);
        }
    }

    @Transactional
    public void delete(Author author) {
        author = authorDao.findById(author.getId()).orElse(null);
        if (author != null) {
            List<Book> books = author.getBooks();
            for (Book book : books) {
                book.getAuthor().remove(author);
            }
            bookDao.saveAll(books);

            authorDao.delete(author);
        }
    }

    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    public List<Author> findAuthorsByBookName(String bookName) {
        return authorDao.findByBooksName(bookName);
    }

    public Map<Author, List<Book>> getAuthorsWithBooks() {
        List<Author> authors = authorDao.findAll(); // Retrieve all authors from the database

        Map<Author, List<Book>> authorsWithBooks = new HashMap<>();

        for (Author author : authors) {
            List<Book> books = author.getBooks(); // Retrieve the books for each author
            authorsWithBooks.put(author, books); // Add the author and their books to the map
        }

        return authorsWithBooks;
    }
}
