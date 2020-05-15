package com.zufar.bookshelf.service;

import com.zufar.bookshelf.dto.AuthorDTO;
import com.zufar.bookshelf.dto.DateDTO;
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

    public Long save(AuthorDTO authorDTO) {
        DateDTO birthdayDTO = authorDTO.getBirthday();
        DateDTO deathdayDTO = authorDTO.getDeathDay();
        LocalDate birthday = LocalDate.of(birthdayDTO.getYear(), birthdayDTO.getMonth(), birthdayDTO.getDay());
        LocalDate deathday = LocalDate.of(deathdayDTO.getYear(), deathdayDTO.getMonth(), deathdayDTO.getDay());
        Country country = countryRepository.getOne(authorDTO.getCountryId());
        Author author = new Author();
        author.setFullName(authorDTO.getFullName());
        author.setNickName(authorDTO.getNickName());
        author.setBirthday(birthday);
        author.setImageLink(authorDTO.getImageLink());
        author.setDeathDay(deathday);
        if (authorDTO.getBooksIds() != null) {
            List<Book> books = bookRepository.findAllById(authorDTO.getBooksIds());
            author.setBooks(books);
            for (Book book : books) {
                book.getAuthors().add(author);
                bookRepository.save(book);
            }
        }
        author.setCountry(country);
        Author savedAuthor = authorRepository.save(author);
        log.info("Preloading " + savedAuthor);
        return savedAuthor.getId();
    }

    public void delete(Author author) {
        List<Long> bookIds = author.getBooks().stream().map(Book::getId).collect(Collectors.toList());
        bookIds.forEach(bookRepository::deleteById);
        authorRepository.delete(author);
    }

    public void update(Author author) {
        authorRepository.save(author);
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
}
