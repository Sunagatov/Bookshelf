package com.zufar.bookshelf.controller;

import com.zufar.bookshelf.dto.AuthorDTO;
import com.zufar.bookshelf.dto.DateDTO;
import com.zufar.bookshelf.entity.Author;
import com.zufar.bookshelf.entity.Book;
import com.zufar.bookshelf.entity.Country;
import com.zufar.bookshelf.service.AuthorService;
import com.zufar.bookshelf.service.BookService;
import com.zufar.bookshelf.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller("authors")
public class AuthorsController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CountryService countryService;

    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }

    @GetMapping("/addAuthorView")
    public String getAddAuthorView(ModelMap modelMap) {
        List<Book> books = bookService.getAll();
        modelMap.addAttribute("books", books);
        List<Country> countries = countryService.getAll();
        modelMap.addAttribute("countries", countries);
        return "addAuthorView";
    }

    @GetMapping("/updateAuthorView/{id}")
    public String getUpdateAuthorView(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Author author = authorService.get(id);
        modelMap.addAttribute("author", author);
        Integer birthYear = Integer.valueOf(Integer.toString(author.getBirthday().getYear()).replace(",", ""));
        modelMap.addAttribute("birthYear", birthYear);
        Integer deathYear = Integer.valueOf(Integer.toString(author.getDeathDay().getYear()).replace(",", ""));
        modelMap.addAttribute("deathYear", deathYear);
        List<Book> books = author.getBooks();
        modelMap.addAttribute("authorBooks", books);
        modelMap.addAttribute("books", bookService.getAll());
        List<Country> countries = countryService.getAll();
        modelMap.addAttribute("countries", countries);
        return "updateAuthorView";
    }

    @GetMapping("/authors")
    public String getAuthors(ModelMap modelMap) {
        List<Author> authors = authorService.getAll();
        modelMap.addAttribute("authors", authors);
        return "authorListView";
    }

    @GetMapping("/author/{id}")
    public String getAuthor(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Author author = authorService.get(id);
        modelMap.addAttribute("author", author);
        List<Book> books = author.getBooks();
        modelMap.addAttribute("books", books);
        return "authorProfileView";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(
            @RequestParam("full_name") String full_name,
            @RequestParam("nickname") String nick_name,
            @RequestParam("country") Long country,
            @RequestParam("books") Long book,
            @RequestParam("image_link") String image_link,
            @RequestParam("birthDay") int birthDay,
            @RequestParam("birthMonth") int birthMonth,
            @RequestParam("birthYear") int birthYear,
            @RequestParam("deathDay") int deathDay,
            @RequestParam("deathMonth") int deathMonth,
            @RequestParam("deathYear") int deathYear,
            ModelMap modelMap
    ) {
        String errorMessage = null;
        if (full_name == null || full_name.isEmpty() || nick_name == null || nick_name.isEmpty() || image_link == null || image_link.isEmpty() ||
                country == -1 || book == -1 || birthDay == -1 || birthMonth == -1 || deathDay == -1 || deathMonth == -1) {
            errorMessage = "Error! Some of fields are empty. Please fill all fields.";
            modelMap.addAttribute("error", errorMessage);
            return "addAuthorView";
        }
        DateDTO birth = new DateDTO(birthYear, birthMonth, birthDay);
        DateDTO death = new DateDTO(deathYear, deathMonth, deathDay);
        List<Long> booksIds = new ArrayList<>();
        booksIds.add(book);
        AuthorDTO author = new AuthorDTO(full_name, nick_name, birth, death, country, booksIds);
        author.setImageLink(image_link);
        authorService.save(author);
        List<Author> authors = authorService.getAll();
        modelMap.addAttribute("authors", authors);
        return "authorListView";
    }

    @PostMapping("/updateAuthor/{id}")
    public String updateAuthor(
            @PathVariable(value = "id") long id, ModelMap modelMap,
            @RequestParam("full_name") String full_name,
            @RequestParam("image_link") String image_link,
            @RequestParam("nickname") String nick_name,
            @RequestParam("country") Long countryId,
            @RequestParam("books") List<Long> booksIds,
            @RequestParam("birthDay") int birthDay,
            @RequestParam("birthMonth") int birthMonth,
            @RequestParam("birthYear") String birthYear,
            @RequestParam("deathDay") int deathDay,
            @RequestParam("deathMonth") int deathMonth,
            @RequestParam("deathYear") String deathYear
    ) {
        String errorMessage = null;
        if (full_name == null || full_name.isEmpty() || nick_name == null || nick_name.isEmpty() ||
                countryId == -1 || booksIds == null || booksIds.isEmpty() || image_link == null || image_link.isEmpty() ||
                birthDay == -1 || birthMonth == -1 ||
                deathDay == -1 || deathMonth == -1 ) {
            errorMessage = "Error! Some of fields are empty. Please fill all fields.";
            modelMap.addAttribute("error", errorMessage);
            return "updateAuthorView";
        }
        Author author = authorService.get(id);
        Integer byear = Integer.valueOf(Integer.toString(author.getBirthday().getYear()).replace(",", ""));
        author.setBirthday(LocalDate.of(byear, birthMonth, birthDay));
        Integer dyear = Integer.valueOf(Integer.toString(author.getDeathDay().getYear()).replace(",", ""));
        author.setDeathDay(LocalDate.of(dyear, deathMonth, deathDay));
        author.setBooks(bookService.getAll(booksIds));
        author.setCountry(countryService.get(countryId));
        author.setNickName(nick_name);
        author.setImageLink(image_link);
        authorService.update(author);
        List<Author> authors = authorService.getAll();
        modelMap.addAttribute("authors", authors);
        return "authorListView";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Author author = authorService.get(id);
        authorService.delete(author);
        List<Author> authors = authorService.getAll();
        modelMap.addAttribute("authors", authors);
        return "authorListView";
    }
}
