package com.zufar.bookshelf.service;

import com.zufar.bookshelf.entity.Authorr;
import com.zufar.bookshelf.entity.Book;
import com.zufar.bookshelf.entity.Country;
import com.zufar.bookshelf.repository.AuthorRepository;
import com.zufar.bookshelf.repository.BookRepository;
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
    private final CountryService countryService;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository,
                         CountryService countryService,
                         BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
        this.bookRepository = bookRepository;
    }

    public Authorr save(Long id, String fullName, String nickName, int birthYear, int birthMonth, int birthDay,
                        int deathYear, int deathMonth, int deathDay, Long countryId, List<Long> booksIds, String imageLink) {
        Authorr author = new Authorr();
        if (id != null) {
            author = authorRepository.getOne(id);
        }
        LocalDate birthday = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate deathday = LocalDate.of(deathYear, deathMonth, deathDay);
        Country country = countryService.get(countryId);
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

        Authorr savedAuthor = authorRepository.save(author);

        log.info("Updating {} was successful", savedAuthor);
        return savedAuthor;
    }

    public Authorr get(Long id) {
        return authorRepository.getOne(id);
    }

    public List<Authorr> getAll() {
        return authorRepository.findAll();
    }

    public Authorr save(Authorr author) {
        Authorr savedAuthor = this.authorRepository.save(author);
        log.info("Saving {} was successful", savedAuthor);
        return savedAuthor;
    }

    public Authorr update(Authorr author) throws Exception {
        Authorr oldAuthor = this.get(author.getId());
        if (oldAuthor == null) throw new Exception();
        oldAuthor.update(author);
        log.info("Updating {} was successful", oldAuthor);
        return oldAuthor;
    }

    public void delete(Authorr author) {
        this.deleteAuthorInAllBooks(author);
        authorRepository.delete(author);
        log.info("Deleting {} was successful", author);

    }

    public void delete(long id) {
        this.delete(this.get(id));
    }

    public void deleteAuthorInAllBooks(Authorr author) {
        author.getBooks()
                .stream()
                .peek(book -> {
                    List<Authorr> lastAuthors = book
                            .getAuthors()
                            .stream()
                            .filter(current ->
                                    current.getId() != null && !current.getId().equals(author.getId()))
                            .collect(Collectors.toList());
                    book.setAuthors(lastAuthors);
                })
                .forEach(this.bookRepository::save);
    }
}
