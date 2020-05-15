package com.zufar.bookshelf.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "Authors")
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String imageLink;

    @Column(nullable = false, length = 50)
    private String full_name;

    @Column(length = 50)
    private String nick_name;


    @Column(nullable = false)
    private LocalDate birthday;

    @Column
    private LocalDate deathday;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Authors_Books",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> books;
}
