package com.zufar.bookshelf.controller;

import com.zufar.bookshelf.entity.Author;
import com.zufar.bookshelf.entity.Authorr;
import com.zufar.bookshelf.service.AuthorService;
import com.zufar.bookshelf.service.BookService;
import com.zufar.bookshelf.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller("authors")
public class AuthorsController implements WebMvcConfigurer {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CountryService countryService;

    @Autowired
    public AuthorsController(AuthorService authorService,
                             BookService bookService,
                             CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }

    @GetMapping("/authors")
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("authors", authorService.getAll());
        return "lists/authorListView";
    }

    @GetMapping("/authors/{id}")
    public String get(@PathVariable(value = "id") long id, ModelMap modelMap) {
        modelMap.addAttribute("author", authorService.get(id));
        return "profiles/authorProfileView";
    }

    @GetMapping("/addAuthorForm")
    public String getAddForm(ModelMap modelMap) {
        modelMap.addAttribute("author", new Author());
        modelMap.addAttribute("books", bookService.getAll());
        modelMap.addAttribute("countries", countryService.getAll());
        return "crud/addAuthorView";
    }

    @PostMapping("/authors")
    public String add( @Valid @ModelAttribute("author") Author author,
                       BindingResult bindingResult,
                       ModelMap modelMap ) {
        if (bindingResult.hasErrors()) {
            return "crud/addAuthorView";
        }
        //   authorService.save(author);
        modelMap.addAttribute("authors", authorService.getAll());
        return "lists/authorListView";
    }

    @GetMapping("/updateAuthorForm/{id}")
    public String getUpdateAuthorView(@PathVariable(value = "id") long id, ModelMap modelMap) {
        modelMap.addAttribute("author", authorService.get(id));
        modelMap.addAttribute("books", bookService.getAll());
        modelMap.addAttribute("countries", countryService.getAll());
        return "crud/updateAuthorView";
    }



    @PostMapping("/authors/{id}")
    public String update(@PathVariable(value = "id") long id,
                         @Valid Authorr author,
                         ModelMap modelMap) throws Exception {
        author.setId(id);
        authorService.update(author);
        modelMap.addAttribute("authors", authorService.getAll());
        return "lists/authorListView";
    }

    @PostMapping("/deleteAuthor/{id}")
    public String delete(@PathVariable(value = "id") long id, ModelMap modelMap) {
        authorService.delete(id);
        modelMap.addAttribute("authors", authorService.getAll());
        return "lists/authorListView";
    }
}
