package com.zufar.bookshelf.dto;

import com.zufar.bookshelf.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private Long id;
    private String fullName;
    private String imageLink;
    private String nickName;
    private DateDTO birthday;
    private DateDTO deathDay;
    private Long countryId;
    private List<Long> booksIds;
    private String bio;

    public AuthorDTO(Author author) {
        this.nickName = author.getNickName();

        LocalDate birthday = author.getBirthday();
        Integer birthYear = Integer.valueOf(Integer.toString(birthday.getYear()).replace(",", ""));
        this.birthday = new DateDTO(birthYear, birthday.getMonthValue(), birthday.getDayOfMonth());

        LocalDate deathday = author.getDeathDay();
        Integer deathYear = Integer.valueOf(Integer.toString(deathday.getYear()).replace(",", ""));
        this.deathDay = new DateDTO(deathYear, deathday.getMonthValue(), deathday.getDayOfMonth());
    }
}
