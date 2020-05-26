package com.zufar.bookshelf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Authors")
public class Authorr {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String imageLink;

    @Size(min=2, max=50)
    @Column(nullable = false, length = 50)
    private String fullName;

    @Size(min=2, max=50)
    @Column(length = 50)
    private String nickName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate birthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDate deathDay;

    @NotNull
    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Authors_Books",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> books;

    public void update(Authorr author) {
        this.id = author.getId();
        this.imageLink = author.getImageLink();
        this.fullName = author.getFullName();
        this.nickName = author.getNickName();
        this.birthday = author.getBirthday();
        this.deathDay = author.getDeathDay();
        this.country = author.getCountry();
        this.books = author.getBooks();
    }

    @Override
    public String toString() {
        List<String> bookTitles = new ArrayList<>();
        if (this.books != null) {
            books.forEach(author -> bookTitles.add(author.getTitle()));
        }
        return "Author{" +
                "id=" + id +
                ", imageLink='" + imageLink + '\'' +
                ", fullName='" + fullName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthday=" + birthday +
                ", deathDay=" + deathDay +
                ", country=" + country +
                ", books=" + bookTitles +
                '}';
    }
}
