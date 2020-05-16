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
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    public Book get(Long id) {
        return bookRepository.getOne(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getAll(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }

    public void save(String title,
                     List<Long> authorIds,
                     int publicationYear, int publicationMonth, int publicationDay,
                     Long countryId,
                     int page_count,
                     String image_link,
                     String fb2Link, String epubLink, String pdf_link) {
        Book book = new Book();
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
        log.info("Saving {} was successful", book);
    }

    public void update(Long id, String title, List<Long> authorIds, int publicationYear, int publicationMonth, int publicationDay, Long countryId, int page_count, String image_link, String fb2Link, String epubLink, String pdf_link) {
        Book book = bookRepository.getOne(id);
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
        log.info("Updating {} was successful", book);
    }

    public void delete(Book book) {
        for (Author author : book.getAuthors()) {
            author.getBooks().remove(book);
            authorRepository.save(author);
        }
        bookRepository.delete(book);
        log.info("Deleting {} was successful", book);
    }

    public void delete(Long id) {
        Book book = this.bookRepository.getOne(id);
        this.delete(book);
    }
}
