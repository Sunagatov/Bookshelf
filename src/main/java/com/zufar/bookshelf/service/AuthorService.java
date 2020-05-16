package com.zufar.bookshelf.service;

import com.zufar.bookshelf.entity.Author;
import com.zufar.bookshelf.entity.Book;
import com.zufar.bookshelf.entity.Country;
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
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, CountryRepository countryRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
    }

    public void delete(Author author) {
        List<Long> bookIds = author.getBooks().stream().map(Book::getId).collect(Collectors.toList());
        bookIds.forEach(bookRepository::deleteById);
        authorRepository.delete(author);
        log.info("Deleting {} was successful", author);

    }

    public Long update(Long id, String fullName, String nickName, int birthYear, int birthMonth, int birthDay, int deathYear, int deathMonth, int deathDay, Long countryId, List<Long> booksIds, String imageLink) {
        Author author = authorRepository.getOne(id);
        LocalDate birthday = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate deathday = LocalDate.of(deathYear, deathMonth, deathDay);
        Country country = countryRepository.getOne(countryId);
        author.setFullName(fullName);
        author.setNickName(nickName);
        author.setBirthday(birthday);
        author.setImageLink(imageLink);
        author.setDeathDay(deathday);
        if (booksIds != null) {
            List<Book> books = bookRepository.findAllById(booksIds);
            author.setBooks(books);
            for (Book book : books) {
                book.getAuthors().add(author);
                bookRepository.save(book);
            }
        }
        author.setCountry(country);

        Author savedAuthor = authorRepository.save(author);

        log.info("Updating {} was successful", savedAuthor);
        return savedAuthor.getId();
    }

    public Author get(Long id) {
        return authorRepository.getOne(id);
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public List<Author> getAll(List<Long> authorIds) {
        return authorRepository.findAllById(authorIds);
    }

    public Long save(String fullName, String nickName, int birthYear, int birthMonth, int birthDay, int deathYear, int deathMonth, int deathDay, Long countryId, List<Long> booksIds, String imageLink) {
        LocalDate birthday = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate deathday = LocalDate.of(deathYear, deathMonth, deathDay);
        Country country = countryRepository.getOne(countryId);
        Author author = new Author();
        author.setFullName(fullName);
        author.setNickName(nickName);
        author.setBirthday(birthday);
        author.setImageLink(imageLink);
        author.setDeathDay(deathday);
        if (booksIds != null) {
            List<Book> books = bookRepository.findAllById(booksIds);
            author.setBooks(books);
            for (Book book : books) {
                book.getAuthors().add(author);
                bookRepository.save(book);
            }
        }
        author.setCountry(country);

        Author savedAuthor = authorRepository.save(author);
        log.info("Saving {} was successful", savedAuthor);

        return savedAuthor.getId();
    }
}
