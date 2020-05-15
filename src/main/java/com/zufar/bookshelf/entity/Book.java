package com.zufar.bookshelf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(nullable = false)
    private String title;

    @Column
    private String imageLink;

    @Column
    private String epubLink;

    @Column
    private String fb2Link;

    @Column
    private String pdfLink;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Author> authors;

    @Column
    private LocalDate publicationDate;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column
    private int pageCount;

}
