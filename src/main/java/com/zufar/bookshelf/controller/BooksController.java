package com.zufar.bookshelf.controller;

import com.zufar.bookshelf.dto.BookDTO;
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

    @GetMapping("/updateBookView/{id}")
    public String getUpdateBookView(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Book book = bookService.get(id);
        modelMap.addAttribute("book", book);
        modelMap.addAttribute("bookAuthors", book.getAuthors());
        modelMap.addAttribute("authors", authorService.getAll());
        modelMap.addAttribute("countries", countryService.getAll());
        Integer publicationYear = Integer.valueOf(Integer.toString(book.getPublicationDate().getYear()).replace(",", ""));
        modelMap.addAttribute("publicationYear", publicationYear);
        return "updateBookView";
    }

    @GetMapping("/getAddBookView")
    public String getAddBookView(ModelMap modelMap) {
        List<Author> authors = authorService.getAll();
        modelMap.addAttribute("authors", authors);
        List<Country> countries = countryService.getAll();
        modelMap.addAttribute("countries", countries);
        return "addBookView";
    }

    @GetMapping("/books")
    public String getBooks(ModelMap modelMap) {
        List<Book> books = bookService.getAll();
        modelMap.addAttribute("books", books);
        return "bookListView";
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Book book = bookService.get(id);
        modelMap.addAttribute("book", book);
        modelMap.addAttribute("authors",  book.getAuthors());
        return "bookProfileView";
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
        String errorMessage = null;
        if (title == null || title.isEmpty() || country == -1 || author == -1 ||
                publicationDay == -1 || publicationMonth == -1 || publicationYear == -1 || page_count == -1) {
            errorMessage = "Error! Some of fields are empty. Please fill all fields.";
            modelMap.addAttribute("error", errorMessage);
            return "addBookView";
        }
        DateDTO publicationDate = new DateDTO(publicationYear, publicationMonth, publicationDay);
//        Set<Long> genresIds = new HashSet<>();
//        genresIds.add(genre);
        List<Long> authorIds = new ArrayList<>();
        authorIds.add(author);
        BookDTO book = new BookDTO(title, authorIds, publicationDate, country, page_count);
        book.setImageLink(image_link);
        book.setFb2Link(fb2Link);
        book.setEpubLink(epubLink);
        book.setPdfLink(pdf_link);
        bookService.save(book);
        List<Book> books = bookService.getAll();
        modelMap.addAttribute("books", books);
        return "bookListView";
    }

    @PostMapping("/updateBook/{id}")
    public String updateBook(
            @PathVariable(value = "id") long id, ModelMap modelMap,
            @RequestParam("title") String title,
            @RequestParam("country") Long countryId,
            @RequestParam("pageCount") int pageCount,
            @RequestParam("authors") List<Long> authorsIds,
            @RequestParam("publicationDay") int publicationDay,
            @RequestParam("publicationMonth") int publicationMonth,
            @RequestParam("publicationYear") String publicationYear,
            @RequestParam("pdf_link") String pdf_link,
            @RequestParam("epubLink") String epubLink,
            @RequestParam("fb2Link") String fb2Link,
            @RequestParam("image_link") String image_link
    ) {
        String errorMessage = null;
        if (title == null || title.isEmpty() ||
                countryId == -1 || authorsIds == null || authorsIds.isEmpty() ||
                publicationDay == -1 || publicationMonth == -1) {
            errorMessage = "Error! Some of fields are empty. Please fill all fields.";
            modelMap.addAttribute("error", errorMessage);
            return "updateAuthorView";
        }
        Book book = bookService.get(id);
        book.setImageLink(image_link);
        book.setFb2Link(fb2Link);
        book.setEpubLink(epubLink);
        book.setPdfLink(pdf_link);
        book.setTitle(title);
        List<Author> selectedAuthors = authorService.getAll(authorsIds);
        book.setAuthors(selectedAuthors);
        book.setCountry(countryService.get(countryId));
        book.setPageCount(pageCount);
        int year = Integer.valueOf(publicationYear.replace(",", ""));
        book.setPublicationDate(LocalDate.of(year, publicationMonth, publicationDay));
        bookService.update(book);
        modelMap.addAttribute("books", bookService.getAll());
        return "bookListView";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") long id, ModelMap modelMap) {
        Book book = bookService.get(id);
        bookService.delete(book);
        List<Book> books = bookService.getAll();
        modelMap.addAttribute("books", books);
        return "bookListView";
    }
}
