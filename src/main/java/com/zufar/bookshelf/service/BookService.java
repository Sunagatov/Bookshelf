package com.zufar.bookshelf.service;

import com.zufar.bookshelf.entity.Authorr;
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
        List<Authorr> authors = authorRepository.findAllById(authorIds);
        book.setAuthors(authors);
        bookRepository.save(book);
        for (Authorr author : authors) {
            author.getBooks().add(book);
            authorRepository.save(author);
        }
    }

    public Book save(Book book) throws Exception {
        Book oldBook = this.get(book.getId());
        if (oldBook == null) throw new Exception();
        oldBook.update(book);
        log.info("Saving {} was successful", oldBook);
        return oldBook;
    }

    public Book update(Book book) throws Exception {
        Book oldBook = this.get(book.getId());
        if (oldBook == null) throw new Exception();
        oldBook.update(book);
        log.info("Updating {} was successful", oldBook);
        return oldBook;
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
                            .filter(currentBook ->
                                    currentBook.getId() != null && !currentBook.getId().equals(book.getId()))
                            .collect(Collectors.toList());
                    author.setBooks(lastBooks);
                })
                .forEach(this.authorRepository::save);
    }
}
