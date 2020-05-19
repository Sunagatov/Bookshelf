package com.zufar.bookshelf.service;

import com.zufar.bookshelf.entity.Author;
import com.zufar.bookshelf.entity.Book;
import com.zufar.bookshelf.repository.AuthorRepository;
import com.zufar.bookshelf.repository.BookRepository;
import com.zufar.bookshelf.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public BookService(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            CountryRepository countryRepository
    ) {
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
    }

    public Book get(Long id) {
        return bookRepository.getOne(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void save(Long id, String title, List<Long> authorIds, int publicationYear, int publicationMonth, int publicationDay, Long countryId, int page_count, String image_link, String fb2Link, String epubLink, String pdf_link) {
        Book book = new Book();
        if (id != null) {
            book = bookRepository.getOne(id);
        }
        book.setTitle(title);
        book.setPublicationDate(LocalDate.of(publicationYear, publicationMonth, publicationDay));
        book.setFb2Link(fb2Link);
        book.setImageLink(image_link);
        book.setEpubLink(epubLink);
        book.setPageCount(page_count);
        book.setPdfLink(pdf_link);
        book.setCountry(countryRepository.getOne(countryId));
        List<Author> authors = authorRepository.findAllById(authorIds);
        book.setAuthors(authors);
        bookRepository.save(book);
        for (Author author : authors) {
            author.getBooks().add(book);
            authorRepository.save(author);
        }
    }

    public Book save(Book book) {
        Book savedBook = this.bookRepository.save(book);
        log.info("Saving {} was successful", savedBook);
        return savedBook;
    }

    public Book update(Book book) {
        Book savedBook = this.bookRepository.save(book);
        log.info("Updating {} was successful", savedBook);
        return savedBook;
    }

    public void delete(Long id) {
        Book book = this.bookRepository.getOne(id);
        this.delete(book);
    }

    public void delete(Book book) {
        this.deleteBookInAllAuthors(book);
        bookRepository.delete(book);
        log.info("Deleting {} was successful", book);
    }

    public void deleteBookInAllAuthors(Book book) {
        book.getAuthors()
                .stream()
                .peek(author -> {
                    List<Book> lastBooks = author
                            .getBooks()
                            .stream()
                            .filter(currentBook -> currentBook.getId() != null && !currentBook.getId().equals(book.getId())).collect(Collectors.toList());
                    author.setBooks(lastBooks);
                })
                .forEach(this.authorRepository::save);
    }
}
