package com.zufar.bookshelf.service;

import com.zufar.bookshelf.dto.BookDTO;
import com.zufar.bookshelf.dto.DateDTO;
import com.zufar.bookshelf.entity.Author;
import com.zufar.bookshelf.entity.Book;
import com.zufar.bookshelf.entity.Country;
import com.zufar.bookshelf.repository.AuthorRepository;
import com.zufar.bookshelf.repository.BookRepository;
import com.zufar.bookshelf.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


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

    public void save(BookDTO bookDTO) {
        DateDTO publication_dateDTO = bookDTO.getPublicationDate();
        LocalDate publication_date = LocalDate.of(publication_dateDTO.getYear(), publication_dateDTO.getMonth(),
                publication_dateDTO.getDay());
        Long countryId = bookDTO.getCountryId();
        Country country = countryRepository.getOne(countryId);
        List<Author> authors = authorRepository.findAllById(bookDTO.getAuthors());
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setPublicationDate(publication_date);
        book.setFb2Link(bookDTO.getFb2Link());
        book.setImageLink(bookDTO.getImageLink());
        book.setEpubLink(bookDTO.getEpubLink());
        book.setPageCount(bookDTO.getPageCount());
        book.setPdfLink(bookDTO.getPdfLink());
        book.setCountry(country);
        book.setAuthors(authors);
        bookRepository.save(book);
        for (Author author : authors) {
            author.getBooks().add(book);
            authorRepository.save(author);
        }

    }

    public void delete(Book book) {
        for (Author author : book.getAuthors()) {
            author.getBooks().remove(book);
            authorRepository.save(author);
        }
        bookRepository.delete(book);
    }

    public void update(Book book) {
        bookRepository.save(book);
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
}
