package com.zufar.bookshelf.controller;

import com.zufar.bookshelf.entity.Book;
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

@Controller
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    @Autowired
    public BooksController(
            BookService bookService,
            AuthorService authorService,
            CountryService countryService
    ) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping("/books")
    public String getBooks(ModelMap modelMap) {
        modelMap.addAttribute("books", bookService.getAll());
        return "bookListView";
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Book book = bookService.get(id);
        modelMap.addAttribute("book", book);
        modelMap.addAttribute("authors",  book.getAuthors());
        return "bookProfileView";
    }

    @GetMapping("/getAddBookView")
    public String getAddBookView(ModelMap modelMap) {
        modelMap.addAttribute("authors", authorService.getAll());
        modelMap.addAttribute("countries", countryService.getAll());
        return "addBookView";
    }

    @PostMapping("/addBook")
    public String addBook(
            @RequestParam("title") String title,
            @RequestParam("country") Long country,
            @RequestParam("authors") Long author,
            @RequestParam("publicationDay") int publicationDay,
            @RequestParam("publicationMonth") int publicationMonth,
            @RequestParam("publicationYear") int publicationYear,
            @RequestParam("pdf_link") String pdf_link,
            @RequestParam("page_count") int page_count,
            @RequestParam("epubLink") String epubLink,
            @RequestParam("fb2Link") String fb2Link,
            @RequestParam("image_link") String image_link,
            ModelMap modelMap
    ) {
        List<Long> authorIds = new ArrayList<>();
        authorIds.add(author);

        bookService.save(title, authorIds, publicationYear, publicationMonth, publicationDay, country, page_count, image_link, fb2Link, epubLink, pdf_link);
        modelMap.addAttribute("books", bookService.getAll());
        return "bookListView";
    }

    @GetMapping("/updateBookView/{id}")
    public String getUpdateBookView(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Book book = bookService.get(id);
        modelMap.addAttribute("book", book);
        modelMap.addAttribute("bookAuthors", book.getAuthors());
        modelMap.addAttribute("authors", authorService.getAll());
        modelMap.addAttribute("countries", countryService.getAll());
        modelMap.addAttribute("publicationYear", Integer.valueOf(Integer.toString(book.getPublicationDate().getYear()).replace(",", "")));
        return "updateBookView";
    }

    @PostMapping("/updateBook/{id}")
    public String updateBook(
            @PathVariable(value = "id") long id, ModelMap modelMap,
            @RequestParam("title") String title,
            @RequestParam("country") Long countryId,
            @RequestParam("pageCount") int pageCount,
            @RequestParam("authors") List<Long> authorIds,
            @RequestParam("publicationDay") int publicationDay,
            @RequestParam("publicationMonth") int publicationMonth,
            @RequestParam("publicationYear") int publicationYear,
            @RequestParam("pdfLink") String pdfLink,
            @RequestParam("epubLink") String epubLink,
            @RequestParam("fb2Link") String fb2Link,
            @RequestParam("imageLink") String imageLink
    ) {
        bookService.update(id, title, authorIds, publicationYear, publicationMonth, publicationDay, countryId, pageCount, imageLink, fb2Link, epubLink, pdfLink);
        modelMap.addAttribute("books", bookService.getAll());
        return "bookListView";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") long id, ModelMap modelMap) {
        bookService.delete(id);
        modelMap.addAttribute("books", bookService.getAll());
        return "bookListView";
    }
}
