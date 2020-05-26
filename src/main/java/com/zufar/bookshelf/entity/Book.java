package com.zufar.bookshelf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=2, max=40)
    @Column(nullable = false)
    private String title;

    @NotNull
    @Column
    private String imageLink;

    @Column
    private String epubLink;

    @Column
    private String fb2Link;

    @Column
    private String pdfLink;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Authorr> authors;

    @NotNull
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @NotNull
    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull
    @Column
    private int pageCount;

    public void update(Book book) {
        this.title = book.getTitle();
        this.imageLink = book.getImageLink();
        this.epubLink = book.getEpubLink();
        this.fb2Link = book.getFb2Link();
        this.pdfLink = book.getPdfLink();
        this.authors = book.getAuthors();
        this.publicationDate = book.getPublicationDate();
        this.country = book.getCountry();
        this.pageCount = book.getPageCount();
    }

    @Override
    public String toString() {
        List<String> authorNames = new ArrayList<>();
        if (this.authors != null) {
            authors.forEach(author -> authorNames.add(author.getFullName()));
        }
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", epubLink='" + epubLink + '\'' +
                ", fb2Link='" + fb2Link + '\'' +
                ", pdfLink='" + pdfLink + '\'' +
                ", authors=" + authorNames +
                ", publicationDate=" + publicationDate +
                ", country=" + country +
                ", pageCount=" + pageCount +
                '}';
    }
}
