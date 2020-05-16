package com.zufar.bookshelf.controller;

import com.zufar.bookshelf.entity.Author;
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

    @GetMapping("/authors")
    public String getAuthors(ModelMap modelMap) {
        modelMap.addAttribute("authors", authorService.getAll());
        return "authorListView";
    }

    @GetMapping("/author/{id}")
    public String getAuthor(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Author author = authorService.get(id);
        modelMap.addAttribute("author", author);
        modelMap.addAttribute("books", author.getBooks());
        return "authorProfileView";
    }

    @GetMapping("/addAuthorView")
    public String getAddAuthorView(ModelMap modelMap) {
        modelMap.addAttribute("books", bookService.getAll());
        modelMap.addAttribute("countries", countryService.getAll());
        return "addAuthorView";
    }

    @GetMapping("/updateAuthorView/{id}")
    public String getUpdateAuthorView(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Author author = authorService.get(id);
        Integer birthYear = Integer.valueOf(Integer.toString(author.getBirthday().getYear()).replace(",", ""));
        Integer deathYear = Integer.valueOf(Integer.toString(author.getDeathDay().getYear()).replace(",", ""));

        modelMap.addAttribute("author", author);
        modelMap.addAttribute("birthYear", birthYear);
        modelMap.addAttribute("deathYear", deathYear);
        modelMap.addAttribute("authorBooks", author.getBooks());
        modelMap.addAttribute("books", bookService.getAll());
        modelMap.addAttribute("countries", countryService.getAll());
        return "updateAuthorView";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(
            @RequestParam("fullName") String fullName,
            @RequestParam("nickname") String nickName,
            @RequestParam("country") Long country,
            @RequestParam("books") Long book,
            @RequestParam("imageLink") String imageLink,
            @RequestParam("birthDay") int birthDay,
            @RequestParam("birthMonth") int birthMonth,
            @RequestParam("birthYear") int birthYear,
            @RequestParam("deathDay") int deathDay,
            @RequestParam("deathMonth") int deathMonth,
            @RequestParam("deathYear") int deathYear,
            ModelMap modelMap
    ) {
        List<Long> booksIds = new ArrayList<>();
        booksIds.add(book);
        authorService.save(fullName, nickName, birthYear, birthMonth, birthDay,
                deathYear, deathMonth, deathDay, country, booksIds, imageLink);

        modelMap.addAttribute("authors", authorService.getAll());
        return "authorListView";
    }

    @PostMapping("/updateAuthor/{id}")
    public String updateAuthor(
            @PathVariable(value = "id") long id, ModelMap modelMap,
            @RequestParam("fullName") String fullName,
            @RequestParam("imageLink") String imageLink,
            @RequestParam("nickname") String nickName,
            @RequestParam("country") Long countryId,
            @RequestParam("books") List<Long> booksIds,
            @RequestParam("birthDay") int birthDay,
            @RequestParam("birthMonth") int birthMonth,
            @RequestParam("birthYear") int birthYear,
            @RequestParam("deathDay") int deathDay,
            @RequestParam("deathMonth") int deathMonth,
            @RequestParam("deathYear") int deathYear
    ) {
        authorService.update(id , fullName, nickName, birthYear, birthMonth, birthDay,
                deathYear, deathMonth, deathDay, countryId, booksIds, imageLink);
        modelMap.addAttribute("authors", authorService.getAll());
        return "authorListView";
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable(value = "id") long id, ModelMap modelMap) {
        authorService.delete(authorService.get(id));
        modelMap.addAttribute("authors", authorService.getAll());
        return "authorListView";
    }
}
